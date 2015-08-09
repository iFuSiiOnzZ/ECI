package elchapuzasinformatico.com.eci.Eci.Models;

import android.os.Environment;

import java.io.File;

/**
 * Created by AnDrEi AJ on 06/07/2015.
 */
public class Constants
{
    /** Numero de paginas cargadas por defecto */
    public static final int LOAD_NUM_PAGES  = 5;

    /** Cachear images */
    public static final boolean IMAGE_CACHE = true;

    /** Pregargar imagesnes */
    public static final boolean IMAGE_PRELOAD  = false;

    /** Para guardar cualquier cosa temporal */
    public static final String CACHE_PATH = Environment.getExternalStorageState() + ".eci" + File.separator;
}
