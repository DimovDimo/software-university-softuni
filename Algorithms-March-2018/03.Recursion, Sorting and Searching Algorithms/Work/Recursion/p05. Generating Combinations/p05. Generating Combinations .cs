using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p05.Generating_Combinations
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] set = Console.ReadLine()
                .Split(' ')
                .Select(x => Int32.Parse(x))
                .ToArray();
            int n = Int32.Parse(Console.ReadLine());
            int[] vector = new int[n];
            GenComb(set, vector, 0, 0);
        }

        private static void GenComb(int[] set, int[] vector, int index, int border)
        {
            if (vector.Length == index)
            {
                Console.WriteLine(string.Join(" ", vector));
            }
            else
            {
                for (int i = border + 0; i < set.Length; i++)
                {
                    vector[index] = set[i];
                    GenComb(set, vector, index + 1, i + 1);
                }
            }
        }
    }
}
