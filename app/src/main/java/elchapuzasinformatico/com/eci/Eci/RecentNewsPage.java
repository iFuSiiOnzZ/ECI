package elchapuzasinformatico.com.eci.Eci;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import elchapuzasinformatico.com.eci.Eci.AsynkTask.GetRecentNews;

/**
 * Created by AnDrEi AJ on 19/07/2015.
 */
public class RecentNewsPage extends Fragment
{
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RecyclerView l_NewsRecycleView = new RecyclerView(getActivity());
        l_NewsRecycleView.setPadding(16, 0, 16, 0);

        l_NewsRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        l_NewsRecycleView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        new GetRecentNews(l_NewsRecycleView, getActivity()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 24);
        return l_NewsRecycleView;
    }
}
