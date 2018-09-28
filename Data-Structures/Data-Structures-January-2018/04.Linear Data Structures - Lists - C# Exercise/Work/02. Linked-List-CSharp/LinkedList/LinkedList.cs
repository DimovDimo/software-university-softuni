using System;
using System.Collections;
using System.Collections.Generic;

public class LinkedList<T> : IEnumerable<T>
{
    public int Count { get; private set; }

    public void AddFirst(T item)
    {
        // TODO
        throw new NotImplementedException();
    }

    public void AddLast(T item)
    {
        // TODO
        throw new NotImplementedException();
    }

    public T RemoveFirst()
    {
        // TODO: Throw exception if the list is empty
        throw new NotImplementedException();
    }

    public T RemoveLast()
    {
        // TODO: Throw exception if the list is empty
        throw new NotImplementedException();
    }

    public IEnumerator<T> GetEnumerator()
    {
        // TODO
        throw new NotImplementedException();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        // TODO
        throw new NotImplementedException();
    }
}
