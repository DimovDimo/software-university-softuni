using System;
using System.Collections.Generic;

class CalculateSequence
{
    static void Main(string[] args)
    {
        int num = int.Parse(Console.ReadLine());

        Queue<long> queue = new Queue<long>();
        queue.Enqueue(num);

        List<long> result = new List<long>();
        for (int i = 0; i < 50; i++)
        {
            long currentNum = queue.Dequeue();
            queue.Enqueue(currentNum + 1);
            queue.Enqueue(currentNum * 2 + 1);
            queue.Enqueue(currentNum + 2);
            result.Add(currentNum);
        }

        Console.WriteLine(string.Join(", ", result));
    }
}