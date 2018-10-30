using System;
using System.Collections.Generic;
using Wintellect.PowerCollections;

public class FirstLastList<T> : IFirstLastList<T> where T : IComparable<T>
{
    private OrderedBag<T> byAscending;
    private LinkedList<T> byInsertion;

    public FirstLastList()
    {
        this.byInsertion = new LinkedList<T>();
        this.byAscending = new OrderedBag<T>();
    }

    public int Count
    {
        get
        {
            return this.byInsertion.Count;
        }
    }

    public void Add(T element)
    {
        this.byInsertion.AddLast(element);
        this.byAscending.Add(element);
    }

    public void Clear()
    {
        this.byInsertion.Clear();
        this.byAscending.Clear();
    }

    public IEnumerable<T> First(int count)
    {
        if (!CountIsInBounds(count))
        {
            throw new ArgumentOutOfRangeException();
        }

        LinkedListNode<T> current = this.byInsertion.First;

        int iterator = 0;
        while (iterator < count)
        {
            yield return current.Value;
            current = current.Next;
            iterator++;
        }
    }    

    public IEnumerable<T> Last(int count)
    {
        if (!CountIsInBounds(count))
        {
            throw new ArgumentOutOfRangeException();
        }

        LinkedListNode<T> current = this.byInsertion.Last;

        int iterator = 0;
        while (iterator < count)
        {
            yield return current.Value;
            current = current.Previous;
            iterator++;
        }
    }

    public IEnumerable<T> Max(int count)
    {
        if (!CountIsInBounds(count))
        {
            throw new ArgumentOutOfRangeException();
        }

        return this.byAscending.Reverse().Take(count);
    }

    public IEnumerable<T> Min(int count)
    {
        if (!CountIsInBounds(count))
        {
            throw new ArgumentOutOfRangeException();
        }

        return this.byAscending.Take(count);
    }

    public int RemoveAll(T element)
    {
        int count = this.byAscending.RemoveAllCopies(element);
        if (count != 0)
        {
            LinkedListNode<T> current = this.byInsertion.Last;
            
            while (current != null)
            {
                LinkedListNode<T> temp = current;
                current = current.Next;
                if (temp.Value.CompareTo(element) == 0)
                {
                    byInsertion.Remove(temp);
                }                
            }
        }

        return count;
    }

    private bool CountIsInBounds(int count)
    {
        return count >= 0 && count <= this.Count;
    }
}
