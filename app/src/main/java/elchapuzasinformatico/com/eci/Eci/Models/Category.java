package elchapuzasinformatico.com.eci.Eci.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 * Modificado: 01/06/2015.
 *          Serializando la clase para poder hacer un mapeo contra el JSon.
 */
public class Category implements Serializable
{
    @SerializedName("slug")
    public String m_Slug = null;

    @SerializedName("title")
    public String m_Title = null;

    @SerializedName("description")
    public String m_Description = null;

    @SerializedName("id")
    public int m_Id = 0;

    @SerializedName("parent")
    public int m_Parent = 0;

    @SerializedName("post_count")
    public int m_Posts = 0;

    public String toString()
    {
        String l_RetString = "";

        l_RetString += "Slug: " + m_Slug + "\n";
        l_RetString += "Title: " + m_Title + "\n";
        l_RetString += "Description: " + m_Description + "\n";

        l_RetString += "Id: " + m_Id + "\n";
        l_RetString += "Posts: " + m_Posts + "\n";
        l_RetString += "Parent: " + m_Parent + "\n";

        return l_RetString;
    }
}
