package IStation.ProbadorOffline;

import IStation.Modulos.Juegos.Compartido.DatosPartida;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Aplicacion extends javax.swing.JFrame {
    
    Vista _vision;
    private static Class _claseDelJuego;
    private static String _rutaServidor = new String();
    private static String _rutaCliente = new String();
    public ModuloJuegos _modJueg;
    private File _fileCliente;
    private File _fileServidor;
    private File _fileDatos;
    private JPanel _panelFondo = new JPanel();
    private int _jugadores = 0;
    public DatosPartida _datos = new DatosPartida() {
        @Override
        public String getNombreJuego() {
            return "Juego guay";
        }
    };

    public Aplicacion() {
        initComponents();
        terminar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                terminarMouseClicked(evt);
            }
        });
        anadirJugador.setEnabled(false);
        this.setTitle("PROBADOR DE JUEGOS OFFLINE . . . . . . . . . . . . . by IStation");
        getContentPane().setBackground(new java.awt.Color(0,0,0));
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.gif")).getImage());
        Icon imgBoton = new ImageIcon(getClass().getResource("/imagenes/dice.gif"));
        crearPartida.setIcon(imgBoton);
        Icon imgBoton2 = new ImageIcon(getClass().getResource("/imagenes/anibobobo1.gif"));
        anadirJugador.setIcon(imgBoton2);
        Icon imgBoton3 = new ImageIcon(getClass().getResource("/imagenes/matar.gif"));
        terminar.setIcon(imgBoton3);
        Icon imgBoton4 = new ImageIcon(getClass().getResource("/imagenes/logo.jpg"));
        titulo.setIcon(imgBoton4);
        titulo.setText("");
        this.setSize(1000,550);
        estado.setText("sin partida");
        jugadores.setText("jugadores conectados : 0");
        terminar.setVisible(false);
        /*JPanel panelFondo = new panelFondo();
        panelFondo.setVisible(true);
        add(panelFondo);
        pack();*/
    }
   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        labelCliente = new javax.swing.JLabel();
        rootCliente = new javax.swing.JTextField();
        titulo = new javax.swing.JLabel();
        crearPartida = new javax.swing.JButton();
        labelServidor = new javax.swing.JLabel();
        rootServidor = new javax.swing.JTextField();
        examinarCliente = new javax.swing.JButton();
        elegirServidor = new javax.swing.JButton();
        anadirJugador = new javax.swing.JButton();
        labelEstado = new javax.swing.JLabel();
        jugadores = new javax.swing.JLabel();
        estado = new javax.swing.JTextField();
        terminar = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(5, 5, 5, 5));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setForeground(new java.awt.Color(0, 204, 204));
        setResizable(false);

        labelCliente.setBackground(new java.awt.Color(0, 0, 0));
        labelCliente.setFont(new java.awt.Font("Blackadder ITC", 0, 24));
        labelCliente.setForeground(new java.awt.Color(0, 204, 204));
        labelCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCliente.setText("Introduce la ruta del juego (cliente)");
        labelCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        rootCliente.setBackground(new java.awt.Color(0, 204, 204));
        rootCliente.setEditable(false);
        rootCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootClienteActionPerformed(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Blackadder ITC", 0, 48));
        titulo.setForeground(new java.awt.Color(255, 0, 0));
        titulo.setText("Probador de Juegos Offline");

        crearPartida.setBackground(new java.awt.Color(255, 255, 102));
        crearPartida.setText("           Crear Partida");
        crearPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearPartidaMouseClicked(evt);
            }
        });

        labelServidor.setBackground(new java.awt.Color(0, 0, 0));
        labelServidor.setFont(new java.awt.Font("Blackadder ITC", 0, 24));
        labelServidor.setForeground(new java.awt.Color(0, 204, 204));
        labelServidor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelServidor.setText("Introduce la ruta del juego (servidor)");
        labelServidor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        rootServidor.setBackground(new java.awt.Color(0, 204, 204));
        rootServidor.setEditable(false);

        examinarCliente.setBackground(new java.awt.Color(255, 0, 0));
        examinarCliente.setForeground(new java.awt.Color(255, 255, 255));
        examinarCliente.setText("Examinar");
        examinarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarClienteActionPerformed(evt);
            }
        });

        elegirServidor.setBackground(new java.awt.Color(255, 0, 0));
        elegirServidor.setForeground(new java.awt.Color(255, 255, 255));
        elegirServidor.setText("Examinar");
        elegirServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elegirServidorActionPerformed(evt);
            }
        });

        anadirJugador.setBackground(new java.awt.Color(255, 255, 102));
        anadirJugador.setText("             Añadir Jugador");
        anadirJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anadirJugadorMouseClicked(evt);
            }
        });

        labelEstado.setBackground(new java.awt.Color(0, 0, 0));
        labelEstado.setForeground(new java.awt.Color(0, 204, 204));
        labelEstado.setText("Estado de la partida");

        jugadores.setBackground(new java.awt.Color(0, 0, 0));
        jugadores.setForeground(new java.awt.Color(0, 204, 204));
        jugadores.setText("Jugadores conectados");

        estado.setBackground(new java.awt.Color(204, 0, 0));
        estado.setEditable(false);
        estado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        terminar.setBackground(new java.awt.Color(255, 255, 102));
        terminar.setText("Terminar");
        terminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
 	terminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                terminarMouseClicked(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(labelEstado)
                                .addGap(43, 43, 43)
                                .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(crearPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelCliente)
                                        .addComponent(labelServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rootCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                            .addComponent(rootServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(examinarCliente)
                                            .addComponent(elegirServidor)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(jugadores)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                        .addComponent(terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1076, 1076, 1076))
                            .addComponent(anadirJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(titulo)
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rootCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinarCliente)
                    .addComponent(labelCliente))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelServidor)
                    .addComponent(rootServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elegirServidor))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crearPartida)
                    .addComponent(anadirJugador))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEstado)
                            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jugadores)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearPartidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearPartidaMouseClicked
        try {
            jugadores.setVisible(true);
            estado.setText(" OK ");
            estado.setBackground(new java.awt.Color(0, 255, 0));
            JarFile aJar = new JarFile(_fileServidor);
            Manifest manifest = aJar.getManifest();
            Map map = manifest.getEntries();
            Attributes att = manifest.getMainAttributes();
            _vision=new Vista(_fileCliente,_fileServidor,att,_datos,_modJueg,this);
            _vision._initThread.start();
            terminar.setVisible(true);
            anadirJugador.setEnabled(true);
        }
        catch (IOException ex) {
            System.out.println("Error relacionado con el jar");
        }
}//GEN-LAST:event_crearPartidaMouseClicked

    private void examinarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarClienteActionPerformed
       JFileChooser elegir = new JFileChooser();
       elegir.setVisible(true);
       elegir.setCurrentDirectory(new File("..\\portal\\cliente\\juegos"));
       elegir.setFileFilter(new FileNameExtensionFilter("Archivos jar","jar" ));
       int returnVal = elegir.showOpenDialog(elegir);
       if ((_fileCliente = elegir.getSelectedFile()) != null) rootCliente.setText(_fileCliente.toString());
}//GEN-LAST:event_examinarClienteActionPerformed

    private void elegirServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirServidorActionPerformed
       JFileChooser elegir2 = new JFileChooser();
       elegir2.setVisible(true);
       elegir2.setCurrentDirectory(new File("..\\Binarios\\Servidor\\Juegos\\"));
       elegir2.setFileFilter(new FileNameExtensionFilter("Archivos jar","jar" ));
       int returnVal2 = elegir2.showOpenDialog(elegir2);
       if ((_fileServidor = elegir2.getSelectedFile()) != null) rootServidor.setText(_fileServidor.toString());
}//GEN-LAST:event_elegirServidorActionPerformed

    private void rootClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rootClienteActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_rootClienteActionPerformed

    private void anadirJugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anadirJugadorMouseClicked
       try {
            if (_jugadores < _vision._configuracion._maxJugadores) {
            
                _jugadores++;
                Object[] array3 = new Object[2];
                array3[0] = (Object) "incorporacion";
                array3[1] = true;
                _vision._mensajes.put(array3);
            jugadores.setText("jugadores conectados : " + _jugadores);//GEN-LAST:event_anadirJugadorMouseClicked
            }
            }catch (Exception exception) {
        }
       
        
}      
    private void terminarMouseClicked(java.awt.event.MouseEvent evt) {
            jugadores.setVisible(false);
            estado.setText("sin partida");
            estado.setBackground(new java.awt.Color(255, 0, 0));
            jugadores.setText("jugadores conectados : " + 0); 
            rootCliente.setText("");
            rootServidor.setText("");
            for (int i=0; i<_jugadores;i++){
                _vision._arrayFrame[i].removeAll();
                _vision._arrayFrame[i].dispose();
            }
            _vision.repaint();
            _vision.stop();
            _jugadores = 0;
            anadirJugador.setEnabled(false);
            try {
            terminar.setVisible(false);
            _modJueg.partidaIniciada(1,false);
            _modJueg.eliminaPartida(1);
            for (int i=0;i<_vision._arrayJuegoAbs.length;i++)
                _vision._arrayJuegoAbs[i].partidaTerminada();
        } catch (Exception exception) {
        }


     }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Aplicacion().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anadirJugador;
    private javax.swing.JButton crearPartida;
    private javax.swing.JButton elegirServidor;
    private javax.swing.JTextField estado;
    private javax.swing.JButton examinarCliente;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jugadores;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelServidor;
    private javax.swing.JTextField rootCliente;
    private javax.swing.JTextField rootServidor;
    private javax.swing.JButton terminar;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
    public void setModuloJuegos(ModuloJuegos mod){
        _modJueg=mod;
    }
    public static String getRutaServidor(){
        return _rutaServidor;
    }
    
    public static String getRutaCliente(){
        return _rutaCliente;
    }
    
    public static Class getClase(){
        return _claseDelJuego;
    }
}
