package elchapuzasinformatico.com.eci.Utilities.Network;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.net.URLConnection;
import java.net.URL;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 */
public class DownloadUtilities
{
    /**
     *
     * @param l_DownloadUrl
     * @return
     */
    public static Bitmap getImageFromNetwork(String l_DownloadUrl)
    {
        try
        {
            InputStream l_InputStream = (InputStream) new URL(l_DownloadUrl).getContent();
            Bitmap l_Bitmap = BitmapFactory.decodeStream(l_InputStream);

            l_InputStream.close();
            return l_Bitmap;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param l_DownloadUrl
     * @return
     */
    public static InputStream getInputStreamFromNetwork(String l_DownloadUrl)
    {
        try
        {
            URL l_URL = new URL(l_DownloadUrl);
            URLConnection l_URLCon = l_URL.openConnection();

            return l_URLCon.getInputStream();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param l_DownloadUrl
     * @return
     */
    public static String getStringFromNetwork(String l_DownloadUrl)
    {
        InputStream l_InputStream = getInputStreamFromNetwork(l_DownloadUrl);
        if(l_InputStream == null) return null;

        BufferedReader l_BufferReader = new BufferedReader(new InputStreamReader(l_InputStream));
        String l_Text = ""; String l_Line = null;

        try
        {
            while ((l_Line = l_BufferReader.readLine()) != null) l_Text += l_Line;
            l_BufferReader.close(); l_InputStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return l_Text;
    }
}
