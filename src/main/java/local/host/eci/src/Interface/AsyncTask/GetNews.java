package local.host.eci.src.Interface.AsyncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import local.host.eci.src.Interface.View.WebImageView;
import local.host.eci.src.Interface.View.WrapContentHorizontalScrollView;
import local.host.eci.src.Utils.HTML.HTMLImageGetter;
import local.host.eci.src.Utils.HTML.ExtraHTMLTags;

import local.host.eci.src.Data.NewsDetails;
import local.host.eci.src.Data.Constants;
import local.host.eci.R;

import local.host.eci.src.Utils.Network;

public class GetNews extends AsyncTask<Integer, Void, Vector<NewsDetails>>
{
    private ProgressDialog m_ProgressDialog = null;
    private Context m_Context = null;

    public GetNews(Context c)
    {
        m_Context = c;
    }

    @Override protected void onPreExecute()
    {
        m_ProgressDialog = new ProgressDialog(m_Context);
        m_ProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        m_ProgressDialog.setIndeterminate(false);
        m_ProgressDialog.setCancelable(false);

        m_ProgressDialog.setMessage("");
        m_ProgressDialog.show();
    }

    @Override protected Vector<NewsDetails> doInBackground(Integer... params)
    {
        Vector<NewsDetails> NewsDetails = new Vector<>();
        Vector<String> PageData = new Vector<>();
        int NumPage = 1;

        try
        {
            do
            {
                PageData.add(Network.GetText(Constants.GetPost(params[0], NumPage++)));
            } while(PageData.get(PageData.size() - 1).contains("Seguir leyendo"));

            Gson Gson = new Gson();

            for(int i = 0; i < PageData.size(); i++)
            {
                JSONObject JSOnRootNode = new JSONObject(PageData.get(i));
                if(!JSOnRootNode.getString("status").equalsIgnoreCase("ok")) return null;

                NewsDetails Details = Gson.fromJson(JSOnRootNode.getJSONObject("post").toString(), NewsDetails.class);
                NewsDetails.add(Details);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return NewsDetails;
    }

    protected void onPostExecute(Vector<NewsDetails> Result)
    {
        m_ProgressDialog.dismiss();
        if(Result.size() == 0) return;

        StringBuilder StringBuilder = new StringBuilder();
        ArrayList<String> YtbVideos = new ArrayList<>();

        for(int i = 0; i < Result.size(); i++)
        {
            StringBuilder.append(Result.get(i).m_Content);
        }

        Pattern pattern = Pattern.compile("(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})");
        Matcher matcher = pattern.matcher(StringBuilder.toString());

        while(matcher.find())
        {
            String vId = matcher.group(1);
            if(!YtbVideos.contains(vId)) YtbVideos.add(vId);
        }

        LinearLayout MainContainer = (LinearLayout) ((Activity) m_Context).findViewById(R.id.lyt_news_details);
        TextView PostText = new TextView(m_Context); PostText.setPadding(16, 16, 16, 0);

        PostText.setText(Html.fromHtml(StringBuilder.toString(), new HTMLImageGetter(PostText, m_Context), new ExtraHTMLTags()));
        PostText.setMovementMethod(LinkMovementMethod.getInstance());

        ////
        WrapContentHorizontalScrollView YtbVideoScrollContainer = new WrapContentHorizontalScrollView(m_Context);
        YtbVideoScrollContainer.setHorizontalScrollBarEnabled(false);
        YtbVideoScrollContainer.setVerticalScrollBarEnabled(false);

        YtbVideoScrollContainer.setPadding(12, 0, 12, 16);
        YtbVideoScrollContainer.setFillViewport(true);

        LinearLayout YtbVideoContainer = new LinearLayout(m_Context);
        YtbVideoContainer.setOrientation(LinearLayout.HORIZONTAL);

        if(YtbVideos.size() == 1)
        {
            WebImageView WebImage = new WebImageView(m_Context);
            WebImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            WebImage.setId(1024);
            WebImage.setPadding(4, 0, 4, 0);

            WebImage.setAdjustViewBounds(true);
            WebImage.setScaleType(ImageView.ScaleType.FIT_XY);

            WebImage.setTag(YtbVideos.get(0));
            WebImage.setOnClickListener((View.OnClickListener) m_Context);
            WebImage.SetURL("http://img.youtube.com/vi/" + YtbVideos.get(0) + "/0.jpg");


            ImageView BtnPlay = new ImageView(m_Context);
            BtnPlay.setImageResource(R.drawable.ic_btn_play);

            RelativeLayout Overlay = new RelativeLayout(m_Context);
            Overlay.addView(WebImage); Overlay.addView(BtnPlay);


            YtbVideoContainer.addView(Overlay);
        }
        else for(int i = 0; i < YtbVideos.size(); i++)
        {
            WebImageView WebImage = new WebImageView(m_Context);

            WebImage.setId(1024);
            WebImage.setPadding(4, 0, 4, 0);
            WebImage.setTag(YtbVideos.get(i));

            WebImage.setOnClickListener((View.OnClickListener) m_Context);
            WebImage.SetURL("http://img.youtube.com/vi/" + YtbVideos.get(i) + "/0.jpg");

            ImageView BtnPlay = new ImageView(m_Context);
            BtnPlay.setImageResource(R.drawable.ic_btn_play);

            RelativeLayout Overlay = new RelativeLayout(m_Context);
            Overlay.addView(WebImage); Overlay.addView(BtnPlay);

            YtbVideoContainer.addView(Overlay);
        }

        YtbVideoScrollContainer.addView(YtbVideoContainer);

        ////
        MainContainer.addView(PostText);
        MainContainer.addView(YtbVideoScrollContainer);
    }
}
