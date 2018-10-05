using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _02.Sort
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> words = Console.ReadLine()
                .Split(' ')
                .ToList();

            words.Sort();

            Console.WriteLine(String.Join(" ", words));
        }
    }
}
