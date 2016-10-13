package eu.ifussionzz.eci.Interface.View;

import android.util.AttributeSet;
import android.content.Context;
import java.net.URL;

import android.widget.TextView;
import android.os.AsyncTask;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WebTextView extends TextView
{
    public WebTextView(Context context)
    {
        super(context);
    }

    public WebTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public WebTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void SetURL(String l_URL)
    {
        new loadImageFromInternet().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, l_URL);
    }

    private class loadImageFromInternet extends AsyncTask<String, Void, String>
    {
        protected String doInBackground(String... Urls)
        {
            try
            {
                InputStreamReader InputStream = new InputStreamReader(new URL(Urls[0]).openConnection().getInputStream());
                BufferedReader BufferReader = new BufferedReader(InputStream);
                String Text = "", Line = null;

                while ((Line = BufferReader.readLine()) != null) Text += Line;
                BufferReader.close(); InputStream.close();

                return Text;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String Text)
        {
            setText(Text);
            invalidate();
        }
    }
}
