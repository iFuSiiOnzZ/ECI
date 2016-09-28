package local.host.eci.src.Data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class NewsCategory implements Serializable
{
    @SerializedName("description")
    public String Description = null;

    @SerializedName("title")
    public String Title = null;

    @SerializedName("slug")
    public String Slug = null;

    @SerializedName("post_count")
    public int Posts = 0;

    @SerializedName("id")
    public int Id = 0;

    @SerializedName("parent")
    public int Parent = 0;
}
