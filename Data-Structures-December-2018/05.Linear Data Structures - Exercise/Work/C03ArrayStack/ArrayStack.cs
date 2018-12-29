using System;
using System.Linq;

public class ArrayStack<T>
{
    private const int InitialCapacity = 16;

    private T[] elements;

    public int Count { get; private set; }

    public ArrayStack(int capacity = InitialCapacity)
    {
        this.elements = new T[InitialCapacity];
    }

    public void Push(T element)
    {
        if (this.Count >= this.elements.Length)
        {
            this.Grow();
        }
        this.elements[this.Count] = element;
        this.Count++;
    }

    public T Pop()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException();
        }
        this.Count--;

        var element = this.elements[this.Count];
        this.elements[this.Count] = default(T);

        return element;
    }

    public T[] ToArray()
    {
        var newArr = new T[this.Count];
        Array.Copy(this.elements, newArr, this.Count);

        return newArr.Reverse().ToArray();
    }

    private void Grow()
    {
        var newArr = new T[2 * this.elements.Length];
        Array.Copy(this.elements, newArr, this.Count);
        this.elements = newArr;
    }
}
