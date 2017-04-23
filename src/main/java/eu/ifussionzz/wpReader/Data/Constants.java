package eu.ifussionzz.wpReader.Data;

public class Constants
{
    public static String CACHE_PATH = "";
    public static String BASE_URL = "";

    public static String GetPostFromPage(int Page)
    {
        //return BASE_URL + "?json=core.get_posts&include=title,excerpt,date,comment_count,thumbnail,categories,url&page=" + Page;
        return BASE_URL + "/wp-json/wp/v2/posts?page=" + Page + "&per_page=22";
    }

    public static String GetMediaData(int MediaID)
    {
        return BASE_URL + "/wp-json/wp/v2/media/" + MediaID;
    }

    public static String GetPost(int PostId, int PageNum)
    {
        return BASE_URL + "?json=core.get_post&post_id=" + PostId + "&page=" + PageNum + "&include=url,title,content,comments,custom_fields";
    }

    public static String GetSearch(String Query)
    {
        return BASE_URL + "?json=core.get_search_results&include=title,excerpt,date,comment_count,thumbnail,categories,url&search=" + Query.replaceAll(" ", "%20");
    }
}
