package eu.ifussionzz.wpReader.Data.Excerpt;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class NewsExcerpt implements Serializable
{
    @SerializedName("id")
    public int id = 0;

    @SerializedName("date")
    public String date;

    @SerializedName("link")
    public String link;

    @SerializedName("title")
    public Title title;

    @SerializedName("excerpt")
    public Excerpt excerpt;

    @SerializedName("content")
    public Content content;

    @SerializedName("featured_media")
    public int featured_media;

    /**/
    public String thumbnail;
}
