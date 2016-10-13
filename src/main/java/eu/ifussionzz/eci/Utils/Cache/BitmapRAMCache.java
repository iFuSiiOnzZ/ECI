package eu.ifussionzz.eci.Utils.Cache;

import android.graphics.Bitmap;

public class BitmapRAMCache
{
    private class BitmapCaheMap
    {
        String Key = "";
        Bitmap Bmp = null;
    }

    private static BitmapRAMCache m_Instance = null;
    BitmapCaheMap m_CacheMap[] = null;

    int m_MaxCachedImages = 100;
    int m_CurrentPos = 0;

    private BitmapRAMCache()
    {
        m_CacheMap = new BitmapCaheMap[m_MaxCachedImages];
        for(int i = 0; i < m_MaxCachedImages; ++i) m_CacheMap[i] = new BitmapCaheMap();
    }

    public static synchronized BitmapRAMCache GetInstance()
    {
        if (m_Instance == null) m_Instance = new BitmapRAMCache();
        return m_Instance;
    }

    private int getMapPos(String Key)
    {
        for(int i = 0; i < m_MaxCachedImages; ++i) if(m_CacheMap[i].Key.equals(Key)) return i;
        return -1;
    }

    public synchronized void put(String Key, Bitmap Bmp)
    {
        int CacheMapPos = getMapPos(Key);
        if(CacheMapPos >= 0) { m_CacheMap[CacheMapPos].Bmp = Bmp; return; }

        m_CacheMap[m_CurrentPos].Key = Key;
        m_CacheMap[m_CurrentPos].Bmp = Bmp;

        m_CurrentPos = (m_CurrentPos + 1) % m_MaxCachedImages;
    }

    public Bitmap get(String Key)
    {
        for(int i = 0; i < m_MaxCachedImages; ++i) if(m_CacheMap[i].Key.equals(Key)) return m_CacheMap[i].Bmp;
        return null;
    }
}