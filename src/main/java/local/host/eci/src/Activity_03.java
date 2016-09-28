package local.host.eci.src;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Menu;
import android.text.Html;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayerFragment;
import android.content.res.Configuration;

import local.host.eci.src.Interface.Listener.OnScrollViewListener;
import local.host.eci.src.Interface.AsyncTask.GetNews;
import local.host.eci.src.Interface.View.WebImageView;
import local.host.eci.src.Interface.View.ScrollView;

import local.host.eci.src.Utils.MyYoutubePlayer;
import local.host.eci.src.Utils.Utils;
import local.host.eci.R;

public class Activity_03 extends AppCompatActivity implements OnScrollViewListener, View.OnClickListener
{
    private static final float START_ALPHA = 0.0f;
    private static final float END_ALPHA = 1.0f;

    private MyYoutubePlayer m_YoutubePlayer = null;
    private Boolean m_PostLoaded = false;

    @Override protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_03);

        Toolbar ToolBar = (Toolbar) findViewById(R.id.lyt_toolbar);
        ToolBar.setPadding(0, Utils.GetStatusBarHeight(this), 0, 0);
        ToolBar.getBackground().setAlpha((int) (START_ALPHA * 255));

        setSupportActionBar(ToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebImageView HeaderImage = (WebImageView) findViewById(R.id.lyt_img_header);
        HeaderImage.SetURL(getIntent().getStringExtra("THUMBNAIL"));

        TextView PostTitie = (TextView) findViewById(R.id.post_title);
        PostTitie.setText(Html.fromHtml(getIntent().getStringExtra("TITLE")));

        ScrollView Scroll = (ScrollView) findViewById(R.id.lyt_scroll);
        Scroll.setOnScrollViewListener(this);

        YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.ytb_fragment);
        m_YoutubePlayer = new MyYoutubePlayer();

        youTubePlayerFragment.initialize("AIzaSyAE1S6bP0pGTktCcRw4Dk-r_AgeD92E798", m_YoutubePlayer);
    }

    @Override public boolean onCreateOptionsMenu(Menu Menu)
    {
        getMenuInflater().inflate(R.menu.activity_03, Menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem Item)
    {
        if (Item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        else if(Item.getItemId() == R.id.menu_copy)
        {
            Utils.Copy2Clipboard("", getIntent().getStringExtra("URL"), this);
            Toast.makeText(this, getString(R.string.toas_url2clipboard), Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (Item.getItemId() == R.id.menu_share)
        {
            Utils.ShareVia("", getIntent().getStringExtra("URL"), this);
            return true;
        }
        else if (Item.getItemId() == R.id.menu_browser)
        {
            Utils.GoToURL(getIntent().getStringExtra("URL"), this);
            return true;
        }

        return super.onOptionsItemSelected(Item);
    }

    @Override public void onWindowFocusChanged (boolean hasFocus)
    {
        if(m_PostLoaded) return;

        RelativeLayout.LayoutParams LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LayoutParams.setMargins(0, findViewById(R.id.lyt_img_header).getHeight(), 0, 0);

        LinearLayout ContainerLayout = (LinearLayout) findViewById(R.id.lyt_news_details);
        ContainerLayout.setLayoutParams(LayoutParams);

        new GetNews(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getIntent().getIntExtra("ID", -1));
        m_PostLoaded = true;
    }

    @Override public void onScrollChanged(ScrollView v, int l, int t, int oldl, int oldt)
    {
        int ImageHeight = findViewById(R.id.lyt_img_header).getHeight();
        int HeaderHeight = ImageHeight - getSupportActionBar().getHeight();

        float Ratio = (float) Math.min(Math.max(t, 0), HeaderHeight) / HeaderHeight;
        Ratio *= (END_ALPHA - START_ALPHA); Ratio += START_ALPHA / END_ALPHA;

        Toolbar ToolBar = (Toolbar) findViewById(R.id.lyt_toolbar);
        ToolBar.getBackground().setAlpha((int)(Ratio * 255));

        RelativeLayout.LayoutParams LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LayoutParams.setMargins(0, (int) (ImageHeight - Ratio * ImageHeight * 0.2f), 0, 0);

        LinearLayout ContainerLayout = (LinearLayout) findViewById(R.id.lyt_news_details);
        ContainerLayout.setLayoutParams(LayoutParams);
    }

    @Override public void onClick(View v)
    {
        if(v.getId() == 1024)
        {
            findViewById(R.id.ytb_frame).setVisibility(View.VISIBLE);
            String YtbID = (String) v.getTag();
            m_YoutubePlayer.Play(YtbID);
        }

        /*Intent videoIntent = YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyAE1S6bP0pGTktCcRw4Dk-r_AgeD92E798", YoutbeID, 0, true, false);
        try { startActivityForResult(videoIntent, 0); } catch(ActivityNotFoundException e) { e.printStackTrace(); }*/
    }

    @Override  public void onBackPressed()
    {
        if (m_YoutubePlayer.isFullScreen()) m_YoutubePlayer.SetFullScreen(false);
        else super.onBackPressed();
    }

    @Override public boolean onKeyDown(int KeyCode, KeyEvent Event)
    {
        if (KeyCode == KeyEvent.KEYCODE_BACK && m_YoutubePlayer != null && m_YoutubePlayer.isPlaying())
        {
            findViewById(R.id.ytb_frame).setVisibility(View.GONE);
            m_YoutubePlayer.Pause();
            return true;
        }
        else if (KeyCode == KeyEvent.KEYCODE_BACK && m_YoutubePlayer != null && m_YoutubePlayer.isFullScreen())
        {
            m_YoutubePlayer.SetFullScreen(false);
            return true;
        }

        return super.onKeyDown(KeyCode, Event);
    }

    @Override public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }
}
