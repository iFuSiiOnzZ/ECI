package elchapuzasinformatico.com.eci.Eci.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AnDrEi AJ on 04/06/2015.
 */
public class PostDetails implements Serializable
{
    @SerializedName("id")
    public int      m_ID = 0;

    @SerializedName("url")
    public String   m_Url = null;

    @SerializedName("title")
    public String m_Title = null;

    @SerializedName("content")
    public String   m_Content = null;

    @SerializedName("comments")
    public ArrayList<CommentDetails> m_Comments = null;

    public String toString()
    {
        return "";
    }
}
