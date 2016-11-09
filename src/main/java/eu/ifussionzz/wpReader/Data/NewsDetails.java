package eu.ifussionzz.wpReader.Data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class NewsDetails implements Serializable
{
    @SerializedName("id")
    public int      m_ID = 0;

    @SerializedName("url")
    public String   m_Url = null;

    @SerializedName("title")
    public String m_Title = null;

    @SerializedName("content")
    public String   m_Content = null;

    @SerializedName("custom_fields")
    public CustomFields OtherFields = null;
}
