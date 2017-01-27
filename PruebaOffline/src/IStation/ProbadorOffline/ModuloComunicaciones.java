package IStation.ProbadorOffline;

import IStation.Cliente.Modulos.IModuloComunicaciones;
import IStation.Modulos.Comunicaciones.Mensaje;
import IStation.Modulos.Juegos.Compartido.MensajePartida;

public class ModuloComunicaciones implements IModuloComunicaciones {

    private Vista _vista;
    private Controlador _controlador;
    private int _idCliente;

    public ModuloComunicaciones(Vista vision,int id) {
        _idCliente = id;
        _vista = vision;
    }

    void desconectar() {}

    public void enviarMensaje(Object _k, String string, int PROTOCOLO_PARTIDA) {}  

    public void enviarMensaje(Mensaje arg0) {
        MensajePartida mp = (MensajePartida) arg0.getDatos();
        _controlador.getPartida().aceptarDatos(_idCliente, mp);
    }
    
    public void setVista(Vista v) {
        _vista = v;
    }

    public void setControlador(Controlador c) {
        _controlador = c;
    }
}
