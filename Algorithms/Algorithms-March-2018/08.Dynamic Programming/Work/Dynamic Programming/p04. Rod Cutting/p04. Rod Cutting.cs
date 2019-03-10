using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04.Rod_Cutting
{
    class Program
    {
        private static int[] price;
        private static int[] bestPrice;
        private static int[] bestCombo;

        static void Main(string[] args)
        {
            price = Console.ReadLine().Split()
                .Select(int.Parse).ToArray();

            bestPrice = new int[price.Length];
            bestCombo = new int[price.Length];

            int n = int.Parse(Console.ReadLine());
            CutRod(n);
            ReconstructSolution(n);
        }

        private static int CutRod(int n)
        {
            if (bestPrice[n] != 0)
            {
                return bestPrice[n];
            }

            if (n == 0)
            {
                return 0;
            }

            //var currentBest = bestPrice[n];
            var currentBest = -1;
            for (int i = 1; i <= n; i++)
            {
                currentBest = Math.Max(currentBest, price[i] + CutRod(n - i));

                if (currentBest > bestPrice[n])
                {
                    bestPrice[n] = currentBest;
                    bestCombo[n] = i;
                }
            }

            return bestPrice[n];
        }

        private static void ReconstructSolution(int n)
        {
            Console.WriteLine(bestPrice[n]);
            while (n - bestCombo[n] != 0)
            {
                Console.Write(bestCombo[n] + " ");
                n = n - bestCombo[n];
            }

            Console.WriteLine(bestCombo[n]);
        }
    }
}
