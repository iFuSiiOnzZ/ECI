package elchapuzasinformatico.com.eci.Utilities.Cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import elchapuzasinformatico.com.eci.Eci.Models.Constants;
import elchapuzasinformatico.com.eci.Utilities.Utilities;

/**
 * Created by AnDrEi AJ on 06/07/2015.
 */
public class BitmapMemCache
{
    /** */
    private static BitmapMemCache m_Instance = null;

    /**
     *
     */
    private BitmapMemCache()
    {
    }

    /**
     *
     * @return
     */
    public static BitmapMemCache getInstance()
    {
        if (m_Instance == null) m_Instance = new BitmapMemCache();
        return m_Instance;
    }

    public Bitmap get(String l_Path2File)
    {
        String l_HashFile = Utilities.string2MD5(l_Path2File);

        File l_File = new File(Constants.CACHE_PATH + l_HashFile);
        if(!l_File.exists()) return null;

        BitmapFactory.Options l_BitmapOptions = new BitmapFactory.Options();
        l_BitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;

        try
        {
            return BitmapFactory.decodeStream(new FileInputStream(l_File), null, l_BitmapOptions);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void put(String l_FileName, Bitmap l_Bitmap)
    {
        String l_HashFile = Utilities.string2MD5(l_FileName);
        File l_File = new File(Constants.CACHE_PATH + l_HashFile);

        try
        {
            FileOutputStream l_OutPutStream = new FileOutputStream(l_File);
            ByteArrayOutputStream l_BiteArrayStream = new ByteArrayOutputStream();

            l_Bitmap.compress(Bitmap.CompressFormat.PNG, 100, l_BiteArrayStream);
            byte[] l_ByteArray = l_BiteArrayStream.toByteArray();

            l_OutPutStream.write(l_ByteArray);

            l_BiteArrayStream.close();
            l_OutPutStream.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
