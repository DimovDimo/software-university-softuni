using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

class ReverseNumbers
{
    static void Main(string[] args)
    {
        var input = Console.ReadLine().Split();

        if (input.Length == 0)
        {
            return;
        }

        Stack<int> stack = new Stack<int>();

        for (int i = 0; i < input.Length; i++)
        {
            var numbers = int.Parse(input[i]);
            stack.Push(numbers);
        }

        while (stack.Count != 0)
        {
            Console.Write("{0} ", stack.Pop());
        }
    }
}