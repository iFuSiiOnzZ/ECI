package elchapuzasinformatico.com.eci.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by andrei on 30/7/15.
 * Modificado: 10/06/2016 20:28     Quitado los comentarios
 *                                  Las funciones empiezan por mayuscula
 *                                  Quitado el prefijo de las variables locales
 */
public class SPManager
{
    protected Context m_Context = null;

    public SPManager(Context Context)
    {
        m_Context = Context;
    }

    protected SharedPreferences getSharedPreferences()
    {
        return PreferenceManager.getDefaultSharedPreferences(m_Context);
    }

    public void PutString(String ID, String Value)
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.putString(ID, Value);
        l_Editor.commit();
    }

    public String GetString(String ID, String DefaultValue)
    {
        return getSharedPreferences().getString(ID, DefaultValue);
    }

    public void PutInt(String ID, Integer Value)
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.putInt(ID, Value);
        l_Editor.commit();
    }

    public int GetInt(String ID, int DefaultValue)
    {
        return getSharedPreferences().getInt(ID, DefaultValue);
    }

    public void PutBool(String ID, Boolean Value)
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.putBoolean(ID, Value);
        l_Editor.commit();
    }

    public boolean GetBool(String ID, boolean DefaultValue)
    {
        return getSharedPreferences().getBoolean(ID, DefaultValue);
    }

    public void ClearConfiguration()
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.clear(); l_Editor.commit();
    }
}
