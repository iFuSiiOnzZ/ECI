package elchapuzasinformatico.com.eci.Eci.AsynkTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import elchapuzasinformatico.com.eci.Utilities.Network.DownloadUtilities;
import elchapuzasinformatico.com.eci.Eci.Models.NewsInfo;
import elchapuzasinformatico.com.eci.Eci.Models.URLS;

/**
 * Created by AnDrEi AJ on 11/06/2016.
 */
public class GetSearchQuery extends AsyncTask<String, Void, ArrayList<NewsInfo>>
{
    private Context m_Context = null;

    public GetSearchQuery(Context Context)
    {
        m_Context = Context;
    }

    @Override protected ArrayList<NewsInfo> doInBackground(String... SearchQuery)
    {
        Log.d("SEACG", URLS.GetSearch(SearchQuery[0]));
        String JSonString = DownloadUtilities.getStringFromNetwork(URLS.GetSearch(SearchQuery[0]));
        if(JSonString == null) return null;

        try
        {
            Gson l_Gson = new Gson();
            JSONObject l_Root = new JSONObject(JSonString);

            if(!l_Root.getString("status").equalsIgnoreCase("ok")) return null;
            return l_Gson.fromJson(l_Root.getJSONArray("posts").toString(), new TypeToken<ArrayList<NewsInfo>>(){}.getType());
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override protected void onPostExecute(ArrayList<NewsInfo> Result)
    {
    }
}
