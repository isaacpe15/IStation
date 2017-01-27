package IStation.ProbadorOffline;

import IStation.Cliente.Base.JuegoAbstracto;
import IStation.Modulos.Juegos.Compartido.DatosPartida;
import IStation.Modulos.Juegos.Compartido.MensajePartida;
import IStation.Modulos.Juegos.Servidor.ILogger;
import IStation.Modulos.Juegos.Servidor.IModuloJuegos;
import IStation.Modulos.Log.Servidor.IModuloLog;

public class ModuloJuegos implements IModuloJuegos{

    private JuegoAbstracto[] _clientes;
    private IModuloLog _modLog = new ModuloLog() ;
    private ILogger _logger ;
    
    public ModuloJuegos(DatosPartida datos, JuegoAbstracto[] arrayClientes) {
        _clientes = arrayClientes;
        _logger = new Logger(_modLog);
    }

    @Override
    public void enviarMensaje(int idCliente, Object msg) {
        MensajePartida mp = (MensajePartida)msg;
       _clientes[idCliente].aceptarMensaje(mp.getDatos(), mp.getEvento());
    }

    @Override
    public void sumarPuntosJugador(int ptos, int idCliente) {}

    @Override
    public int consultarPuntosJugador(int idCliente) {
        return 0;
    }

    @Override
    public String consultarNombreJugador(int idCliente) {
        return "Jugador "+(idCliente+1);
    }

    public void eliminaPartida(int idPartida) {}

    public void partidaIniciada(int idPartida, boolean iniciada) {}

    public IModuloLog getModuloLog() {
        return _modLog;
    }

    public ILogger getLogger() {
        return _logger;
    }
}
