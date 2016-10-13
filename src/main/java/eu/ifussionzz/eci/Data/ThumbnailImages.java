package eu.ifussionzz.eci.Data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ThumbnailImages implements Serializable
{
    @SerializedName("full")
    public ImageData Full = null;

    @SerializedName("thumbnail")
    public ImageData Thumbnai = null;

    @SerializedName("medium")
    public ImageData Medium = null;

    @SerializedName("post-thumbnail")
    public ImageData PostThumbnail = null;

    @SerializedName("popular_posts_img")
    public ImageData PopularPostsImg = null;
}
