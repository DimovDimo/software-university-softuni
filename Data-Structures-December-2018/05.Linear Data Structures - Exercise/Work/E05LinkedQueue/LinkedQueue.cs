using System;

public class LinkedQueue<T>
{
    private QueueNode<T> head;
    private QueueNode<T> tail;

    public int Count { get; private set; }

    public void Enqueue(T element)
    {
        if (this.Count == 0)
        {
            this.head = this.tail = new QueueNode<T>(element);
        }
        else
        {
            var newNode = new QueueNode<T>(element);
            this.tail.NextNode = newNode;
            newNode.PrevNode = this.tail;
            this.tail = newNode;
        }

        this.Count++;
    }

    public T Dequeue()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException();
        }

        var element = this.head;
        this.head = element.NextNode;

        this.Count--;

        return element.Value;
    }

    public T[] ToArray()
    {
        T[] array = new T[this.Count];
        int pos = 0;
        var current = this.head;

        while (current != null)
        {
            array[pos++] = current.Value;
            current = current.NextNode;
        }

        return array;
    }

    private class QueueNode<T>
    {
        public T Value { get; private set; }
        public QueueNode<T> NextNode { get; set; }
        public QueueNode<T> PrevNode { get; set; }

        public QueueNode(T value)
        {
            this.Value = value;
        }
    }
}
