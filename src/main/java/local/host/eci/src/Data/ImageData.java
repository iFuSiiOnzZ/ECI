package local.host.eci.src.Data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ImageData implements Serializable
{
    @SerializedName("url")
    public String URL = null;

    @SerializedName("height")
    public int Height = 0;

    @SerializedName("width")
    public int Width = 0;
}
