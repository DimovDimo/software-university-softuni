using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02.RecursiveFactorial
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = Int32.Parse(Console.ReadLine());
            Console.WriteLine(Factorial(n));
        }

        private static long Factorial(int n)
        {
            if(n == 0)
            {
                return 1;
            }

            return n * Factorial(n - 1);
        }
    }
}
