using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p05.Combinations_without_Repetition
{
    class Program
    {
        private static string[] elements;
        private static string[] comb;

        static void Main(string[] args)
        {
            elements = Console.ReadLine().Split().ToArray();
            int k = Int32.Parse(Console.ReadLine());
            comb = new string[k];
            Combinations(0, 0);
        }

        public static void Combinations(int index, int start)
        {
            if (index >= comb.Length)
            {
                PrintCombinations();
            }
            else
            {
                for (int i = start; i < elements.Length; i++)
                {
                    comb[index] = elements[i];
                    Combinations(index + 1, i + 1);
                }
            }
        }

        private static void PrintCombinations()
        {
            Console.WriteLine(string.Join(" ", comb));
        }
    }
}
