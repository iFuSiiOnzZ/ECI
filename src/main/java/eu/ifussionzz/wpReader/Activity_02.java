package eu.ifussionzz.wpReader;

import eu.ifussionzz.wpReader.Data.Constants;
import eu.ifussionzz.wpReader.Data.DrawerItem;
import eu.ifussionzz.wpReader.Interface.Listener.OnClickListenerInterface;
import eu.ifussionzz.wpReader.Interface.Listener.OnClickListenerRecycleView;
import eu.ifussionzz.wpReader.Interface.View.Adapter.DrawerAdapter;
import eu.ifussionzz.wpReader.Interface.View.Adapter.InfinitePagerAdapter;
import eu.ifussionzz.wpReader.Utils.Utils;
import local.host.eci.R;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

import android.text.method.LinkMovementMethod;
import android.content.DialogInterface;

import android.util.Log;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;

import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.text.Html;

import java.util.ArrayList;


public class Activity_02 extends AppCompatActivity implements ViewPager.OnPageChangeListener, Runnable, View.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener, OnClickListenerInterface
{
    private long m_LastTimePressBack = System.currentTimeMillis();
    private static long m_TimeToExitBackPress = 2000;
    private SearchView m_SView = null;

    private ArrayList<DrawerItem> m_DrawerItems = new ArrayList<>();
    private ActionBarDrawerToggle m_Toggle = null;
    private DrawerLayout m_DrawerLayout = null;

    @Override protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_02);

        Toolbar ToolBar = (Toolbar) findViewById(R.id.lyt_toolbar);
        ViewPager ViewPager = (ViewPager) findViewById(R.id.lyt_news_pager);

        ToolBar.setPadding(0, Utils.GetStatusBarHeight(this), 0, 0);
        setSupportActionBar(ToolBar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        m_DrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        m_Toggle = new ActionBarDrawerToggle(this, m_DrawerLayout, R.string.open, R.string.close);

        m_Toggle.setDrawerIndicatorEnabled(true);
        m_DrawerLayout.addDrawerListener(m_Toggle);

        m_DrawerItems.add(new DrawerItem("", "", null));
        m_DrawerItems.add(new DrawerItem("AlwaysFromKeyboard", "http://www.alwaysfromkeyboard.es/", "http://www.alwaysfromkeyboard.es/wp-content/uploads/2016/09/logopeque3.png"));
        m_DrawerItems.add(new DrawerItem("ElChapuzasInformatico", "https://elchapuzasinformatico.com/", "https://elchapuzasinformatico.com/wp-content/uploads/2015/05/logo_el_chapuzas5.png"));

        Constants.BASE_URL = m_DrawerItems.get(1).URL;
        getSupportActionBar().setTitle(m_DrawerItems.get(1).Name);

        RecyclerView DrawerRecycleView = (RecyclerView) findViewById(R.id.drawer_item_view);
        DrawerRecycleView.setLayoutManager(new LinearLayoutManager(this));

        DrawerRecycleView.setAdapter(new DrawerAdapter(m_DrawerItems));
        DrawerRecycleView.addOnItemTouchListener(new OnClickListenerRecycleView(this, this));

        ViewPager.setOffscreenPageLimit(5);
        ViewPager.addOnPageChangeListener(this);
        ViewPager.setAdapter(new InfinitePagerAdapter(this));

        findViewById(R.id.lyt_page_indicator).setOnClickListener(this);
        new Thread(this).start();
    }

    @Override protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        m_Toggle.syncState();
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
        else if(Item.getItemId() == android.R.id.home)
        {
            if(m_DrawerLayout.isDrawerOpen(GravityCompat.START)) m_DrawerLayout.closeDrawers();
            else m_DrawerLayout.openDrawer(GravityCompat.START);
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
        while(ViewPager.getChildCount() == 0 || ((ViewGroup)ViewPager.getChildAt(0)).getChildCount() == 0) try{ Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        final ProgressBar ProgessBar = (ProgressBar) findViewById(R.id.lyt_progess);
        runOnUiThread(new Runnable() { @Override public void run() { ProgessBar.setVisibility(View.GONE); }});
    }

    private void ShowAbout()
    {
        AlertDialog.Builder AlertBuilder = new AlertDialog.Builder(this);

        String Message = "<strong>Versión</strong>: En Desarrollo<br />";
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

    @Override public void onItemClick(View v, int p)
    {
        for(int i = 0; i < ((ViewGroup) v.getParent()).getChildCount(); i++)
        {
            ((ViewGroup) v.getParent()).getChildAt(i).setSelected(i == p);
        }

        ((ViewPager) findViewById(R.id.lyt_news_pager)).removeAllViews();
        findViewById(R.id.lyt_progess).setVisibility(View.VISIBLE);

        String URL = m_DrawerItems.get(p).URL;
        Constants.BASE_URL = URL;

        new Thread(this).start();
        ((ViewPager) findViewById(R.id.lyt_news_pager)).setAdapter(new InfinitePagerAdapter(this));

        getSupportActionBar().setTitle(m_DrawerItems.get(p).Name);
        m_DrawerLayout.closeDrawers();

        TextView PageIndicator = (TextView) findViewById(R.id.lyt_page_number);
        PageIndicator.setText("" + 1);
    }

    @Override public void onItemLongClick(View v, int p)
    {

    }
}
