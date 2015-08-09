package elchapuzasinformatico.com.eci.Views;

import android.content.Context;
import android.widget.TextView;
import android.view.View;

import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 04/07/2015.
 */
public class ExpandableTextView extends TextView implements View.OnClickListener
{
    /** */
    private static final int DEFAULT_TRIM_LENGTH = 200;

    /** */
    private static final String ELLIPSIS = "...";

    /** */
    private String m_FullText = null;

    /** */
    private boolean m_Trim = true;

    /** */
    private int m_TrimLength = 0;

    /**
     *
     * @param l_Context
     */
    public ExpandableTextView(Context l_Context)
    {
        super(l_Context);
        setOnClickListener(this);
        m_TrimLength = DEFAULT_TRIM_LENGTH;
    }

    /**
     *
     */
    private void setText()
    {
        super.setText(getDisplayableText());
    }

    /**
     *
     * @return
     */
    private CharSequence getDisplayableText()
    {
        return m_Trim ? getTrimmedText(m_FullText, m_TrimLength) : m_FullText;
    }

    /**
     *
     * @param l_Text
     */
    @Override public void setText(CharSequence l_Text, BufferType l_BufferType)
    {
        m_FullText = l_Text.toString();
        setText();
    }

    /**
     *
     * @param v
     */
    @Override public void onClick(View v)
    {
        m_Trim = !m_Trim; setText();
        requestFocusFromTouch();
    }

    /**
     *
     * @param l_Text
     * @param l_TrimLength
     * @return
     */
    private String getTrimmedText(String l_Text, int l_TrimLength)
    {
        if(l_Text != null && l_Text.length() > l_TrimLength)return m_FullText.substring(0, m_TrimLength).concat(ELLIPSIS);
        else return m_FullText;
    }

    /**
     *
     * @param l_TrimLength
     */
    public void setTrimLength(int l_TrimLength)
    {
        m_TrimLength = l_TrimLength;
        setText();
    }

    /**
     *
     * @return
     */
    public int getTrimLength()
    {
        return m_TrimLength;
    }
}
