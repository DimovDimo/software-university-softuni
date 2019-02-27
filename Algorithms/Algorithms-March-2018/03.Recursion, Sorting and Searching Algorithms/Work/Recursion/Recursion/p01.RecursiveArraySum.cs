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
            int[] arr = Console.ReadLine()
                .Split(' ')
                .Select(x => Int32.Parse(x))
                .ToArray();

            int sum = Sum(arr, 0);

            Console.WriteLine(sum);
        }

        private static int Sum(int[] arr, int index)
        {
            if(arr.Length == index)
            {
                return 0;
            }

            return arr[index] + Sum(arr, index + 1);
        }
    }
}
