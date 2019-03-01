using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02.Nested_Loops
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = Int32.Parse(Console.ReadLine());
            int[] vector = new int[n];
            GenZeroOne(vector, 0, n);
        }

        private static void GenZeroOne(int[] vector, int index, int n)
        {
            if (vector.Length == index)
            {
                Console.WriteLine(string.Join(" ", vector));
            }
            else
            {
                for (int i = 0; i < n; i++)
                {
                    vector[index] = i + 1;
                    GenZeroOne(vector, index + 1, n);
                }
            }
        }
    }
}
