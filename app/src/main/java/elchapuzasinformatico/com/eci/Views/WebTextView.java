package elchapuzasinformatico.com.eci.Views;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * Created by AnDrEi AJ on 27/05/2015.
 */
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

    public void setURL(String l_URL)
    {
        new loadImageFromInternet().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, l_URL);
    }

    private class loadImageFromInternet extends AsyncTask<String, Void, String>
    {
        protected String doInBackground(String... l_Urls)
        {
            try
            {
                InputStreamReader l_InputStream = new InputStreamReader(new URL(l_Urls[0]).openConnection().getInputStream());
                BufferedReader l_BufferReader = new BufferedReader(l_InputStream);
                String l_Text = ""; String l_Line = null;

                while ((l_Line = l_BufferReader.readLine()) != null) l_Text += l_Line;
                l_BufferReader.close(); l_InputStream.close();

                return l_Text;
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String l_Text)
        {
            setText(l_Text);
            invalidate();
        }
    }
}
