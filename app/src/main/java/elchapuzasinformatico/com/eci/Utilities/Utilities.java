package elchapuzasinformatico.com.eci.Utilities;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.ClipData;

import android.content.res.Configuration;
import android.content.Context;
import android.content.Intent;

import android.view.inputmethod.InputMethodManager;
import elchapuzasinformatico.com.eci.R;
import android.net.Uri;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;


/**
 * Created by AnDrEi AJ on 31/05/2015.
 * Modificado: 11/06/2016 20:28     Quitado los comentarios
 *                                  Quitado el prefijo de las variables locales
 *
 *                                  Funcion para forzar el cierre del teclado
 *                                  Funcion para forzar la aparicion del teclado
 *
 *             12/06/2016 10:20     La ocultacion del teclado no requiere ninguna vista
 *
 */
public class Utilities
{
    public static int getStatusBarHeight(Context Context)
    {
        int ResourceId = Context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return ResourceId > 0 ? Context.getResources().getDimensionPixelSize(ResourceId) : 0;
    }

    public static int getNavigationBarHeight(Context Context, int Orientation)
    {
        int ResourceId = Context.getResources().getIdentifier(Orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
        return ResourceId > 0 ? Context.getResources().getDimensionPixelSize(ResourceId) : 0;
    }

    public static void goToURL(String URL, Context Context)
    {
        Intent GoTo = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        Context.startActivity(GoTo);
    }

    public static void shareVia(String Subject, String Content, Context Context)
    {
        Intent SharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        SharingIntent.setType("text/plain");

        SharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Content);
        SharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Subject);
        Context.startActivity(Intent.createChooser(SharingIntent, Context.getString(R.string.common_sharevia)));
    }

    public static void copy2Clipboard(String Label, String Content, Context Context)
    {
        ClipboardManager Clipboard = (ClipboardManager) Context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData l_Clip = ClipData.newPlainText(Label, Content);
        Clipboard.setPrimaryClip(l_Clip);
    }

    public static  void OpenKeyboard(Context Context)
    {
        InputMethodManager InputManager = (InputMethodManager) Context.getSystemService(Context.INPUT_METHOD_SERVICE);
        InputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static  void CloseKeyboard(Context Context)
    {
        InputMethodManager InputManager = (InputMethodManager) Context.getSystemService(Context.INPUT_METHOD_SERVICE);
        InputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static String string2MD5(String PlainString)
    {
        String HashText = null;

        try
        {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");

            MD5.reset(); MD5.update(PlainString.getBytes());
            byte[] Digest = MD5.digest();

            HashText = new BigInteger(1, Digest).toString(16);
            while(HashText.length() < 32 ) HashText = "0" + HashText;
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return  HashText;
    }
}
