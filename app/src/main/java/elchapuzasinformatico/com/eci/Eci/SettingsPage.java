package elchapuzasinformatico.com.eci.Eci;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import elchapuzasinformatico.com.eci.Eci.SharedPref.SettingsPrefs;
import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 09/08/2015.
 */
public class SettingsPage extends Fragment implements SeekBar.OnSeekBarChangeListener, Switch.OnCheckedChangeListener
{
    View m_View = null;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        m_View = inflater.inflate(R.layout.settings, container, false);

        ((TextView) m_View.findViewById(R.id.id_seek_value)).setText(String.valueOf(new SettingsPrefs(getActivity()).GetPreloadNumPages()));
        SeekBar l_PagesPreload = ((SeekBar) m_View.findViewById(R.id.id_pages_preload));

        l_PagesPreload.setProgress(new SettingsPrefs(getActivity()).GetPreloadNumPages());
        l_PagesPreload.setOnSeekBarChangeListener(this);
        l_PagesPreload.setMax(15);

        Switch l_ImagePreload = ((Switch) m_View.findViewById(R.id.id_images_preload));
        l_ImagePreload.setChecked(new SettingsPrefs(getActivity()).GetPreloadImages());
        l_ImagePreload.setOnCheckedChangeListener(this);

        return m_View;
    }

    @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        ((TextView) m_View.findViewById(R.id.id_seek_value)).setText(String.valueOf(progress));
        new SettingsPrefs(getActivity()).SetPreloadNumPages(progress);
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override public void onStopTrackingTouch(SeekBar seekBar)
    {

    }

    @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        new SettingsPrefs(getActivity()).SetPreloadImages(isChecked);
    }
}