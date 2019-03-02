using System;

namespace p07.N_Choose_K_Count
{
    class Program
    {
        static void Main(string[] args)
        {
            long objects = Int64.Parse(Console.ReadLine());
            long sample = Int64.Parse(Console.ReadLine());
            Print(BinomialCal(objects, sample));
        }

        private static long NChooseKCount(long objects, long sample)
        {
            return factorial(objects) /
                (factorial(sample) * factorial(objects - sample));
        }

        private static long BinomialCal(long n, long k)
        {
            if (k > n)
            {
                return 0;
            }
            if (k == n || k == 0)
            {
                return 1;
            }

            return BinomialCal(n - 1, k - 1) + BinomialCal(n - 1, k);
        }

        private static void Print(long p)
        {
            Console.WriteLine(p);
        }

        public static long factorial(long number)
        {
            long fact = 1;
            for (long i = number; i >= 1; i--)
            {
                fact = fact * i;
            }

            return fact;
        }
    }
}
