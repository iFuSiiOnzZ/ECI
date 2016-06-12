package elchapuzasinformatico.com.eci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import elchapuzasinformatico.com.eci.Eci.AsynkTask.GetSearchQuery;

/**
 * Created by AnDrEi AJ on 11/06/2016.
 */
public class SearchActivity extends AppCompatActivity
{
    @Override protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        new GetSearchQuery(this).execute(getIntent().getStringExtra("SEARCH_QUERY"));
    }
}
