package eu.ifussionzz.wpReader.Data.Media;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Thumbnail implements Serializable
{
    @SerializedName("file")
    public String file;

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("mime_type")
    public String mime_type;

    @SerializedName("source_url")
    public String source_url;
}
