package elchapuzasinformatico.com.eci.Utilities.Html;

import android.text.Editable;
import android.text.Html;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;

import org.xml.sax.XMLReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnDrEi AJ on 04/07/2015.
 */
public class ExtraHtmlTags implements Html.TagHandler
{
    private int m_index = 0;
    private List<String> m_parents = new ArrayList<>();

    @Override public void handleTag(final boolean opening, final String tag, Editable output, final XMLReader xmlReader)
    {
        if(tag.equals("ul") || tag.equals("ol") || tag.equals("dd"))
        {
            if(opening) m_parents.add(tag);
            else m_parents.remove(tag);

            m_index = 0;
        } else if(tag.equals("li") && !opening) handleListTag(output);
    }

    private void handleListTag(Editable output)
    {
        if(m_parents.get(m_parents.size() - 1).equals("ul"))
        {
            output.append("\n");
            String[] split = output.toString().split("\n");

            int lastIndex = split.length - 1;
            int start = output.length() - split[lastIndex].length() - 1;
            output.setSpan(new BulletSpan(15 * m_parents.size()), start, output.length(), 0);
        }
        else if(m_parents.get(m_parents.size() - 1).equals("ol"))
        {
            m_index++;

            output.append("\n");
            String[] split = output.toString().split("\n");

            int lastIndex = split.length - 1;
            int start = output.length() - split[lastIndex].length() - 1;
            output.insert(start, m_index + ". ");
            output.setSpan(new LeadingMarginSpan.Standard(15 * m_parents.size()), start, output.length(), 0);
        }
    }
}

