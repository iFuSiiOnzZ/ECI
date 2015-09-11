package elchapuzasinformatico.com.eci.Eci;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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