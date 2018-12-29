using System;

public class LinkedStack<T>
{
    private Node<T> firstNode;

    public int Count { get; private set; }

    public void Push(T element)
    {
        this.firstNode = new Node<T>(element, this.firstNode);
        this.Count++;
    }

    public T Pop()
    {
        if (this.Count == 0)
        {
            throw new InvalidOperationException();
        }

        Node<T> first = this.firstNode;
        this.firstNode = first.NextNode;
        this.Count--;

        return first.Value;
    }

    public T[] ToArray()
    {
        T[] array = new T[this.Count];

        int position = 0;
        Node<T> currentNode = this.firstNode;

        while (currentNode != null)
        {
            array[position++] = currentNode.Value;
            currentNode = currentNode.NextNode;
        }

        return array;
    }

    private class Node<T>
    {
        public T Value { get; }
        public Node<T> NextNode { get; set; }

        public Node(T value, Node<T> nextNode = null)
        {
            this.Value = value;
            this.NextNode = nextNode;
        }
    }
}
