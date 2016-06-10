package elchapuzasinformatico.com.eci.Eci.SharedPref;

import android.content.Context;

import elchapuzasinformatico.com.eci.SharedPref.SPManager;

/**
 * Created by AnDrEi AJ on 15/09/2015.
 * Modificado: 10/06/2016 20:28     Quitado los comentarios
 *                                  Las funciones empiezan por mayuscula
 *                                  Quitado el prefijo de las variables locales
 */
public class SettingsPrefs extends SPManager
{
    private static String PRELOAD_NUM_PAGES = "preload_num_pages";
    private static String PRELOAD_IMAGES    = "preload_images";

    public SettingsPrefs(Context Context)
    {
        super(Context);
    }

    public void SetPreloadNumPages(int NumPages)
    {
        PutInt(PRELOAD_NUM_PAGES, NumPages);
    }

    public int GetPreloadNumPages()
    {
        return GetInt(PRELOAD_NUM_PAGES, 5);
    }

    public void SetPreloadImages(boolean Preload)
    {
        PutBool(PRELOAD_IMAGES, Preload);
    }

    public boolean GetPreloadImages()
    {
        return GetBool(PRELOAD_IMAGES, true);
    }
}
