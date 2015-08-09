package elchapuzasinformatico.com.eci.Threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AnDrEi AJ on 10/07/2015.
 */
public class ImageUtils extends AsyncTask<Void, Void, Bitmap>
{
    private String      m_URLString  = null;
    private ImageView   m_ImageView  = null;
    private boolean     m_RoundImage = false;

    public ImageUtils()
    {
    }

    public ImageUtils downloadFrom(String l_Url)
    {
        m_URLString = l_Url;
        return this;
    }

    public ImageUtils placeInto(ImageView l_View)
    {
        m_ImageView = l_View;
        return this;
    }

    public ImageUtils roundImage(boolean l_RoundImage)
    {
        m_RoundImage = l_RoundImage;
        return this;
    }

    @Override protected Bitmap doInBackground(Void... l_Params)
    {
        try
        {
            URL l_URL = new URL(m_URLString);
            HttpURLConnection l_Connection = (HttpURLConnection) l_URL.openConnection();

            l_Connection.setDoInput(true); l_Connection.connect();
            InputStream l_InputStream = l_Connection.getInputStream();

            return BitmapFactory.decodeStream(l_InputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override protected void onPostExecute(Bitmap l_Result)
    {
        if(l_Result == null) return;

        if(m_RoundImage) l_Result = RoundImage(l_Result);
        if(m_ImageView != null) m_ImageView.setImageBitmap(l_Result);
    }

    public Bitmap RoundImage(Bitmap l_Bitmap)
    {
        int l_Radius = Math.min(l_Bitmap.getWidth() / 2, l_Bitmap.getHeight() / 2);
        int l_Height = l_Bitmap.getHeight();
        int l_Width  = l_Bitmap.getWidth();

        Bitmap l_CircleBitmap = Bitmap.createBitmap(l_Width + 8, l_Height + 8, Bitmap.Config.ARGB_8888);
        Canvas l_Canvas       = new Canvas(l_CircleBitmap);
        Paint l_Paint         = new Paint();

        l_Paint.setAntiAlias(true);
        l_Paint.setStyle(Paint.Style.FILL);

        l_Canvas.drawARGB(0, 0, 0, 0);
        l_Canvas.drawCircle((l_Width / 2) + 4, (l_Height / 2) + 4, l_Radius, l_Paint);

        l_Paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        l_Canvas.drawBitmap(l_Bitmap, 4, 4, l_Paint);
        l_Paint.setXfermode(null);

        l_Paint.setStyle(Paint.Style.STROKE);
        l_Paint.setStrokeWidth(2.5f);

        l_Canvas.drawCircle((l_Width / 2) + 4, (l_Height / 2) + 4,l_Radius, l_Paint);
        return l_CircleBitmap;
    }
}
