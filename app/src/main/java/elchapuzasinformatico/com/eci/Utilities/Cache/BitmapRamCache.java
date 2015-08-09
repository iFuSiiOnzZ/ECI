package elchapuzasinformatico.com.eci.Utilities.Cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by AnDrEi AJ on 26/06/2015.
 */
public class BitmapRamCache extends LruCache<String, Bitmap>
{
    /** */
    private static final int DEFAULT_CACHE_SIZE = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;

    /** */
    private static BitmapRamCache m_Instance = null;

    /**
     *
     */
    private BitmapRamCache()
    {
        super(DEFAULT_CACHE_SIZE);
    }

    /**
     *
     * @return
     */
    public static BitmapRamCache getInstance()
    {
        if (m_Instance == null) m_Instance = new BitmapRamCache();
        return m_Instance;
    }
}
