package elchapuzasinformatico.com.eci.Utilities.Html;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import elchapuzasinformatico.com.eci.Utilities.Network.DownloadUtilities;

/**
 * http://stackoverflow.com/questions/7424512/android-html-imagegetter-as-asynctask
 */
public class HtmlImageGetter implements ImageGetter
{
    Context m_Context = null;
    TextView m_View = null;

    public HtmlImageGetter(TextView l_View, Context l_Context)
    {
        m_Context = l_Context;
        m_View = l_View;
    }

    public Drawable getDrawable(String source)
    {
        UrlDrawable urlDrawable = new UrlDrawable();

        ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable);
        asyncTask.execute(source);

        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable>
    {
        UrlDrawable m_UrlDrawer = null;

        public ImageGetterAsyncTask(UrlDrawable InputDrawable)
        {
            m_UrlDrawer = InputDrawable;
        }

        private Drawable GetDrawable(String ImgUrl)
        {
            InputStream URLStrem = DownloadUtilities.getInputStreamFromNetwork(ImgUrl);
            Bitmap URLBitmap = BitmapFactory.decodeStream(URLStrem);

            WindowManager WNDManager = (WindowManager) m_Context.getSystemService(Context.WINDOW_SERVICE);
            Display DisplayInfo = WNDManager.getDefaultDisplay();

            Point WindowsSize = new Point(); DisplayInfo.getSize(WindowsSize);
            float ScaleFactor = ((float)WindowsSize.x / (float)URLBitmap.getWidth());

            Drawable DrawableImg = new BitmapDrawable(m_Context.getResources(), Bitmap.createScaledBitmap(URLBitmap, WindowsSize.x, Math.round(URLBitmap.getHeight() * ScaleFactor), false));
            DrawableImg.setBounds(0, 0, DrawableImg.getIntrinsicWidth(), DrawableImg.getIntrinsicHeight());

            return DrawableImg;
        }

        @Override protected Drawable doInBackground(String... Url)
        {
            try
            {
                return GetDrawable(Url[0]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            catch (OutOfMemoryError e)
            {
                e.printStackTrace();
            }

            System.gc();
            return null;
        }

        @Override protected void onPostExecute(Drawable drawable)
        {
            if(drawable == null) return;

            m_UrlDrawer.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            m_UrlDrawer.setDrawable(drawable);

            m_View.setMinimumHeight(m_View.getHeight() + drawable.getIntrinsicHeight());
            m_View.invalidate();

            m_View.setText(m_View.getText());
        }
    }

    public class UrlDrawable extends BitmapDrawable
    {
        protected Drawable m_Drawable = null;

        public void setDrawable(Drawable drawable)
        {
            m_Drawable = drawable;
        }

        @Override public void draw(Canvas canvas)
        {
            if(m_Drawable != null) m_Drawable.draw(canvas);
        }
    }
}
