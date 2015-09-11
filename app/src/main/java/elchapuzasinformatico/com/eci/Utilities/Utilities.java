package elchapuzasinformatico.com.eci.Utilities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 31/05/2015.
 */
public class Utilities
{
    /**
     *
     * @param l_Context
     * @return
     */
    public static int getStatusBarHeight(Context l_Context)
    {
        int resourceId = l_Context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? l_Context.getResources().getDimensionPixelSize(resourceId) : 0;
    }

    public static int getNavigationBarHeight(Context context, int orientation)
    {
        int resourceId = context.getResources().getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }

    /**
     *
     * @param l_URL
     * @param l_Context
     */
    public static void goToURL(String l_URL, Context l_Context)
    {
        Intent l_Intent = new Intent(Intent.ACTION_VIEW, Uri.parse(l_URL));
        l_Context.startActivity(l_Intent);
    }

    /**
     *
     * @param l_Content
     * @param l_Subject
     * @param l_Context
     */
    public static void shareVia(String l_Subject, String l_Content, Context l_Context)
    {
        Intent l_SharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        l_SharingIntent.setType("text/plain");

        l_SharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, l_Content);
        l_SharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, l_Subject);
        l_Context.startActivity(Intent.createChooser(l_SharingIntent, l_Context.getString(R.string.common_sharevia)));
    }

    /**
     *
     * @param l_Label
     * @param l_Content
     * @param l_Context
     */
    public static void copy2Clipboard(String l_Label, String l_Content, Context l_Context)
    {
        ClipboardManager l_Clipboard = (ClipboardManager) l_Context.getSystemService(l_Context.CLIPBOARD_SERVICE);
        ClipData l_Clip = ClipData.newPlainText(l_Label, l_Content);
        l_Clipboard.setPrimaryClip(l_Clip);
    }

    /**
     *
     * @param l_Str
     * @return
     */
    public static String string2MD5(String l_Str)
    {
        String l_HashText = null;

        try
        {
            MessageDigest l_MD5 = MessageDigest.getInstance("MD5");

            l_MD5.reset(); l_MD5.update(l_Str.getBytes());
            byte[] l_Digest = l_MD5.digest();

            l_HashText = new BigInteger(1, l_Digest).toString(16);
            while(l_HashText.length() < 32 ) l_HashText = "0" + l_HashText;
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return  l_HashText;
    }
}
