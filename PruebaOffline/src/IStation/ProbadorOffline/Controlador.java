package IStation.ProbadorOffline;

import IStation.Modulos.Juegos.Compartido.DatosPartida;
import IStation.Modulos.Juegos.Servidor.IInicioPartida;
import IStation.Modulos.Juegos.Servidor.Partida;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Controlador {
    
    private HashMap<String, IInicioPartida> _iniciadores;
    private IInicioPartida _iniciadorPartida;
    private String _nombreJuego;
    private ModuloJuegos _moduloJ;
    private ModuloComunicaciones[] _moduloC;
    private DatosPartida _datos;
    private String _mensaje;
    public Partida _partida;
    
    public Controlador(File ruta,DatosPartida datos, ModuloComunicaciones[] modulo, ModuloJuegos moduloJ){
        
        _datos = datos;
        _moduloJ = moduloJ;
        _moduloC = modulo;
            
        JarFile aJar;
        URL[] array = new URL[1];
        try {
            array[0] = ruta.toURI().toURL();
            URLClassLoader loader = new URLClassLoader(array);
            aJar = new JarFile(ruta);
            Manifest manifest = aJar.getManifest();
            Map map = manifest.getEntries();
            Attributes att = manifest.getMainAttributes();
            String clase = att.getValue("ClaseInicio").toString();
            _nombreJuego = att.getValue("NombreJuego").toString();
            Class iniciador = loader.loadClass(clase);
            _iniciadorPartida = (IInicioPartida) iniciador.newInstance();
        }
        catch (Exception e) {
            System.out.println("Problema relacionado con el jar");
        }
        _partida = _iniciadorPartida.iniciarPartida(datos, moduloJ, 0);
        System.out.println("Se ha cargado el juego " + _nombreJuego);
        } 
    
    public DatosPartida getDatos(){
        return _datos;
    }
    
    public String getMensaje(){
        return _mensaje;
    }
    
    public void setMensaje(String msm){
        _mensaje = msm;
    }
    public Partida getPartida(){
        return _partida;
    }
    
}
