package eu.ifussionzz.eci.Utils;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import android.net.ConnectivityManager;
import android.content.Context;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import android.net.NetworkInfo;
import java.net.URLConnection;
import java.net.URL;


public class Network
{
    public static NetworkInfo GetNetwotrkInfo(Context c)
    {
        ConnectivityManager ConManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        return ConManager.getActiveNetworkInfo();
    }

    public static boolean isNetworkAvailable(Context c)
    {
        NetworkInfo NetInfo = GetNetwotrkInfo(c);
        return NetInfo != null && NetInfo.isConnected();
    }

    public static int GetNetworkType(Context c)
    {
        NetworkInfo NetInfo = GetNetwotrkInfo(c);
        return (NetInfo == null) ? -1 : NetInfo.getType();
    }

    public static String GetNetworkTypeName(Context c)
    {
        NetworkInfo NetInfo = GetNetwotrkInfo(c);
        return (NetInfo == null) ? null : NetInfo.getTypeName();
    }

    public static Bitmap GetImage(String DownloadUrl) throws IOException
    {
        InputStream InputStream = (InputStream) new URL(DownloadUrl).getContent();
        Bitmap BitmapImage = BitmapFactory.decodeStream(InputStream);

        InputStream.close();
        return BitmapImage;
    }

    public static InputStream GetInputStream(String DownloadUrl) throws IOException
    {
        URL URI = new URL(DownloadUrl);
        URLConnection URLCon = URI.openConnection();

        return URLCon.getInputStream();
    }

    public static String GetText(String DownloadUrl) throws IOException
    {
        InputStream InputStream = GetInputStream(DownloadUrl);
        if(InputStream == null) return null;

        BufferedReader BufferReader = new BufferedReader(new InputStreamReader(InputStream));
        String Text = "", Line = null;

        while ((Line = BufferReader.readLine()) != null) Text += Line;
        BufferReader.close(); InputStream.close();

        return Text;
    }
}
