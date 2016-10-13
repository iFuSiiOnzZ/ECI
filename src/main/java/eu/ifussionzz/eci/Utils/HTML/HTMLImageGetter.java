package eu.ifussionzz.eci.Utils.HTML;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import eu.ifussionzz.eci.Utils.Network;
import android.os.AsyncTask;

import android.text.Html.ImageGetter;
import android.content.Context;

import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * http://stackoverflow.com/questions/7424512/android-html-imagegetter-as-asynctask
 */
public class HTMLImageGetter implements ImageGetter
{
    Context m_Context = null;
    TextView m_View = null;

    public HTMLImageGetter(TextView v, Context c)
    {
        m_Context = c;
        m_View = v;
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

        private Drawable GetDrawable(String ImgUrl) throws IOException
        {
            InputStream URLStrem = Network.GetInputStream(ImgUrl);
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
