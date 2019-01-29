using System;
using System.Collections.Generic;
using Wintellect.PowerCollections;
using System.Linq;

public class FirstLastList<T> : IFirstLastList<T> where T : IComparable<T>
{
    private List<T> elements = new List<T>();
    private OrderedBag<T> ordaredElements = new OrderedBag<T>();
    private OrderedBag<T> reverseOrdaredElements = new OrderedBag<T>((x, y) => -x.CompareTo(y));

    public int Count
    {
        get
        {
            return this.elements.Count;
        }
    }

    public void Add(T element)
    {
        this.elements.Add(element);
        this.ordaredElements.Add(element);
        this.reverseOrdaredElements.Add(element);
    }

    public void Clear()
    {
        this.elements.Clear();
        this.ordaredElements.Clear();
        this.reverseOrdaredElements.Clear();
    }

    public IEnumerable<T> First(int count)
    {
        if (this.Count < count)
        {
            throw new ArgumentOutOfRangeException();
        }

        return this.elements.Take(count);
    }

    public IEnumerable<T> Last(int count)
    {
        if (this.Count < count)
        {
            throw new ArgumentOutOfRangeException();
        }

        return this.elements.Skip(Math.Max(0, this.elements.Count - count)).Reverse();
    }

    public IEnumerable<T> Max(int count)
    {
        if (this.Count < count)
        {
            throw new ArgumentOutOfRangeException();
        }

        return this.reverseOrdaredElements.Take(count);
    }

    public IEnumerable<T> Min(int count)
    {
        if (this.Count < count)
        {
            throw new ArgumentOutOfRangeException();
        }

        return this.ordaredElements.Take(count);
    }

    public int RemoveAll(T element)
    {
        foreach (var item in this.ordaredElements.Range(element, true, element, true))
        {
            this.elements.Remove(item);
        }
        int result = this.ordaredElements.RemoveAllCopies(element);
        this.reverseOrdaredElements.RemoveAllCopies(element);

        return result;
    }
}
