package net.petclinic.utils;


import java.io.Serializable;
import java.util.*;

/**
 * Created on 24.03.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class CustomList<E> extends AbstractList<E> implements Cloneable, List<E>, Serializable{
    private Object[] elementData;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_EMPTY_CAPACITY = {};
    private static final Object[] EMPTY_ARRAY = {};
    private int size = 0;

    public CustomList()
    {
        this.elementData = DEFAULT_EMPTY_CAPACITY;
    }

    public CustomList(int capacity)
    {
        if(capacity > 0)
            elementData = new Object[capacity];
        else if (capacity == 0)
            elementData = EMPTY_ARRAY;
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

    public boolean add(Object o)
    {
        checkSize(size + 1);
        elementData[size++] = o;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index)
    {
        if(index >= 0 && index < size)
            return (E) elementData[index];
        else
            throw new ArrayIndexOutOfBoundsException(outOfBound(index));
    }


    public int indexOf(Object o)
    {
        if(o == null)
        {
            for(int i = 0;i < size; i++)
            {
                if(elementData[i] == null)
                    return i;
            }
        }else
        {
            for(int i = 0; i< size; i++)
                if(o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    @SuppressWarnings("unchecked")
    public E remove(int index)
    {
        rangeCheck(index);
        E e = (E) elementData[index];
        elementData[index] = null;
        return e;
    }

    private void fastRemove(int index)
    {
        int leng = size - index - 1;
        if (leng > 0)
            System.arraycopy(elementData, index + 1, elementData, index, leng);
        elementData[--size] = null;
    }

    public boolean remove(Object o)
    {
        if(o == null)
        {
            for(int i=0; i <size;i++)
                if(elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
        }else{
            for(int i = 0; i<size; i++)
                if(o.equals(elementData[i]))
                {
                    fastRemove(i);
                    return true;
                }
        }
        return false;
    }

    public boolean containes(E e)
    {
        return indexOf(e) >= 0;
    }

    private void rangeCheck(int index)
    {
        if(index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(outOfBound(index));
    }

    private String outOfBound(int index)
    {
        return "Index " + index + " is out of bound.";
    }

    private void checkSize(int newCapacity)
    {
        if(elementData.length == DEFAULT_EMPTY_CAPACITY.length)
            elementData = new Object[DEFAULT_CAPACITY];
        else if(elementData.length >= size + 1)
        {
            extendSize(size + 10);
        }
    }

    private void extendSize(int newCapacity)
    {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, newCapacity);
        elementData = newArray;
    }

    public Iterator<E> iterator()
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>
    {
        int cursor;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            int i = cursor;

            if(i >= size)
                throw new NoSuchElementException();
            Object[] elementData = CustomList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();

            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        @Override
        @SuppressWarnings("unchecked")
        public void remove() {
            if(lastRet < 0)
                throw new IllegalStateException();
            int i = cursor;

            try {
                CustomList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            }catch (IndexOutOfBoundsException ex)
            {
                throw new ConcurrentModificationException();
            }
        }
    }
}
