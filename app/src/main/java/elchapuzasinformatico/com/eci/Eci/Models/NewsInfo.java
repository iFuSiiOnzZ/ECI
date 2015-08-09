package elchapuzasinformatico.com.eci.Eci.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AnDrEi AJ on 30/05/2015.
 */
public class NewsInfo implements Serializable
{
    @SerializedName("id")
    public int m_Id = 0;

    @SerializedName("title")
    public String m_Title = null;

    @SerializedName("excerpt")
    public String m_Excerpt = null;

    @SerializedName("date")
    public String m_Date = null;

    @SerializedName("comment_count")
    public int m_NumComments = 0;

    @SerializedName("thumbnail")
    public String m_Thumbnail = null;

    @SerializedName("categories")
    public ArrayList<Category> m_Categories = null;

    public String toString()
    {
        String l_RetString = "";

        l_RetString += "Title: " + m_Title + "\n";
        l_RetString += "Description: " + m_Excerpt + "\n";

        l_RetString += "Time: " + m_Date + "\n";
        l_RetString += "Author: " + m_Categories.get(0).m_Title + "\n";

        l_RetString += "PostImag " + m_Thumbnail + "\n";
        l_RetString += "NumComments " + m_NumComments + "\n";

        return l_RetString;
    }
}
