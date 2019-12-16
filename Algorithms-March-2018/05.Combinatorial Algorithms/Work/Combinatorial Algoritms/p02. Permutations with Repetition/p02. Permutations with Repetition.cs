using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02.Permutations_with_Repetition
{
    class Program
    {
        //private static HashSet<string> memory = new HashSet<string>();
        private static List<string> byInsertion = new List<string>();
        private static string[] elements;

        static void Main(string[] args)
        {
            elements = Console.ReadLine().Split().ToArray();
            Permute(0);
            Print();
        }

        private static void Print()
        {
            foreach(var item in byInsertion)
            {
                Console.WriteLine(item);
            }
        }

        public static void Permute(int index)
        {
            if (index >= elements.Length)
            {
                var element = string.Join(" ", elements);
                if (!byInsertion.Contains(element))
                {
                    byInsertion.Add(element);
                }
                //if (!memory.Contains(element))
                //{
                //    memory.Add(element);
                //    byInsertion.Add(element);
                //}
            }
            else
            {
                Permute(index + 1);
                for (int i = index + 1; i < elements.Length; i++)
                {
                    Swap(index, i);
                    Permute(index + 1);
                    Swap(index, i);
                }
            }
        }

        private static void Swap(int index, int i)
        {
            var temp = elements[index];
            elements[index] = elements[i];
            elements[i] = temp;
        }
    }
}
