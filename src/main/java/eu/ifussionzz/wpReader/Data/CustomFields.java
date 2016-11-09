package eu.ifussionzz.wpReader.Data;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomFields implements Serializable
{
    @SerializedName("post_video")
    public ArrayList<String> PostVideos = null;
}
