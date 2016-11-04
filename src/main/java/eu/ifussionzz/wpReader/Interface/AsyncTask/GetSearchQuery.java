package eu.ifussionzz.wpReader.Interface.AsyncTask;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import eu.ifussionzz.wpReader.Data.NewsExcerpt;
import eu.ifussionzz.wpReader.Data.Constants;

import eu.ifussionzz.wpReader.Interface.Listener.OnClickListenerRecycleView;
import eu.ifussionzz.wpReader.Interface.View.Adapter.SearchAdapter;
import eu.ifussionzz.wpReader.Utils.Network;


public class GetSearchQuery extends AsyncTask<String, Void, ArrayList<NewsExcerpt>>
{
    RecyclerView m_RecyclerView = null;
    private Context m_Context = null;

    public GetSearchQuery(Context Context, RecyclerView rv)
    {
        m_RecyclerView = rv;
        m_Context = Context;
    }

    @Override protected ArrayList<NewsExcerpt> doInBackground(String... SearchQuery)
    {

        try
        {
            Gson Gson = new Gson();
            String JSonString = Network.GetText(Constants.GetSearch(SearchQuery[0]));

            JSONObject Root = new JSONObject(JSonString);
            if(!Root.getString("status").equalsIgnoreCase("ok")) return null;

            return Gson.fromJson(Root.getJSONArray("posts").toString(), new TypeToken<ArrayList<NewsExcerpt>>(){}.getType());
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override protected void onPostExecute(ArrayList<NewsExcerpt> Result)
    {
        SearchAdapter Adapter = new SearchAdapter(Result);
        OnClickListenerRecycleView Click = new OnClickListenerRecycleView(m_Context, Adapter);

        m_RecyclerView.setAdapter(Adapter);
        m_RecyclerView.addOnItemTouchListener(Click);
    }
}
