using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03.Recursive_Drawing
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = Int32.Parse(Console.ReadLine());
            PrintFigure(n);
        }

        private static void PrintFigure(int n)
        {
            if(n <= 0)
            {
                return;
            }

            Console.WriteLine(new string('*', n));
            PrintFigure(n - 1);
            Console.WriteLine(new string('#', n));
        }
    }
}
