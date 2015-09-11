package elchapuzasinformatico.com.eci;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import elchapuzasinformatico.com.eci.Adapters.DrawerAdapter;
import elchapuzasinformatico.com.eci.ClickListeners.onClickListenerInterface;
import elchapuzasinformatico.com.eci.ClickListeners.onClickListenerRecycleView;
import elchapuzasinformatico.com.eci.Eci.NewsPage;
import elchapuzasinformatico.com.eci.Eci.RecentNewsPage;
import elchapuzasinformatico.com.eci.Eci.SettingsPage;
import elchapuzasinformatico.com.eci.Models.DrawerItem;
import elchapuzasinformatico.com.eci.Threads.UncaughtException;
import elchapuzasinformatico.com.eci.Utilities.Utilities;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 */
public class MainActivity extends AppCompatActivity implements onClickListenerInterface
{
    DrawerLayout m_DrawerLayout = null;
    ActionBarDrawerToggle m_Toggle = null;

    private long m_LastTimePressBack = System.currentTimeMillis();
    private long m_TimeToExitBackPress = 2000;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtException(this));
        setContentView(R.layout.drawer);

        Toolbar l_ToolBar = (Toolbar) findViewById(R.id.toolbar);
        l_ToolBar.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(l_ToolBar); getSupportActionBar().setTitle("");

        getSupportActionBar().setTitle("Noticias");
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView l_DrawerRecycleView = (RecyclerView) findViewById(R.id.drawer_item_view);
        l_DrawerRecycleView.setLayoutManager(new LinearLayoutManager(this));

        m_DrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        m_Toggle = new ActionBarDrawerToggle(this, m_DrawerLayout, R.string.open, R.string.close);

        m_Toggle.setDrawerIndicatorEnabled(true);
        m_DrawerLayout.setDrawerListener(m_Toggle);

        ArrayList<DrawerItem> m_DrawerItems = new ArrayList<>();
        m_DrawerItems.add(new DrawerItem("",          null));
        m_DrawerItems.add(new DrawerItem("Noticias",  BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon_news)));
        m_DrawerItems.add(new DrawerItem("Recientes", BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon_recents)));
        m_DrawerItems.add(new DrawerItem("Ajustes",   BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon_settings)));

        l_DrawerRecycleView.setAdapter(new DrawerAdapter(m_DrawerItems));
        l_DrawerRecycleView.addOnItemTouchListener(new onClickListenerRecycleView(this, this));

        FragmentManager l_FragmentManager = getSupportFragmentManager();
        l_FragmentManager.beginTransaction().replace(R.id.fragment_container, new NewsPage(), "NewsPage").addToBackStack(null).commit();
    }

    @Override protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        m_Toggle.syncState();
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

    @Override public boolean onOptionsItemSelected(MenuItem l_Item)
    {
        if(l_Item.getItemId() == android.R.id.home)
        {
            if(m_DrawerLayout.isDrawerOpen(GravityCompat.START)) m_DrawerLayout.closeDrawers();
            else m_DrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(l_Item);
    }

    @Override public void onItemClick(View l_View, int l_Position)
    {
        ViewGroup l_ViewGroup = (ViewGroup) l_View.getParent();
        for(int i = 0; i < l_ViewGroup.getChildCount(); i++)
        {
            l_ViewGroup.getChildAt(i).setSelected(i == l_Position);
        }

        if(l_Position == 1)
        {
            getSupportActionBar().setTitle("Noticias");
            FragmentManager l_FragmentManager = getSupportFragmentManager();
            l_FragmentManager.beginTransaction().replace(R.id.fragment_container, new NewsPage(), "NewsPage").addToBackStack(null).commit();
        }

        if(l_Position == 2)
        {
            getSupportActionBar().setTitle("Recientes");
            FragmentManager l_FragmentManager = getSupportFragmentManager();
            l_FragmentManager.beginTransaction().replace(R.id.fragment_container, new RecentNewsPage(), "RecentNewsPage").addToBackStack(null).commit();
        }

        if(l_Position == 3)
        {
            getSupportActionBar().setTitle("Ajustes");
            FragmentManager l_FragmentManager = getSupportFragmentManager();
            l_FragmentManager.beginTransaction().replace(R.id.fragment_container, new SettingsPage(), "SettingsPage").addToBackStack(null).commit();
        }

        m_DrawerLayout.closeDrawers();
    }

    @Override public void onItemLongClick(View l_View, int l_Position)
    {

    }
}
