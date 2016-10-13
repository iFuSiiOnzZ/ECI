package eu.ifussionzz.eci;

import eu.ifussionzz.eci.Interface.View.Adapter.InfinitePagerAdapter;
import eu.ifussionzz.eci.Utils.Utils;
import local.host.eci.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

import android.text.method.LinkMovementMethod;
import android.content.DialogInterface;

import android.view.ViewGroup;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;

import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.text.Html;


public class Activity_02 extends AppCompatActivity implements ViewPager.OnPageChangeListener, Runnable, View.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener
{
    private long m_LastTimePressBack = System.currentTimeMillis();
    private static long m_TimeToExitBackPress = 2000;
    private SearchView m_SView = null;

    @Override protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_02);

        Toolbar ToolBar = (Toolbar) findViewById(R.id.lyt_toolbar);
        ViewPager ViewPager = (ViewPager) findViewById(R.id.lyt_news_pager);

        ToolBar.setPadding(0, Utils.GetStatusBarHeight(this), 0, 0);
        setSupportActionBar(ToolBar); getSupportActionBar().setTitle(getString(R.string.activity_02_toolbar));

        ViewPager.setOffscreenPageLimit(5);
        ViewPager.addOnPageChangeListener(this);
        ViewPager.setAdapter(new InfinitePagerAdapter(this));

        findViewById(R.id.lyt_page_indicator).setOnClickListener(this);
        new Thread(this).start();
    }

    @Override public void onClick(View v)
    {
    }

    @Override public boolean onQueryTextSubmit(String query)
    {
        m_SView.setQueryHint("");
        m_SView.setIconified(true);
        m_SView.onActionViewCollapsed();

        if(query.length() < 3)
        {
            Toast.makeText(this, getString(R.string.toast_minsearch_length), Toast.LENGTH_SHORT).show();
            return false;
        }

        Intent SearchIntent = new Intent(this, Activity_04.class);
        String QuerySearch = query.trim();

        SearchIntent.putExtra("SEARCH_QUERY", QuerySearch);
        startActivity(SearchIntent);

        return false;
    }

    @Override public boolean onQueryTextChange(String newText)
    {
        return false;
    }

    @Override public boolean onClose()
    {
        TextView SearchText = (TextView) m_SView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        SearchText.setText("");

        m_SView.setQueryHint("");
        return false;
    }

    @Override public boolean onCreateOptionsMenu(Menu Menu)
    {
        getMenuInflater().inflate(R.menu.activity_02, Menu);
        m_SView = (SearchView) Menu.findItem(R.id.menu_search).getActionView();

        m_SView.setOnSearchClickListener(this);
        m_SView.setOnQueryTextListener(this);
        m_SView.setOnCloseListener(this);

        return true;
    }

    @Override public void onResume()
    {
        super.onResume();
        findViewById(R.id.lyt_toolbar).getBackground().setAlpha(255);
    }

    @Override public boolean onOptionsItemSelected(MenuItem Item)
    {
        if (Item.getItemId() == R.id.menu_about)
        {
            ShowAbout();
            return true;
        }
        else if(Item.getItemId() == R.id.menu_settings)
        {
            Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(Item);
    }

    @Override public boolean onKeyDown(int KeyCode, KeyEvent Event)
    {
        if (KeyCode == KeyEvent.KEYCODE_BACK)
        {
            if(m_LastTimePressBack + m_TimeToExitBackPress > System.currentTimeMillis()) finish();
            else Toast.makeText(this, getString(R.string.toast_backpress_exit), Toast.LENGTH_SHORT).show();

            m_LastTimePressBack = System.currentTimeMillis();
            return true;
        }

        return super.onKeyDown(KeyCode, Event);
    }

    @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
    }

    @Override public void onPageSelected(int position)
    {
        TextView PageIndicator = (TextView) findViewById(R.id.lyt_page_number);
        PageIndicator.setText("" + (position + 1));
    }

    @Override public void onPageScrollStateChanged(int state)
    {
    }

    @Override public void run()
    {
        ViewPager ViewPager = (ViewPager) findViewById(R.id.lyt_news_pager);
        while(ViewPager.getChildCount() == 0) try{ Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        final ProgressBar ProgessBar = (ProgressBar) findViewById(R.id.lyt_progess);
        runOnUiThread(new Runnable() { @Override public void run() { ProgessBar.setVisibility(View.GONE); }});
    }

    private Boolean isNumber(String Val)
    {
        byte[] nVal = Val.getBytes();
        for(int i = 0; i < nVal.length; ++i) if(nVal[i] < '0' || nVal[i] > '9') return false;

        return true;
    }

    private void ShowAbout()
    {
        AlertDialog.Builder AlertBuilder = new AlertDialog.Builder(this);

        String Message = "<strong>Versión</strong>: 1.0.0<br />";
        Message += "Andrei Jianu<br /><br />";

        Message += "<strong>Disclaimer</strong>: Esta aplicación hace uso del plugin <a href=\"https://es.wordpress.org/plugins/json-api/\">JSON-API</a> para recuperar contenido a través HTTP.";
        Message += "<br /><br />";

        Message += "<strong>Código fuente</strong>:<br />";
        Message += "<a href=\"https://github.com/iFuSiiOnzZ/wpReader\">https://github.com/iFuSiiOnzZ/wpReader</a><br /><br />";

        TextView TextAbout = new TextView(this);
        TextAbout.setText(Html.fromHtml(Message));

        TextAbout.setPadding(75, 25, 25, 25);
        TextAbout.setMovementMethod(LinkMovementMethod.getInstance());

        AlertBuilder.setView(TextAbout);
        AlertBuilder.setTitle(R.string.menu_about);

        AlertBuilder.setPositiveButton(R.string.button_accept, new DialogInterface.OnClickListener()
        {
            @Override public void onClick(DialogInterface dialog, int which)
            {
            }
        });

        AlertBuilder.setIcon(R.drawable.ic_launcher);
        AlertBuilder.setCancelable(false);

        AlertBuilder.create().show();
    }
}
