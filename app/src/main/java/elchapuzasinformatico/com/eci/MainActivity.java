package elchapuzasinformatico.com.eci;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import elchapuzasinformatico.com.eci.Adapters.InfinitePageAdapter;
import elchapuzasinformatico.com.eci.Eci.SettingsPage;
import elchapuzasinformatico.com.eci.Eci.SharedPref.SettingsPrefs;
import elchapuzasinformatico.com.eci.Threads.UncaughtException;
import elchapuzasinformatico.com.eci.Utilities.Utilities;
import elchapuzasinformatico.com.eci.Eci.NewsPage;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 */
public class MainActivity extends AppCompatActivity
{
    private long m_LastTimePressBack = System.currentTimeMillis();
    private long m_TimeToExitBackPress = 2000;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtException(this));
        setContentView(R.layout.main_activity);

        Toolbar l_ToolBar = (Toolbar) findViewById(R.id.toolbar);
        ViewPager l_ViewPager = (ViewPager) findViewById(R.id.id_news_info);

        l_ToolBar.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(l_ToolBar); getSupportActionBar().setTitle("");

        getSupportActionBar().setTitle("Noticias");
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        l_ViewPager.setOffscreenPageLimit(new SettingsPrefs(this).getPreloadNumPages());
        l_ViewPager.setAdapter(new InfinitePageAdapter(this));
    }

    @Override protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
    }

    @Override public boolean onCreateOptionsMenu(Menu l_Menu)
    {
        getMenuInflater().inflate(R.menu.news_options_menu, l_Menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem l_MenuItem)
    {
        if (l_MenuItem.getItemId() == R.id.id_settings)
        {
            Toast.makeText(this, "No Implementado (001)", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (l_MenuItem.getItemId() == R.id.id_search)
        {
            Toast.makeText(this, "No Implementado (002)", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(l_MenuItem);
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if(m_LastTimePressBack + m_TimeToExitBackPress > System.currentTimeMillis()) finish();
            else Toast.makeText(this, "Pulse otra vez para salir", Toast.LENGTH_SHORT).show();

            m_LastTimePressBack = System.currentTimeMillis();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
