package elchapuzasinformatico.com.eci.Eci.Models;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 * Modificado: 13/06/2015 10:05     Cogiendo solo los datos que interesa para NewsInfo
 *
 *             10/06/2016 20:28     Quitado los comentarios
 *                                  BASE_URL usa HTTPS por defecto
 *                                  Quitado el prefijo de las variables locales
 */
public class URLS
{
    public static final String BASE_URL = "https://elchapuzasinformatico.com/";

    public static String getRecentPosts(int NumPost)
    {
        return BASE_URL + "?json=core.get_recent_posts&include=title,excerpt,date,comment_count,thumbnail,categories&count=" + NumPost;
    }

    public static String getCategories()
    {
        return BASE_URL + "?json=core.get_category_index";
    }

    public static String getPostFromPage(int Page)
    {
        return BASE_URL + "?json=core.get_posts&include=title,excerpt,date,comment_count,thumbnail,categories&page=" + Page;
    }

    public static String getPost(int PostId, int PageNum)
    {
        return BASE_URL + "?json=core.get_post&post_id=" + PostId + "&page=" + PageNum + "&include=url,title,content,comments";
    }
}
