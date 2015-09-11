package elchapuzasinformatico.com.eci;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

import elchapuzasinformatico.com.eci.Eci.AsynkTask.getNewsWithId;
import elchapuzasinformatico.com.eci.Eci.Models.PostDetails;
import elchapuzasinformatico.com.eci.Models.ICallBack;
import elchapuzasinformatico.com.eci.Threads.UncaughtException;
import elchapuzasinformatico.com.eci.Utilities.Utilities;

/**
 * Created by AnDrEi AJ on 31/05/2015.
 */
public class NewsActivity extends AppCompatActivity implements View.OnClickListener, ICallBack
{
    /** */
    private PostDetails m_NewsDetails = null;

    /**
     *
     * @param savedInstanceState
     */
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtException(this));
        setContentView(R.layout.post_page);

        Toolbar l_ToolBar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout l_TextLayout = (LinearLayout) findViewById(R.id.id_post_text);

        setSupportActionBar(l_ToolBar); getSupportActionBar().setTitle(getIntent().getStringExtra("postTitle"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        l_ToolBar.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);
        l_TextLayout.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);

        getNewsWithId l_PostPage = new getNewsWithId(this, getIntent().getIntExtra("postId", -1));
        l_PostPage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }

    /**
     *
     * @param l_Item
     * @return
     */
    @Override public boolean onOptionsItemSelected(MenuItem l_Item)
    {
        if(l_Item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }

        if(l_Item.getItemId() == R.id.id_copy_link)
        {
            Utilities.copy2Clipboard("", m_NewsDetails.m_Url, this);
            Toast.makeText(this, getString(R.string.toas_url2clipboard), Toast.LENGTH_SHORT).show();
        }

        if(l_Item.getItemId() == R.id.id_share_post)
        {
            Utilities.shareVia("", m_NewsDetails.m_Url, this);
        }

        if(l_Item.getItemId() == R.id.id_open_brower)
        {
            Utilities.goToURL(m_NewsDetails.m_Url, this);
        }

        return super.onOptionsItemSelected(l_Item);
    }

    /**
     *
     * @param l_Menu
     * @return
     */
    @Override public boolean onCreateOptionsMenu(Menu l_Menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post_options_menu, l_Menu);

        return true;
    }

    /**
     *
     * @param l_View
     */
    @Override public void onClick(View l_View)
    {
        String l_YoutbeID = (String) l_View.getTag();
        if(l_YoutbeID == null) return;

        Intent videoIntent = YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyAE1S6bP0pGTktCcRw4Dk-r_AgeD92E798", l_YoutbeID, 0, true, false);
        try { startActivityForResult(videoIntent, 0); } catch(ActivityNotFoundException e) { e.printStackTrace(); }
    }

    @Override public void CallBack(Object l_Object, int l_ResultCode)
    {
        m_NewsDetails = (PostDetails) l_Object;
    }
}
