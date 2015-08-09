package elchapuzasinformatico.com.eci.Eci;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import elchapuzasinformatico.com.eci.Adapters.InfinitePageAdapter;

/**
 * Created by AnDrEi AJ on 10/07/2015.
 */
public class NewsPage extends Fragment
{
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewPager l_ViewPager = new ViewPager(getActivity());

        l_ViewPager.setOffscreenPageLimit(5);
        l_ViewPager.setAdapter(new InfinitePageAdapter(getActivity()));

        return l_ViewPager;
    }
}
