package net.petclinic.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created on 24.03.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class CustomLinkedList<E> {
    private Node first;
    private Node last;

    private int size = 0;

    public int size(){
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean add(E e)
    {
        Node node = new Node(e);
        if(size == 0)
        {
            first = node;
            last = node;
        }else
        {
            last.next = node;
            last.next.previous = last;
            last = node;
        }

        size++;
        return true;
    }

    public boolean remove(E e)
    {
        if(size > 0)
        {
            if(first.e.equals(e))
            {
                first = first.next;
                first.previous = null;
                size--;
                return true;
            }else {
                Node current = first.next;
                Node previous = first;
                while (current != null) {
                    if (current.equals(e)) {
                        current.next.previous = previous;
                        previous.next = current.next;
                        size--;
                        return true;
                    } else {
                        previous = current;
                        current = current.next;
                    }

                }
            }

        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public E removeFirst()
    {
        Node temp = first;
        first = first.next;
        size--;
        return (E) temp.e;
    }

    @SuppressWarnings("unchecked")
    public E removeLast()
    {
        Node tmp = last;
        last = last.previous;
        last.next = null;
        size--;
        return (E) tmp.e;
    }

    private class Node<E>
    {
        E e;
        Node<E> next;
        Node<E> previous;

        Node(E e) {
            this.e = e;
            this.next = null;
            this.previous = null;

        }
    }

    public Iterator<E> iterator()
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>
    {
        int index = 0;
        Node<E> current = null;
        Node<E> lastReturned = null;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();

            if(index == 0)
                current = first;
            else
                current = current.next;
            index++;
            return (E) current.e;
        }

        @Override
        public void remove() {
            if(current != null)
            {
                current.previous = current.next;
                size--;
            }
        }
    }
}
