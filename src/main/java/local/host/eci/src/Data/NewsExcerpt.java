package local.host.eci.src.Data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;


public class NewsExcerpt implements Serializable
{
    @SerializedName("id")
    public int Id = 0;

    @SerializedName("title")
    public String Title = null;

    @SerializedName("url")
    public String Url = null;

    @SerializedName("excerpt")
    public String Excerpt = null;

    @SerializedName("date")
    public String Date = null;

    @SerializedName("comment_count")
    public int NumComments = 0;

    @SerializedName("thumbnail")
    public String Thumbnail = null;

    @SerializedName("thumbnail_size")
    public String ThumbnailSize = null;

    @SerializedName("thumbnail_images")
    public ThumbnailImages Images = null;

    @SerializedName("categories")
    public ArrayList<NewsCategory> Categories = null;
}
