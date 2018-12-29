using System;
using System.Collections.Generic;

public class Sequence
{
    static void Main(string[] args)
    {
        var input = Console.ReadLine().Split();
        long n = long.Parse(input[0]);
        long m = long.Parse(input[1]);

        Queue<Item> numbersQueue = new Queue<Item>();
        numbersQueue.Enqueue(new Item(n));

        while (numbersQueue.Count != 0)
        {
            Item currentItem = numbersQueue.Dequeue();

            if (currentItem.Value < m)
            {
                numbersQueue.Enqueue(new Item(currentItem.Value + 1, currentItem));
                numbersQueue.Enqueue(new Item(currentItem.Value + 2, currentItem));
                numbersQueue.Enqueue(new Item(currentItem.Value * 2, currentItem));
            }

            if (currentItem.Value == m)
            {
                printSolution(currentItem);
                return;
            }
        }
    }

    private static void printSolution(Item currentItem)
    {
        List<long> result = new List<long>();

        while (currentItem.PrevItem != null)
        {
            result.Add(currentItem.Value);
            currentItem = currentItem.PrevItem;
        }

        result.Add(currentItem.Value);
        result.Reverse();
        Console.WriteLine(string.Join(" -> ", result));
    }

    private class Item
    {
        public long Value { get; }
        public Item PrevItem { get; }

        public Item(long value, Item prevItem = null)
        {
            this.Value = value;
            this.PrevItem = prevItem;
        }
    }
}