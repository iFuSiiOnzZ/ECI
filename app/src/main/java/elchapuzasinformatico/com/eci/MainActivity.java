package elchapuzasinformatico.com.eci;

import android.content.DialogInterface;
import android.content.Intent;

import android.view.ViewGroup;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

import android.widget.EditText;
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
 *
 *             11/06/2016 11:15     Dialago para la busqueda
 */
public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener
{
    private long m_LastTimePressBack = System.currentTimeMillis();
    private long m_TimeToExitBackPress = 2000;
    private EditText m_SearchEditText = null;

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

        m_SearchEditText = new EditText(this);
        m_SearchEditText.setId(0);
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

        if (MenuItem.getItemId() == R.id.id_refresh)
        {
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

        if (MenuItem.getItemId() == R.id.id_search)
        {
            AlertDialog.Builder AlBuilder = new AlertDialog.Builder(this);
            AlBuilder.setView(m_SearchEditText);

            AlBuilder.setTitle("Buscar");
            AlBuilder.setMessage("");

            AlBuilder.setPositiveButton("Buscar", this);
            AlBuilder.setNegativeButton("Cancelar", this);

            AlBuilder.create().show();
            Utilities.OpenKeyboard(this);

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

    @Override public void onClick(DialogInterface Dialog, int Which)
    {
        Utilities.CloseKeyboard(this);
        ((ViewGroup)m_SearchEditText.getParent()).removeView(m_SearchEditText);

        if(Which != -1) return;
        Intent SearchIntent = new Intent(this, SearchActivity.class);

        SearchIntent.putExtra("SEARCH_QUERY", m_SearchEditText.getText().toString());
        startActivity(SearchIntent);
    }
}
