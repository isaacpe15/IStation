package IStation.ProbadorOffline;

import IStation.Cliente.Applet.PanelCargando;
import IStation.Cliente.Base.DialogoConfiguracion;
import IStation.Cliente.Base.JuegoAbstracto;
import IStation.Cliente.Base.PanelBase;
import IStation.Cliente.Modulos.IComponenteCliente;
import IStation.Modulos.Comunicaciones.Mensaje;
import IStation.Modulos.Juegos.Compartido.DatosPartida;
import IStation.Modulos.Juegos.Compartido.MensajePartida;
import IStation.Modulos.Juegos.Servidor.IInicioPartida;
import java.awt.Frame;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.jar.Attributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Vista extends javax.swing.JApplet implements IComponenteCliente, Runnable {

    private HashMap<String, IInicioPartida> _iniciadores;
    private URLClassLoader _loader;
    private static final int PROTOCOLO_PARTIDA = 2;
    private String _mensaje;
    private int _idPartida = -1;
    private JuegoAbstracto _juegoAbs;
    public JuegoAbstracto[] _arrayJuegoAbs;
    public DatosPartida _configuracion;
    public Thread _initThread;
    public BlockingQueue<Object[]> _mensajes = new LinkedBlockingQueue();
    private ModuloComunicaciones _modCom;
    private ModuloComunicaciones[] _arrayModCom;
    private ModuloJuegos _modJuego;
    public Thread[] _arrayMainThread;
    private Semaphore _sem;
    private JPanel[] _arrayPanel;
    public Frame[] _arrayFrame;
    private PanelBase[] _arrayPanelJuego;
    private Controlador _controlador;
    private Class _c;
    private String rutaFile;

    private static void prepararClassLoader(URL url) {
        try {
            java.lang.reflect.Method m = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            m.setAccessible(true);
            m.invoke(ClassLoader.getSystemClassLoader(), url);
        } catch (Exception ex) {
            System.err.println("Fallo al inicializar el cargador de clases");
            ex.printStackTrace();
        }

    }

    public Vista(File ruta, File servidor, Attributes att, DatosPartida datos, ModuloJuegos m, JFrame padre) {
        DialogoConfiguracion _dConfig;

        try {
            URL[] array = new URL[1];
            array[0] = ruta.toURI().toURL();
            URLClassLoader loader = new URLClassLoader(array);
            String clase = att.getValue("ClaseJuego").toString();
            System.out.println(clase);
            _c = loader.loadClass(clase);
            rutaFile = new File(ruta.getParent()).toURI().toURL().toString();
            _modCom = new ModuloComunicaciones(this, 0);
            try {
                _juegoAbs = (JuegoAbstracto) _c.getConstructors()[0].newInstance(_modCom, "Jugador 11", rutaFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Frame panel = new Frame();
            _dConfig = _juegoAbs.damePanelConfiguracion(null);
            _configuracion = _dConfig.lanzaDialogo();
            Object[] array2 = new Object[1];
            Object[] array3 = new Object[2];
            array2[0] = (Object) "creacion";
            _mensajes.put(array2);
            //Iniciar thread de procesamiento de mensajes de inicio
            _initThread = new Thread(this);
            datos = _configuracion;
            System.out.println(datos._maxJugadores);
            _arrayPanel = new JPanel[datos._maxJugadores];
            _arrayFrame = new Frame[datos._maxJugadores];
            _arrayPanelJuego = new PanelBase[datos._maxJugadores];
            _arrayMainThread = new Thread[datos._maxJugadores];
            _arrayJuegoAbs = new JuegoAbstracto[datos._maxJugadores];
            _arrayModCom = new ModuloComunicaciones[datos._maxJugadores];
            /*for (int i=0;i<datos._maxJugadores;i++){
            _arrayPanel[i].setVisible(false);
            _arrayFrame[i].setVisible(false);
            }*/
            /*for (int j = 0; j < datos._maxJugadores; j++) {
            array3[0] = (Object) "incorporacion";
            array3[1] = true;
            _arrayFrame[j] = new Frame();
            _arrayPanel[j] = new JPanel();
            _arrayFrame[j].setTitle("Jugador " + (j + 1));
            _mensajes.put(array3);
            }*/
            _arrayJuegoAbs[0] = _juegoAbs;
            _arrayModCom[0] = _modCom;
        /*for (int j = 1; j < datos._maxJugadores; j++) {
        _arrayModCom[j] = new ModuloComunicaciones(this,j);
        String name = "Jugador " + (j + 1)+"0";
        _arrayJuegoAbs[j] = (JuegoAbstracto) c.getConstructors()[0].newInstance(_arrayModCom[j], name, rutaFile);
        System.out.println("Nombre real:"+_arrayJuegoAbs[j].getJugadores().dameMiNombre());
        }*/
        } catch (Exception ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
        _modJuego = m = new ModuloJuegos(_configuracion, _arrayJuegoAbs);
        _controlador = new Controlador(servidor, _configuracion, _arrayModCom, m);
    /*for (int i=0;i<datos._maxJugadores;i++)
    _arrayModCom[i].setControlador(_controlador);*/
    }

    @Override
    public void aceptarMensaje(Object arg0, String arg1) {
        for (int i = 0; i < _configuracion._maxJugadores; i++) {
            _arrayModCom[i].enviarMensaje((Mensaje) arg0);
        }
    }

    @Override
    public void run() {
        String comando;
        int jugadores = 0;
        Object[] mensaje;
        try {
            while (true) {
                mensaje = _mensajes.take();
                comando = (String) mensaje[0];
                if (comando.equals("creacion")) {
                    _idPartida = 1;
                    if (_idPartida < 0) {
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error al crear la partida. Espera un poco y vuelve a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(-1);
                    } else {
                        JPanel _panelCarg;
                        //Mostrar formulario de "cargando"
                        _panelCarg = new PanelCargando();
                        _panelCarg.setVisible(true);
                        _panelCarg.setBounds(0, 0, 800, 600);
                        getContentPane().add(_panelCarg, java.awt.BorderLayout.CENTER);
                        getContentPane().remove(_panelCarg);
                        _panelCarg.setVisible(false);
                    }
                } else if (comando.equals("incorporacion")) {
                    _arrayFrame[jugadores] = new Frame();
                    _arrayPanel[jugadores] = new JPanel();

                    if (jugadores > 0) {
                        _arrayModCom[jugadores] = new ModuloComunicaciones(this, jugadores);
                    }
                    //Comprobar si la incorporacion ha sido correcta
                    if ((Boolean) mensaje[1] == false) {
                        System.out.println("error");
                        System.exit(-1);
                    } else {
                        _arrayModCom[jugadores].setControlador(_controlador);
                        String name;
                        if (jugadores > 0) {
                            name = "Jugador " + (jugadores + 1) + "0";
                        } else {
                            name = "Jugador " + (jugadores + 1) + "1";
                        }
                        _arrayJuegoAbs[jugadores] = (JuegoAbstracto) _c.getConstructors()[0].newInstance(_arrayModCom[jugadores], name, rutaFile);
                        _arrayPanelJuego[jugadores] = _arrayJuegoAbs[jugadores].damePanelJuego();
                        _arrayFrame[jugadores].setTitle("Jugador " + (jugadores + 1));
                        _arrayMainThread[jugadores] = new Thread(_arrayJuegoAbs[jugadores]);
                        //Mostrar juego
                        _arrayPanel[jugadores].setBounds(((jugadores % 2) * 300), ((jugadores / 2) * 200 + 50), 800, 450);
                        _arrayFrame[jugadores].setBounds(((jugadores % 2) * 300), ((jugadores / 2) * 200 + 50), 800, 450);
                        _arrayPanelJuego[jugadores].setBounds(((jugadores % 2) * 300), ((jugadores / 2) * 200 + 50), 800, 450);
                        if (jugadores > 0) {
                            _arrayModCom[jugadores].setControlador(_controlador);
                        }

                        for (int i = 0; i < jugadores; i++) {
                            _arrayPanelJuego[i].setVisible(true);
                            _arrayPanel[i].setVisible(true);
                            _arrayFrame[i].setVisible(true);
                        }
                        _arrayPanelJuego[jugadores].setVisible(true);
                        _arrayPanel[jugadores].add(_arrayPanelJuego[jugadores], java.awt.BorderLayout.CENTER);
                        _arrayPanel[jugadores].setVisible(true);
                        _arrayFrame[jugadores].add(_arrayPanel[jugadores], java.awt.BorderLayout.CENTER);
                        _arrayFrame[jugadores].setVisible(true);
                        //Iniciar thread juego
                        _arrayMainThread[jugadores].setName("Thread Jugador " + jugadores);
                        _arrayMainThread[jugadores].start();
                        _arrayPanel[jugadores].setVisible(true);
                        _arrayFrame[jugadores].setVisible(true);
                        boolean anadirCliente = _controlador.getPartida().anadirCliente(jugadores, false);
                        if (jugadores < _configuracion._maxJugadores - 1) {
                            jugadores++;
                        }
                    }
                } else if (comando.equals("transmision")) {
                    MensajePartida p;
                    _mensaje = "transmision";
                    if (!(mensaje[1] instanceof MensajePartida)) {
                        continue;
                    }
                    p = (MensajePartida) mensaje[1];
                    _controlador.getPartida().aceptarDatos(0, p);
                } else if (comando.equals("salida.virtual")) {
                    for (int i = 0; i < _configuracion._maxJugadores; i++) {
                        _arrayModCom[i].desconectar();
                    }
                    _sem.release();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    @Override
    public JPanel getPanel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ModuloComunicaciones[] getModulo() {
        return _arrayModCom;
    }

    public DatosPartida getDatos() {
        return _configuracion;
    }

    public String getMensaje() {
        return _mensaje;
    }
}
