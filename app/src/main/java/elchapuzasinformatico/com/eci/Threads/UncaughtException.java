package elchapuzasinformatico.com.eci.Threads;

import android.app.Activity;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AnDrEi AJ on 27/07/2015.
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

    @Override public void uncaughtException(Thread l_Thread, Throwable l_Exception)
    {
        StackTraceElement l_Exceptions[] = l_Exception.getStackTrace();
        String l_ThreadInfo = l_Thread.toString();
        String l_Error = l_Exception.toString() + "\n";

        l_Error += "----------------[STACK TRACE]----------------\n" + l_ThreadInfo + "\n";
        for(int i = 0; i < l_Exceptions.length; i++) l_Error += l_Exceptions[i].toString() + "\n";
        l_Error += "---------------------------------------------\n\n";

        l_Error += "-------------------[CAUSE]-------------------\n";
        Throwable l_Cause = l_Exception.getCause();
        if(l_Cause != null)
        {
            l_Exceptions = l_Cause.getStackTrace();
            for(int i = 0; i < l_Exceptions.length; i++) l_Error += l_Exceptions[i].toString() + "\n";
        }
        l_Error += "---------------------------------------------\n\n\n\n\n";

        try
        {
            FileWriter l_ErrorFile = new FileWriter("/sdcard/ECIExceptions.txt", true);
            l_ErrorFile.write(l_Error); l_ErrorFile.flush(); l_ErrorFile.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        m_Handle.uncaughtException(l_Thread, l_Exception);
    }
}
