package eu.ifussionzz.eci.Utils;

import android.content.res.Configuration;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.net.Uri;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;

public class Utils
{
    public static int GetStatusBarHeight(Context Context)
    {
        int ResourceId = Context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return ResourceId > 0 ? Context.getResources().getDimensionPixelSize(ResourceId) : 0;
    }

    public static int GetNavigationBarHeight(Context Context, int Orientation)
    {
        int ResourceId = Context.getResources().getIdentifier(Orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
        return ResourceId > 0 ? Context.getResources().getDimensionPixelSize(ResourceId) : 0;
    }

    public static Point GetScreenDimensions(Context Context)
    {
        WindowManager wm = (WindowManager) Context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        return size;
    }

    public static void GoToURL(String URL, Context Context)
    {
        Intent GoTo = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        Context.startActivity(GoTo);
    }

    public static void ShareVia(String Subject, String Content, Context Context)
    {
        Intent SharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        SharingIntent.setType("text/plain");

        SharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Content);
        SharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Subject);

        Context.startActivity(Intent.createChooser(SharingIntent, "> · · ·"));
    }

    public static void Copy2Clipboard(String Label, String Content, Context Context)
    {
        ClipboardManager Clipboard = (ClipboardManager) Context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData Clip = ClipData.newPlainText(Label, Content); Clipboard.setPrimaryClip(Clip);
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

    public static String MD5(String PlainString) throws NoSuchAlgorithmException
    {
        String HashText = null;
        MessageDigest MD5 = MessageDigest.getInstance("MD5");

        MD5.reset(); MD5.update(PlainString.getBytes());
        byte[] Digest = MD5.digest();

        HashText = new BigInteger(1, Digest).toString(16);
        while(HashText.length() < 32 ) HashText = "0" + HashText;

        return  HashText;
    }
}
