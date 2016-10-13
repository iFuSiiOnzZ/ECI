package eu.ifussionzz.eci.Utils;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class MyYoutubePlayer implements YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener
{
    private YouTubePlayer m_YoutubePlayer = null;
    private boolean m_FullScreen = false;

    public void Play(String YoutubeID)
    {
        if(m_YoutubePlayer == null) return;
        m_YoutubePlayer.loadVideo(YoutubeID);
    }

    public void Pause()
    {
        if(m_YoutubePlayer == null) return;
        m_YoutubePlayer.pause();
    }

    public boolean isPlaying()
    {
        if(m_YoutubePlayer == null) return false;
        return m_YoutubePlayer.isPlaying();
    }

    public boolean isFullScreen()
    {
        if(m_YoutubePlayer == null) return false;
        return m_FullScreen;
    }

    public void SetFullScreen(boolean b)
    {
        m_FullScreen = b;
        m_YoutubePlayer.setFullscreen(b);
    }

    @Override public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
    {
        m_YoutubePlayer = youTubePlayer;
        m_YoutubePlayer.setOnFullscreenListener(this);
    }

    @Override public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
    {
    }

    @Override public void onFullscreen(boolean b)
    {
        m_FullScreen = b;
    }
}
