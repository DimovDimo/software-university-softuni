using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03.Combinations_with_Repetition
{
    class Program
    {
        static void Main(string[] args)
        {
            int count = Int32.Parse(Console.ReadLine());
            int col = Int32.Parse(Console.ReadLine());
            int[] vector = new int[col];
            GenZ(vector, 0, 1, count);
        }

        private static void GenZ(int[] vector, int index, int start, int to) 
        {
            if (vector.Length == index)
            {
                Console.WriteLine(string.Join(" ", vector));
            }
            else
            {
                for (int i = start; i <= to; i++)
                {
                    vector[index] = i;
                    GenZ(vector, index + 1, i, to);
                }
            }
        }
    }
}
