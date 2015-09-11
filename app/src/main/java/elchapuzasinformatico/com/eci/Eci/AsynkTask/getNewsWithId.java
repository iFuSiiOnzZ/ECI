package elchapuzasinformatico.com.eci.Eci.AsynkTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import elchapuzasinformatico.com.eci.Eci.Models.PostDetails;
import elchapuzasinformatico.com.eci.Eci.Models.URLS;
import elchapuzasinformatico.com.eci.Models.ICallBack;
import elchapuzasinformatico.com.eci.R;
import elchapuzasinformatico.com.eci.Utilities.Html.ExtraHtmlTags;
import elchapuzasinformatico.com.eci.Utilities.Html.HtmlImageGetter;
import elchapuzasinformatico.com.eci.Utilities.Network.DownloadUtilities;
import elchapuzasinformatico.com.eci.Utilities.Utilities;
import elchapuzasinformatico.com.eci.Views.WebImageView;

/**
 * Created by AnDrEi AJ on 31/05/2015.
 */
public class getNewsWithId extends AsyncTask<Void, Void, PostDetails>
{
    private ProgressDialog m_ProgressDialog = null;
    private  PostDetails m_PostDetails = null;
    private Context m_Context = null;
    private int m_PostId = 0;

    /**
     * Constructor.
     * @param l_Context contexto de ejecucion.
     * @param l_PostId id del post a mostrar.
     */
    public getNewsWithId(Context l_Context, int l_PostId)
    {
        m_Context = l_Context;
        m_PostId = l_PostId;
    }

    /**
     * Carga el contenido de una noticias de forma asincrona.
     * @param params nada (no se usa).
     * @return estructura de datos con todos los detalles de la noticas.
     */
    @Override protected PostDetails doInBackground(Void... params)
    {
        ((Activity) m_Context).runOnUiThread(new Runnable() {@Override public void run() {m_ProgressDialog.setMessage(m_Context.getString(R.string.common_downloading));}});
        String l_JSonString = DownloadUtilities.getStringFromNetwork(URLS.getPost(m_PostId));

        Log.v("URL", URLS.getPost(m_PostId));

        if(l_JSonString == null) return null;
        PostDetails l_PostDetails = null;

        try
        {
            Gson l_Gson = new Gson();
            JSONObject l_Root = new JSONObject(l_JSonString);
            if(!l_Root.getString("status").equalsIgnoreCase("ok")) return null;

            ((Activity) m_Context).runOnUiThread(new Runnable() {@Override public void run() {m_ProgressDialog.setMessage(m_Context.getString(R.string.common_processing));}});
            l_PostDetails = l_Gson.fromJson(l_Root.getJSONObject("post").toString(), PostDetails.class);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return l_PostDetails;
    }

    /**
     * Muestra un spinner, para que el usuario tenga constancia de que esta pasando algo.
     */
    @Override protected void onPreExecute()
    {
        m_ProgressDialog = new ProgressDialog(m_Context);
        m_ProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        m_ProgressDialog.setIndeterminate(false);
        m_ProgressDialog.setCancelable(false);

        m_ProgressDialog.setMessage("");
        m_ProgressDialog.show();
    }

    /**
     * Pone toda la informacion en pantalla.
     * @param l_Result estructura de datos con todos los detalles de la noticas.
     */
    protected void onPostExecute(PostDetails l_Result)
    {
        if(l_Result == null)
        {
            m_ProgressDialog.dismiss();
            return;
        }

        // Se crear un TextView para poner la noticas.
        LinearLayout l_MainContainer = (LinearLayout) ((Activity) m_Context).findViewById(R.id.id_post_text);
        l_MainContainer.setPadding(16, 16, 16, Utilities.getNavigationBarHeight(m_Context, Configuration.ORIENTATION_PORTRAIT));

        // Se busca todos los videos de youtube.
        ArrayList<String> l_Videos = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})");
        Matcher matcher = pattern.matcher(l_Result.m_Content);

        while (matcher.find())
        {
            String vId = matcher.group(1);
            if(!l_Videos.contains(vId)) l_Videos.add(vId);
        }

        // Se pone el texto de la noticias.
        TextView l_NewsText = new TextView(m_Context);
        l_NewsText.setText(Html.fromHtml(l_Result.m_Content, new HtmlImageGetter(l_NewsText, m_Context), new ExtraHtmlTags()));
        l_NewsText.setMovementMethod(LinkMovementMethod.getInstance());
        l_MainContainer.addView(l_NewsText);

        // Se anaden los videos, si hay.
        HorizontalScrollView l_VideoScrollContainer = new HorizontalScrollView(m_Context);
        l_VideoScrollContainer.setHorizontalScrollBarEnabled(false);
        l_VideoScrollContainer.setVerticalScrollBarEnabled(false);
        l_VideoScrollContainer.setPadding(0, 0, 0, 32);
        
        LinearLayout l_VideoContainer = new LinearLayout(m_Context);
        l_VideoContainer.setOrientation(LinearLayout.HORIZONTAL);

        l_MainContainer.addView(l_VideoScrollContainer);
        l_VideoScrollContainer.addView(l_VideoContainer);

        for(int i = 0; i < l_Videos.size(); i++)
        {
            WebImageView l_Image = new WebImageView(m_Context);
            l_Image.setURL("http://img.youtube.com/vi/" + l_Videos.get(i) + "/0.jpg");

            l_Image.setTag(l_Videos.get(i));
            l_Image.setPadding(4, 0, 4, 0);
            l_Image.setOnClickListener((View.OnClickListener) m_Context);
            l_Image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            l_VideoContainer.addView(l_Image);
        }

        // Se ponen los comentarios de los uaurios.
        for(int i = 0; i < l_Result.m_Comments.size(); ++i)
        {
            View l_CommentView = LayoutInflater.from(m_Context).inflate(R.layout.post_comment, null);

            TextView l_Comment = (TextView) l_CommentView.findViewById(R.id.id_comment_content);
            TextView l_Author = (TextView) l_CommentView.findViewById(R.id.id_comment_author);
            TextView l_Replay = (TextView) l_CommentView.findViewById(R.id.id_comment_replay);

            l_Comment.setText(Html.fromHtml(l_Result.m_Comments.get(i).m_Content, new HtmlImageGetter(l_Comment, m_Context), null));
            l_Comment.setMovementMethod(LinkMovementMethod.getInstance());

            l_Author.setText(Html.fromHtml("<strong>" + l_Result.m_Comments.get(i).m_Author + "</strong>", new HtmlImageGetter(l_Comment, m_Context), null));
            l_Replay.setText(l_Result.m_Comments.get(i).m_ID + " : " +  l_Result.m_Comments.get(i).m_Parent);

            l_MainContainer.addView(l_CommentView);
        }

        m_ProgressDialog.dismiss();
        ((ICallBack) m_Context).CallBack(l_Result, 0);
    }
}
