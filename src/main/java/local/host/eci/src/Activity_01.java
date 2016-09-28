package local.host.eci.src;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

import android.widget.Toast;
import android.os.Bundle;

import local.host.eci.src.Data.Constants;
import local.host.eci.src.Utils.Network;
import local.host.eci.R;

public class Activity_01 extends AppCompatActivity implements Runnable
{
    @Override protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_01);

        new Thread(this).start();
        Constants.CACHE_PATH = getExternalCacheDir().getAbsolutePath() + "/";

        int nThreads = Runtime.getRuntime().availableProcessors();
    }

    @Override public void run()
    {
        Sleep(1500);
        if(!Network.isNetworkAvailable(this)) { runOnUiThread(new Runnable() { @Override public void run() { ShowToastAndExit(); }}); return; }

        Intent mainIntent = new Intent().setClass(this, Activity_02.class);
        startActivity(mainIntent); finish();
    }

    public void Sleep(long time)
    {
        try { Thread.sleep(time); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void ShowToastAndExit()
    {
        Toast.makeText(this, getString(R.string.toast_no_internet), Toast.LENGTH_LONG).show();
        finish();
    }
}
