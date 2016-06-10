package elchapuzasinformatico.com.eci;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Menu;

import elchapuzasinformatico.com.eci.Adapters.InfinitePageAdapter;
import elchapuzasinformatico.com.eci.Eci.SharedPref.SettingsPrefs;
import elchapuzasinformatico.com.eci.Threads.UncaughtException;
import elchapuzasinformatico.com.eci.Utilities.Utilities;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 * Modificado: 10/06/2016 20:28     Quitado los comentarios
 *                                  Quitado el prefijo de las variables locales
 */
public class MainActivity extends AppCompatActivity
{
    private long m_LastTimePressBack = System.currentTimeMillis();
    private long m_TimeToExitBackPress = 2000;

    @Override protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);


        Thread.setDefaultUncaughtExceptionHandler(new UncaughtException(this));
        setContentView(R.layout.main_activity);

        Toolbar ToolBar = (Toolbar) findViewById(R.id.toolbar);
        ViewPager ViewPager = (ViewPager) findViewById(R.id.id_news_info);

        ToolBar.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(ToolBar); getSupportActionBar().setTitle("");
        InfinitePageAdapter InfAdapter = new InfinitePageAdapter(this, getSupportActionBar());

        getSupportActionBar().setTitle("Noticias - 1");
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        ViewPager.setOffscreenPageLimit(new SettingsPrefs(this).GetPreloadNumPages());
        ViewPager.addOnPageChangeListener(InfAdapter);
        ViewPager.setAdapter(InfAdapter);
    }

    @Override protected void onPostCreate(Bundle SavedInstanceState)
    {
        super.onPostCreate(SavedInstanceState);
    }

    @Override public boolean onCreateOptionsMenu(Menu Menu)
    {
        getMenuInflater().inflate(R.menu.news_options_menu, Menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem MenuItem)
    {
        if (MenuItem.getItemId() == R.id.id_settings)
        {
            Toast.makeText(this, "Hoy no, mañana (001)", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (MenuItem.getItemId() == R.id.id_search)
        {
            Toast.makeText(this, "Mañana tampoco, pasado mañana (002)", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(MenuItem);
    }

    @Override public boolean onKeyDown(int KeyCode, KeyEvent Event)
    {
        if (KeyCode == KeyEvent.KEYCODE_BACK)
        {
            if(m_LastTimePressBack + m_TimeToExitBackPress > System.currentTimeMillis()) finish();
            else Toast.makeText(this, "Pulse otra vez para salir", Toast.LENGTH_SHORT).show();

            m_LastTimePressBack = System.currentTimeMillis();
            return true;
        }

        return super.onKeyDown(KeyCode, Event);
    }
}
