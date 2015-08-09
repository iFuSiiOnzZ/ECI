package elchapuzasinformatico.com.eci.Eci;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 09/08/2015.
 */
public class SettingsPage extends Fragment
{
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View l_View = inflater.inflate(R.layout.settings, container, false);

        return l_View;
    }
}