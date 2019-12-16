using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Recursion
{
    class Program
    {
        static void Main(string[] args)
        {
            var arr = Console.ReadLine()
                .Split()
                .Select(int.Parse)
                .ToArray();

            ReverseArray(arr, 0);
        }

        private static void ReverseArray(int[] arr, int index)
        {
            if(arr.Length - 1 > index)
            {
                ReverseArray(arr, index + 1);
            }   

            Console.Write(arr[index] + " ");
        }
    }
}
