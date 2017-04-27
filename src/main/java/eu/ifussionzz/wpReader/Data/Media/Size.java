package eu.ifussionzz.wpReader.Data.Media;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Size implements Serializable
{
    @SerializedName("thumbnail")
    public Thumbnail thumbnail;

    @SerializedName("medium")
    public Thumbnail medium;

    @SerializedName("post-thumbnail")
    public Thumbnail post_thumbnail;
}
