package elchapuzasinformatico.com.eci;

import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.LinearLayout;
import android.view.MenuInflater;
import android.widget.Toast;

import android.view.MenuItem;
import android.view.Menu;
import android.view.View;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

import elchapuzasinformatico.com.eci.Eci.AsynkTask.getNewsWithId;
import elchapuzasinformatico.com.eci.Threads.UncaughtException;
import elchapuzasinformatico.com.eci.Eci.Models.PostDetails;
import elchapuzasinformatico.com.eci.Utilities.Utilities;
import elchapuzasinformatico.com.eci.Models.ICallBack;

/**
 * Created by AnDrEi AJ on 31/05/2015.
 * Modificado: 10/06/2016 20:28     Quitado los comentarios
 *                                  Quitado el prefijo de las variables locales
 */
public class NewsActivity extends AppCompatActivity implements View.OnClickListener, ICallBack
{
    /** */
    private PostDetails m_NewsDetails = null;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtException(this));
        setContentView(R.layout.post_page);

        Toolbar ToolBar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout TextLayout = (LinearLayout) findViewById(R.id.id_post_text);

        setSupportActionBar(ToolBar); getSupportActionBar().setTitle(getIntent().getStringExtra("postTitle"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ToolBar.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);
        TextLayout.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);

        getNewsWithId PostPage = new getNewsWithId(this, getIntent().getIntExtra("postId", -1));
        PostPage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override protected void onActivityResult(int RequestCode, int ResultCode, Intent Data)
    {

    }

    @Override public boolean onOptionsItemSelected(MenuItem Item)
    {
        if(Item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }

        if(Item.getItemId() == R.id.id_copy_link)
        {
            Utilities.copy2Clipboard("", m_NewsDetails.m_Url, this);
            Toast.makeText(this, getString(R.string.toas_url2clipboard), Toast.LENGTH_SHORT).show();
        }

        if(Item.getItemId() == R.id.id_share_post)
        {
            Utilities.shareVia("", m_NewsDetails.m_Url, this);
        }

        if(Item.getItemId() == R.id.id_open_brower)
        {
            Utilities.goToURL(m_NewsDetails.m_Url, this);
        }

        return super.onOptionsItemSelected(Item);
    }

    @Override public boolean onCreateOptionsMenu(Menu Menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post_options_menu, Menu);

        return true;
    }

    @Override public void onClick(View View)
    {
        String YoutbeID = (String) View.getTag();
        if(YoutbeID == null) return;

        Intent videoIntent = YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyAE1S6bP0pGTktCcRw4Dk-r_AgeD92E798", YoutbeID, 0, true, false);
        try { startActivityForResult(videoIntent, 0); } catch(ActivityNotFoundException e) { e.printStackTrace(); }
    }

    @Override public void CallBack(Object Object, int ResultCode)
    {
        m_NewsDetails = (PostDetails) Object;
    }
}
