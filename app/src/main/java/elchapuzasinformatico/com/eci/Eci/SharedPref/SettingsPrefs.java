package elchapuzasinformatico.com.eci.Eci.SharedPref;

import android.content.Context;

import elchapuzasinformatico.com.eci.SharedPref.SPManager;

/**
 * Created by AnDrEi AJ on 15/09/2015.
 */
public class SettingsPrefs extends SPManager
{
    private static String PRELOAD_NUM_PAGES = "preload_num_pages";
    private static String PRELOAD_IMAGES    = "preload_images";

    /**
     * Constructor
     *
     * @param context
     */
    public SettingsPrefs(Context context)
    {
        super(context);
    }

    /**
     *
     * @param l_NumPages
     */
    public void setPreloadNumPages(int l_NumPages)
    {
        putInt(PRELOAD_NUM_PAGES, l_NumPages);
    }

    /**
     *
     * @return
     */
    public int getPreloadNumPages()
    {
        return getInt(PRELOAD_NUM_PAGES, 5);
    }

    /**
     *
     * @param l_Preload
     */
    public void setPreloadImages(boolean l_Preload)
    {
        putBool(PRELOAD_IMAGES, l_Preload);
    }

    /**
     *
     * @return
     */
    public boolean getPreloadImages()
    {
        return getBool(PRELOAD_IMAGES, true);
    }
}
