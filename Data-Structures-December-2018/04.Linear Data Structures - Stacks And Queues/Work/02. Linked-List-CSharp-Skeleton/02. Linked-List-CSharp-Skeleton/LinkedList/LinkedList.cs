using System;
using System.Collections;
using System.Collections.Generic;

public class LinkedList<T> : IEnumerable<T>
{
    public class Node
    {
        public Node(T value)
        {
            Value = value;
        }

        public T Value { get; set; }
        public Node Next { get; set; }
    }

    public Node Head { get; private set; }
    public Node Tail { get; private set; }
    public int Count { get; private set; }

    public void AddFirst(T item)
    {
        Node old = this.Head;

        this.Head = new Node(item);
        this.Head.Next = old;

        if (Count == 0)
        {
            Tail = Head;
        }

        Count++;
    }

    public void AddLast(T item)
    {
        Node old = this.Tail;
        this.Tail = new Node(item);

        if (Count == 0)
        {
            Head = Tail;
        }
        else
        {
            old.Next = this.Tail;
        }

        Count++;
    }

    public T RemoveFirst()
    {
        if (Count == 0)
        {
            throw new InvalidOperationException();
        }

        T item = this.Head.Value;

        this.Head = this.Head.Next;

        this.Count--;

        if (Count == 0)
        {
            Tail = null;
        }

        return item;
    }

    public T RemoveLast()
    {
        if (Count == 0)
        {
            throw new InvalidOperationException();
        }

        T item = Tail.Value;

        if(Count == 1)
        {
            this.Head = this.Tail = null;
        }
        else
        {
            Node parent = this.Head;

            while (parent.Next != this.Tail)
            {
                parent = parent.Next;
            }

            parent.Next = null;
            this.Tail = parent;
        }

        Count--;
        return item;
    }

    public IEnumerator<T> GetEnumerator()
    {
        Node current = Head;

        while (current != null)
        {
            yield return current.Value;
            current = current.Next;
        }
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return GetEnumerator();
    }
}
