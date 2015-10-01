package elchapuzasinformatico.com.eci.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by andrei on 30/7/15.
 */
public class SPManager
{
    protected Context m_Context = null;

    /**
     * Constructor
     *
     * @param context
     */
    public SPManager(Context context)
    {
        m_Context = context;
    }

    /**
     *
     * @return
     */
    protected SharedPreferences getSharedPreferences()
    {
        return PreferenceManager.getDefaultSharedPreferences(m_Context);
    }

    /**
     * Stores a string to shared preferences.
     * @param l_ID id that identifies the string.
     * @param l_Value value that we want to store.
     */
    public void putString(String l_ID, String l_Value)
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.putString(l_ID, l_Value);
        l_Editor.commit();
    }

    /**
     * Return the string stored in the shared preferences.
     * Default return value null.
     *
     * @param l_ID id that identifies the string.
     * @return the stored string.
     */
    public String getString(String l_ID, String l_DefaultValue)
    {
        return getSharedPreferences().getString(l_ID, l_DefaultValue);
    }

    /**
     * Stores an integer to shared preferences.
     *
     * @param l_ID id that identifies the intger.
     * @param l_Value value that we want to store.
     */
    public void putInt(String l_ID, Integer l_Value)
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.putInt(l_ID, l_Value);
        l_Editor.commit();
    }

    /**
     * Return the integer stored in the shared preferences.
     * Default return value zero.
     *
     * @param l_ID id that identifies the integer.
     * @return the stored integer.
     */
    public int getInt(String l_ID, int l_DefaultValue)
    {
        return getSharedPreferences().getInt(l_ID, l_DefaultValue);
    }

    /**
     * Stores a bool to shared preferences.
     * @param l_ID id that identifies the bool.
     * @param l_Value value that we want to store.
     */
    public void putBool(String l_ID, Boolean l_Value)
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.putBoolean(l_ID, l_Value);
        l_Editor.commit();
    }

    /**
     * Return the boolean stored in the shared preferences.
     * Default return value false.
     *
     * @param l_ID id that identifies the boolean.
     * @return the stored boolean.
     */
    public boolean getBool(String l_ID, boolean l_DefaultValue)
    {
        return getSharedPreferences().getBoolean(l_ID, l_DefaultValue);
    }

    /**
     * This method sets all the shared info to a default value.
     */
    public void clearConfiguration()
    {
        SharedPreferences.Editor l_Editor = getSharedPreferences().edit();
        l_Editor.clear(); l_Editor.commit();
    }
}
