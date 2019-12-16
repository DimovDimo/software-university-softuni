using System;

namespace Dynamic_Programming
{
    class Program
    {
        static void Main(string[] args)
        {
            long n = long.Parse(Console.ReadLine());

            long[] memorization = new long[n + 1];

            Console.WriteLine(Fibonacy(n, memorization));
        }

        private static long Fibonacy(long n, long[] memorization)
        {
            if (memorization[n] != 0)
            {
                return memorization[n];
            }

            if (n <= 2)
            {
                return 1;
            }

            memorization[n] = Fibonacy(n - 1, memorization) + Fibonacy(n - 2, memorization); ;
            return memorization[n];
        }
    }
}
