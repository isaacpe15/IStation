/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IStation.ProbadorOffline;

import IStation.Modulos.IModulo;
import IStation.Modulos.Log.Servidor.IModuloLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Victor Senovilla
 */
public class ModuloLog implements IModulo,IModuloLog {
    FileOutputStream _ficheroLog; 
    PrintStream _escritor;
    Calendar _fecha;
    
    /**
     * 
     * Crea una instancia del Modulo de Log
     * @throws java.io.IOException : Si no se puede crear/abrir el archivo de log
     */
    public ModuloLog() 
    {
        try {
            File carpeta;

            _fecha = java.util.Calendar.getInstance();
            carpeta = new File("./Logs");

            if (!carpeta.exists()) {
                carpeta.mkdir();
            }
            creaArchivoLog();
            escribirInformacion(this, "\n\n*****************\nNueva ejecucion\n*****************\n\n");
        } catch (IOException ex) {
            Logger.getLogger(ModuloLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * Crea un nuevo fichero de Log para la fecha actual o abre el existente
     * @throws java.io.IOException : Si hay error al abrir/crear el Log
     */
    private void creaArchivoLog() throws IOException{
        File archivo= new File(String.format("./Logs/%1$tY%1$tm%1$te.txt", _fecha));
        if(!archivo.exists())
        {
            archivo.createNewFile();
        }
        if(_ficheroLog!=null)
            _ficheroLog.close();
        _ficheroLog=new FileOutputStream(archivo,true);
        _escritor=new PrintStream(_ficheroLog); 
    }
    
    
    /**
     * Salva el estado actual del modulo para poder ser reinstanciado despues
     * @return Estado actual del modulo
     */
    public Object salvarEstado() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Acaba la ejecucion del modulo liberando los recursos utilizados
     */
    public void terminarModulo()
    {
       synchronized (this)
       {
            try 
            {
                _ficheroLog.close();
            } 
            catch (IOException ex) 
            {
               //Aqui si peta no nos importa
            }
       }
    }
    
   
    /**
     * 
     * Escribe una linea de informacion en el Log diario
     * @param llamador : Objeto que invoca al metodo
     * @param mensaje : Texto a escribir
     */
    public synchronized void escribirInformacion(Object llamador,String mensaje)
    {

            String llam;
            Calendar fechaAhora= Calendar.getInstance();
            String[] traza;
            if((_fecha.get(Calendar.DAY_OF_YEAR)!=fechaAhora.get(Calendar.DAY_OF_YEAR))||
                (_fecha.get(Calendar.YEAR)!=fechaAhora.get(Calendar.YEAR)))
                {
                try 
                {
                    _fecha = fechaAhora;
                    creaArchivoLog();
                } 
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                }
            if(llamador!=null)
                llam=llamador.getClass().getSimpleName();
            else
                llam="Desconocido";
            traza=dameInfoTraza(new Throwable());
            _escritor.printf("[%1$tH:%1$tM] (%2$s:%3$s[%4$s]) : %5$s\r\n", fechaAhora,
                    extractSimpleClassName(traza[0]),traza[1],traza[2], mensaje);
            _escritor.flush();
            System.err.printf("[%1$tH:%1$tM] (%2$s:%3$s[%4$s]) : %5$s\r\n", fechaAhora,
                    extractSimpleClassName(traza[0]),traza[1],traza[2], mensaje);
            System.err.flush();
  
    }

    /**
     * 
     * Escribe una linea de error en el Log diario
     * @param llamador : Objeto que invoca al método
     * @param mensaje : Texto de error a escribir
     */
    public void escribirError (Object llamador, String mensaje)
    {
        escribirInformacion(llamador, "[ERROR] "+ mensaje);
    }
    
    /**
     * 
     * Escribe una excepcion en el Log diario
     * @param llamador : Objeto que invoca al método
     * @param ex : Excepcion ocurrida
     * @param mensaje : Texto de error a escribir
     */
     public void escribirExcepcion (Object llamador, Exception ex, String mensaje)
    {
        String error=ex.getClass().getSimpleName()+" ("+ex.getMessage()+") ";
        for(StackTraceElement s: ex.getStackTrace()){
            error=error+s.toString()+" \r\n ";
        }
        
        if (error.endsWith("| "))
           error=error.substring(0,error.length()-4);
        escribirError(llamador, mensaje+" -> \r\n"+error); 
        //Falta mandar mail al Victor Senovilla
    }
     
    public static String extractSimpleClassName (String fullClassName)
    {
        if ((null == fullClassName) || ("".equals (fullClassName)))
            return "";
        int lastDot = fullClassName.lastIndexOf ('.');
        if (0 > lastDot)
            return fullClassName;
        
        return fullClassName.substring (++lastDot);
    }
     
    private String[] dameInfoTraza(Throwable t)
    {
        int i;
        StackTraceElement[] ste = t.getStackTrace();
        
        i=1;
        
        if (ste[2].getMethodName().equals("escribirExcepcion"))
            i=3;
        else if (ste[1].getMethodName().equals("escribirError"))
            i=2;
        return new String[] {ste[i].getClassName(),ste[i].getMethodName(),String.valueOf(ste[i].getLineNumber())};
        //return new String[] {ste[i].getClassName(),ste[i].getMethodName(),ste[i].getMethodName()};
        
        
    }

    public void escribirInformacion(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void escribirError(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void escribirExcepcion(Exception arg0, String arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void escribirInformacion(String arg0, int arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void escribirError(String arg0, int arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void escribirExcepcion(Exception arg0, String arg1, int arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
