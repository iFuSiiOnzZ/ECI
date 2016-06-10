package elchapuzasinformatico.com.eci.Eci;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import elchapuzasinformatico.com.eci.Adapters.InfinitePageAdapter;
import elchapuzasinformatico.com.eci.Eci.SharedPref.SettingsPrefs;

/**
 * Created by AnDrEi AJ on 10/07/2015.
 * Modificado: 10/06/2016 20:28     Quitado los comentarios
 *                                  Quitado el prefijo de las variables locales
 */
public class NewsPage extends Fragment
{
    @Override public View onCreateView(LayoutInflater Inflater, ViewGroup Container, Bundle SavedInstanceState)
    {
        ViewPager ViewPager = new ViewPager(getActivity());

        ViewPager.setOffscreenPageLimit(new SettingsPrefs(getActivity()).GetPreloadNumPages());
        ViewPager.setAdapter(new InfinitePageAdapter(getActivity(), null));

        return ViewPager;
    }
}
