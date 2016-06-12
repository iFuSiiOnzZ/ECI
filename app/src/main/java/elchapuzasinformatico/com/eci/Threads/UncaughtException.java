package elchapuzasinformatico.com.eci.Threads;

import android.app.Activity;
import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AnDrEi AJ on 27/07/2015.
 * Modificado: 11/06/2016 23:10     Comentado el codigo de captura de excepciones
 */
public class UncaughtException implements Thread.UncaughtExceptionHandler
{
    private Activity m_App = null;
    private Thread.UncaughtExceptionHandler m_Handle = null;

    public UncaughtException(Activity l_App)
    {
        m_App = l_App;
        m_Handle = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override public void uncaughtException(Thread Thread, Throwable Exception)
    {
        /*StackTraceElement Exceptions[] = Exception.getStackTrace();
        String ThreadInfo = Thread.toString();
        String Error = Exception.toString() + "\n";

        Error += "----------------[STACK TRACE]----------------\n" + ThreadInfo + "\n";
        for(int i = 0; i < Exceptions.length; i++) Error += Exceptions[i].toString() + "\n";
        Error += "---------------------------------------------\n\n";

        Error += "-------------------[CAUSE]-------------------\n";
        Throwable l_Cause = Exception.getCause();
        if(l_Cause != null)
        {
            Exceptions = l_Cause.getStackTrace();
            for(int i = 0; i < Exceptions.length; i++) Error += Exceptions[i].toString() + "\n";
        }

        Error += "---------------------------------------------\n\n\n\n\n";

        try
        {
            FileWriter ErrorFile = new FileWriter("/sdcard/ECIExceptions.txt", true);
            ErrorFile.write(Error); ErrorFile.flush(); ErrorFile.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }*/

        m_Handle.uncaughtException(Thread, Exception);
    }
}
