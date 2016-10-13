package eu.ifussionzz.eci.Utils.Cache;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import java.security.NoSuchAlgorithmException;

import eu.ifussionzz.eci.Data.Constants;
import eu.ifussionzz.eci.Utils.Utils;


public class BitmapMEMCache
{
    private static BitmapMEMCache m_Instance = null;

    private BitmapMEMCache()
    {
    }


    public static synchronized BitmapMEMCache getInstance()
    {
        if (m_Instance == null) m_Instance = new BitmapMEMCache();
        return m_Instance;
    }

    public Bitmap get(String Path2File) throws NoSuchAlgorithmException, FileNotFoundException
    {
        File File = new File(Constants.CACHE_PATH + Utils.MD5(Path2File));
        if(!File.exists()) return null;

        BitmapFactory.Options BitmapOptions = new BitmapFactory.Options();
        BitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;

        return BitmapFactory.decodeStream(new FileInputStream(File), null, BitmapOptions);
    }

    public synchronized void put(String FileName, Bitmap Bitmap) throws NoSuchAlgorithmException, IOException
    {
        File File = new File(Constants.CACHE_PATH + Utils.MD5(FileName));
        if(false) if(File.exists()) return;

        FileOutputStream OutPutStream = new FileOutputStream(File);
        ByteArrayOutputStream BiteArrayStream = new ByteArrayOutputStream();

        Bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, BiteArrayStream);
        OutPutStream.write(BiteArrayStream.toByteArray());

        BiteArrayStream.close();
        OutPutStream.close();
    }
}
