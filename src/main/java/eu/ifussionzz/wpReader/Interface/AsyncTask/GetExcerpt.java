package eu.ifussionzz.wpReader.Interface.AsyncTask;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.io.IOException;

import eu.ifussionzz.wpReader.Data.Constants;
import eu.ifussionzz.wpReader.Utils.Network;

import eu.ifussionzz.wpReader.Interface.Listener.OnClickListenerRecycleView;
import eu.ifussionzz.wpReader.Interface.View.Adapter.ExcerptAdapter;
import eu.ifussionzz.wpReader.Data.Excerpt.NewsExcerpt;

public class GetExcerpt extends AsyncTask<Integer, Void, ArrayList<NewsExcerpt>>
{
    private RecyclerView m_RecyclerView = null;
    private Context m_Context = null;

    public GetExcerpt(Context c, RecyclerView rv)
    {
        m_RecyclerView = rv;
        m_Context = c;
    }

    @Override protected ArrayList<NewsExcerpt> doInBackground(Integer... PageNumber)
    {
        ArrayList<NewsExcerpt> NewsExcerptArray = new ArrayList<>();

        try
        {
            String JSonString = Network.GetText(Constants.GetPostFromPage(PageNumber[0]));
            if(JSonString == null) return null;

            Gson gson = new Gson();
            NewsExcerptArray = gson.fromJson(JSonString, new TypeToken<ArrayList<NewsExcerpt>>(){}.getType());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return NewsExcerptArray;
    }

    @Override protected void onPostExecute(ArrayList<NewsExcerpt> Result)
    {
        ExcerptAdapter Adapter = new ExcerptAdapter(Result);
        OnClickListenerRecycleView Click = new OnClickListenerRecycleView(m_Context, Adapter);

        m_RecyclerView.setAdapter(Adapter);
        m_RecyclerView.addOnItemTouchListener(Click);
    }
}