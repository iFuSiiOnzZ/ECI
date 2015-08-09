package elchapuzasinformatico.com.eci.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 */
public class TemplateManager<T>
{
    /**
     * Vector que contiene todos los elementos.
     */
    protected List<T> m_Data = null;

    /**
     * Constructor: Instancia un vector de tipo T un vector que contiene todos los elementos.
     */
    public TemplateManager()
    {
        m_Data = new ArrayList<>();
    }

    /**
     * Anade un nuevo elemento al manager.
     * @param l_Element elemento a anadir.
     */
    public void addElement(T l_Element)
    {
        m_Data.add(l_Element);
    }

    /**
     * Recupera un elemento del manager.
     * @param l_Index posicion donde se encuentra en el vector.
     * @return elemento requerido.
     */
    public T getElement(int l_Index)
    {
        return m_Data.get(l_Index);
    }

    /**
     * Devuelve todos los elementos del manager.
     * @return vector que contiene todos los elementos.
     */
    public List<T> getAllData()
    {
        return m_Data;
    }

    /**
     * Devuelve cuantos elementos hay en el manager.
     * @return numero de elementos.
     */
    public int size()
    {
        return m_Data.size();
    }

    /**
     * Borra todos los elementos.
     */
    public void clear()
    {
        m_Data.clear();
    }

    /**
     * Devuelve una string con toda la informacion que contiene.
     * @return toda la informacion que contiene.
     */
    public String toString()
    {
        String l_RetString = "";

        for(int i = 0; i < m_Data.size(); ++i) l_RetString += String.valueOf(m_Data.get(i)) + "\n";

        return l_RetString;
    }
}
