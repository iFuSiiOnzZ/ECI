package eu.ifussionzz.wpReader.Data.Media;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Media implements Serializable
{
    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("file")
    public String file;

    @SerializedName("sizes")
    public Size images;
}
