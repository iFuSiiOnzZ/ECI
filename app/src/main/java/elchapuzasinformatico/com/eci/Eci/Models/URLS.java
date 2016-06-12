package elchapuzasinformatico.com.eci.Eci.Models;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 * Modificado: 13/06/2015 10:05     Cogiendo solo los datos que interesa para NewsInfo
 *
 *             10/06/2016 20:28     Quitado los comentarios
 *                                  BASE_URL usa HTTPS por defecto
 *                                  Quitado el prefijo de las variables locales
 *
 *             11/06/2016 11:40     Funciones empiezan por mayuscula
 *                                  Consutruccion para la query de busqueda
 */
public class URLS
{
    public static final String BASE_URL = "https://elchapuzasinformatico.com/";

    public static String GetRecentPosts(int NumPost)
    {
        return BASE_URL + "?json=core.get_recent_posts&include=title,excerpt,date,comment_count,thumbnail,categories&count=" + NumPost;
    }

    public static String GetCategories()
    {
        return BASE_URL + "?json=core.get_category_index";
    }

    public static String GetPostFromPage(int Page)
    {
        return BASE_URL + "?json=core.get_posts&include=title,excerpt,date,comment_count,thumbnail,categories&page=" + Page;
    }

    public static String GetPost(int PostId, int PageNum)
    {
        return BASE_URL + "?json=core.get_post&post_id=" + PostId + "&page=" + PageNum + "&include=url,title,content,comments";
    }

    public static String GetSearch(String Query)
    {
        return BASE_URL + "?json=core.get_search_results&include=title,excerpt,date,comment_count,thumbnail,categories&search=" + Query.replaceAll(" ", "%20");
    }
}
