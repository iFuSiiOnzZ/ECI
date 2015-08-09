package elchapuzasinformatico.com.eci.Utilities.Network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;

/**
 * Created by AnDrEi AJ on 24/05/2015.
 *
 * Manifest.xml
 *      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 *      <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
 *      <uses-permission android:name="android.permission.INTERNET" />
 */
public class NetworkManager
{
    /**
     * Nos dice si hay conexion a Internet.
     * @param l_Context contexto en el cual se ejecuta.
     * @return verdadero si hay conexion a Internet o falso en caso contrario.
     */
    public static boolean isNetworkAvailable(Context l_Context)
    {
        ConnectivityManager l_ConnectivityManager = (ConnectivityManager) l_Context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo l_NetworkInfo = l_ConnectivityManager.getActiveNetworkInfo();

        return l_NetworkInfo != null && l_NetworkInfo.isConnected();
    }

    /**
     * Nos dice el tipo de conexion a Internet.
     * @param l_Context contexto en el cual se ejecuta.
     * @return -1 si no hay conectividad disponible, sino ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE, etc.
     */
    public static int getNetworkType(Context l_Context)
    {
        ConnectivityManager l_ConnectivityManager = (ConnectivityManager) l_Context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo l_NetworkInfo = l_ConnectivityManager.getActiveNetworkInfo();

        return (l_NetworkInfo == null) ? -1 : l_NetworkInfo.getType();
    }

    /**
     * Nos deveulve una cadena con el tipo de conexion a Internet.
     * @param l_Context contexto en el cual se ejecuta.
     * @return null si no hay conectividad disponible o el nombre del tipo de conexion.
     */
    public static String getNetworkTypeName(Context l_Context)
    {
        ConnectivityManager l_ConnectivityManager = (ConnectivityManager) l_Context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo l_NetworkInfo = l_ConnectivityManager.getActiveNetworkInfo();

        return (l_NetworkInfo == null) ? null : l_NetworkInfo.getTypeName();
    }
}
