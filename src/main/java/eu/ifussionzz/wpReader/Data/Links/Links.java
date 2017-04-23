package eu.ifussionzz.wpReader.Data.Links;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Vector;

public class Links implements Serializable
{
    @SerializedName("wp:featuredmedia")
    public Vector<Container> media;
}
