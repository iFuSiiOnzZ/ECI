package elchapuzasinformatico.com.eci.Eci.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by AnDrEi AJ on 04/06/2015.
 */
public class CommentDetails implements Serializable
{
    @SerializedName("id")
    public int      m_ID = 0;

    @SerializedName("url")
    public String m_URL = null;

    @SerializedName("parent")
    public int      m_Parent = 0;

    @SerializedName("date")
    public String   m_Date = null;

    @SerializedName("name")
    public String   m_Author = null;

    @SerializedName("content")
    public String   m_Content = null;

    public String toString()
    {
        return "";
    }
}
