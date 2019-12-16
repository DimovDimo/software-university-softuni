using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03.Variations_without_Repetition
{
    class Program
    {
        private static string[] elements;
        private static string[] vari;
        private static bool[] used;

        static void Main(string[] args)
        {
            elements = Console.ReadLine().Split().ToArray();
            int k = Int32.Parse(Console.ReadLine());
            vari = new string[k];
            used = new bool[elements.Length];
            Variations(0);
        }

        public static void Variations(int index)
        {
            if (index >= vari.Length)
            {
                Console.WriteLine(string.Join(" ", vari));
            }
            else
            {
                for (int i = 0; i < elements.Length; i++)
                {
                    if (!used[i])
                    {
                        used[i] = true;
                        vari[index] = elements[i];
                        Variations(index + 1);
                        used[i] = false;
                    }
                }
            }
        }
    }
}
