package elchapuzasinformatico.com.eci.Eci.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AnDrEi AJ on 13/06/2015.
 */
public class PostJSONData implements Serializable
{
    @SerializedName("id")
    public int m_Id = 0;

    @SerializedName("title")
    public String m_Title = null;

    @SerializedName("excerpt")
    public String m_Excerpt = null;

    @SerializedName("date")
    public String m_Date = null;

    @SerializedName("comment_Count")
    public int m_NumComments = 0;

    @SerializedName("thumbnail")
    public String m_Thumbnail = null;

    @SerializedName("categories")
    public ArrayList<Category> m_Categories = null;
}
