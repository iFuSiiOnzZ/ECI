package elchapuzasinformatico.com.eci.Eci.Models;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 * Modificado: 13/06/2015 10:05
 *              Cogiendo solo los datos que nos interesa para NewsInfo
 */
public class URLS
{
    public static final String BASE_URL = "http://elchapuzasinformatico.com/";

    /**
     *
     * @param l_NumPost
     * @return
     */
    public static String getRecentPosts(int l_NumPost)
    {
        return BASE_URL + "?json=core.get_recent_posts&include=title,excerpt,date,comment_count,thumbnail,categories&count=" + l_NumPost;
    }

    public static String getCategories()
    {
        return BASE_URL + "?json=core.get_category_index";
    }

    /**
     *
     * @param l_Page
     * @return
     */
    public static String getPostFromPage(int l_Page)
    {
        return BASE_URL + "?json=core.get_posts&include=title,excerpt,date,comment_count,thumbnail,categories&page=" + l_Page;
    }

    /**
     *
     * @param l_PostId
     * @return
     */
    public static String getPost(int l_PostId, int l_PageNum)
    {
        return BASE_URL + "?json=core.get_post&post_id=" + l_PostId + "&page=" + l_PageNum + "&include=url,title,content,comments";
    }
}
