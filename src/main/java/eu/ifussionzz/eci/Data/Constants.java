package eu.ifussionzz.eci.Data;

public class Constants
{
    public static String CACHE_PATH = "";
    public static final String BASE_URL = "https://elchapuzasinformatico.com/";

    public static String GetRecentPosts(int NumPost)
    {
        return BASE_URL + "?json=core.get_recent_posts&include=title,excerpt,date,comment_count,thumbnail,categories,url&count=" + NumPost;
    }

    public static String GetCategories()
    {
        return BASE_URL + "?json=core.get_category_index";
    }

    public static String GetPostFromPage(int Page)
    {
        return BASE_URL + "?json=core.get_posts&include=title,excerpt,date,comment_count,thumbnail,categories,url&page=" + Page;
    }

    public static String GetPost(int PostId, int PageNum)
    {
        return BASE_URL + "?json=core.get_post&post_id=" + PostId + "&page=" + PageNum + "&include=url,title,content,comments";
    }

    public static String GetSearch(String Query)
    {
        return BASE_URL + "?json=core.get_search_results&include=title,excerpt,date,comment_count,thumbnail,categories,url&search=" + Query.replaceAll(" ", "%20");
    }
}
