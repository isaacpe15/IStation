/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IStation.ProbadorOffline;

import IStation.Modulos.Juegos.Servidor.ILogger;
import IStation.Modulos.Log.Servidor.IModuloLog;

/**
 *
 * @author usuario_local
 */
public class Logger implements ILogger{

     private final IModuloLog _mLog;

    /**
     * Constructora de un Logger
     * @param mLog 
     */
    public Logger(IModuloLog mLog) {
        _mLog = mLog;
    }

 

    public void escribirInformacion(String mensaje) {
        //_mLog.escribirInformacion(null, mensaje);
    }

    public void escribirError(String mensaje) {
        //_mLog.escribirError(null, mensaje);
    }

    public void escribirExcepcion(Exception ex, String mensaje) {
        //_mLog.escribirExcepcion(null, ex, mensaje);
    }

}
