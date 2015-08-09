package elchapuzasinformatico.com.eci.Utilities;

import android.content.Context;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by AnDrEi AJ on 04/07/2015.
 */
public class YoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    /** */
    private static final String API_KEY = "AIzaSyAE1S6bP0pGTktCcRw4Dk-r_AgeD92E798";

    /** */
    private YouTubePlayerView m_YoutubeVideo = null;

    /** */
    private String m_YoutubeID = null;

    public YoutubePlayer(String l_YoutubeID, Context l_Context)
    {
        m_YoutubeVideo = new YouTubePlayerView(l_Context);
        m_YoutubeVideo.initialize(API_KEY, this);
        m_YoutubeID = l_YoutubeID;
    }

    public View getView()
    {
        return m_YoutubeVideo;
    }

    @Override public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
    {
        youTubePlayer.loadVideo(m_YoutubeID);
    }

    @Override public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
    {
    }
}
