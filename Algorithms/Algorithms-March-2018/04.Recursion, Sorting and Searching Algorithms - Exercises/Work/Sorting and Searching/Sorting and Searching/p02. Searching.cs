using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sorting_and_Searching
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] arr = Console.ReadLine()
                .Split()
                .Select(int.Parse)
                .ToArray();

            int search = Int32.Parse(Console.ReadLine());
            bool isOut = true;
            for(int i = 0; i < arr.Length; i++)
            {
                if(arr[i] == search)
                {
                    Console.WriteLine(i);
                    isOut = false;
                    break;
                }
            }

            if(isOut) Console.WriteLine(-1);
        }
    }
}
