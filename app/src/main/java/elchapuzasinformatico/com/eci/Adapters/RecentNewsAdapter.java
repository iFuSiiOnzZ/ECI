package elchapuzasinformatico.com.eci.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import elchapuzasinformatico.com.eci.Eci.Models.NewsInfo;
import elchapuzasinformatico.com.eci.Holders.NewsHolder;
import elchapuzasinformatico.com.eci.Holders.RecentsNewsHolder;
import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 30/05/2015.
 * Igual que NewsAdapter pero con menos informacion a mostrar.
 */
public class RecentNewsAdapter extends RecyclerView.Adapter<RecentsNewsHolder>
{
    private int m_RawLayout = 0;
    private Context m_Context = null;
    private List<NewsInfo> m_NewsInfos = null;

    /**
     *
     * @param l_NewsInfos lista de noticias, contiene el titulo, descripcion, fecha publicacion, etc.
     * @param l_RawLayout identificar del layout asociado a la noticia.
     * @param l_Context contexto de ejecucion.
     */
    public RecentNewsAdapter(List<NewsInfo> l_NewsInfos, int l_RawLayout, Context l_Context)
    {
        m_NewsInfos = l_NewsInfos;
        m_RawLayout = l_RawLayout;
        m_Context = l_Context;
    }

    /**
     *
     * @param l_ViewGroup contiene la vista padre.
     * @param l_ViewType tipo de vista que vamos a generar (no se usa).
     * @return estructura que contiene todas las vistas del layout.
     */
    @Override public RecentsNewsHolder onCreateViewHolder(ViewGroup l_ViewGroup, int l_ViewType)
    {
        View l_View = LayoutInflater.from(l_ViewGroup.getContext()).inflate(m_RawLayout, l_ViewGroup, false);
        return new RecentsNewsHolder(l_View);
    }

    /**
     *
     * @param l_ViewHolder estructura que contiene todas las vistas del layout.
     * @param l_Location  posicion en la array de noticias con la cual llenaremos la vista.
     */
    @Override public void onBindViewHolder(RecentsNewsHolder l_ViewHolder, int l_Location)
    {
        NewsInfo l_NewsInfo = m_NewsInfos.get(l_Location);

        l_ViewHolder.m_PostTime.setText(Html.fromHtml(l_NewsInfo.m_Date));
        l_ViewHolder.m_PostTitle.setText(Html.fromHtml(l_NewsInfo.m_Title));
        l_ViewHolder.m_PostCategory.setText(Html.fromHtml(l_NewsInfo.m_Categories.get(0).m_Title));

        l_ViewHolder.m_PostComments.setText(l_NewsInfo.m_NumComments +" " + m_Context.getString(R.string.commom_comments));
        l_ViewHolder.m_PostImage.setURL(l_NewsInfo.m_Thumbnail);
    }

    /**
     * Devuelve el numero de noticias disponibles.
     * @return numero de noticias.
     */
    @Override public int getItemCount()
    {
        return m_NewsInfos == null ? 0 : m_NewsInfos.size();
    }

    /**
     *  Nos permite cambiar las actuales noticias por otras.
     * @param l_NewsInfos lista de noticias
     */
    public void setData(List<NewsInfo> l_NewsInfos)
    {
        m_NewsInfos = l_NewsInfos;
    }
}
