package net.petclinic.utils;


/**
 * Created on 24.03.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class CustomList<V> {
    private Object[] array;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_EMPTY_CAPACITY = {};
    private static final Object[] EMPTY_ARRAY = {};
    private int size = 0;

    public CustomList()
    {
        this.array = DEFAULT_EMPTY_CAPACITY;
    }

    public CustomList(int capacity)
    {
        if(capacity > 0)
            array = new Object[capacity];
        else if (capacity == 0)
            array = EMPTY_ARRAY;
        else
            throw new IllegalArgumentException("Illegal Capacity: "+
                    capacity);

    }



    public int size()
    {
        return this.size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean add(V v)
    {

        return true;
    }
}
