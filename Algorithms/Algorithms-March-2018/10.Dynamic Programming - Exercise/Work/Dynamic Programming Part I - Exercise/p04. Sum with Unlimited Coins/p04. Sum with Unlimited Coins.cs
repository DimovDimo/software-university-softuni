using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04.Sum_with_Unlimited_Coins
{
    class Program
    {
        private static int countComb;
        private static int sum;
        private static int[] coins;
        private static List<int> getCoins = new List<int>();
        private static StringBuilder comb = new StringBuilder();

        static void Main(string[] args)
        {
            coins = Console.ReadLine().Split().Select(int.Parse).Reverse().ToArray();
            sum = int.Parse(Console.ReadLine());
            Combinations(0, sum);
            Result();
        }

        private static void Result()
        {
            Console.WriteLine(countComb);
            //Console.WriteLine($"The {countComb} combinations are:");
            //Console.WriteLine(comb.ToString());
        }

        private static void Combinations(int index, int different)
        {
            if (index >= coins.Length)
            {
                return;
            }
            else if (different == 0)
            {
                MakePrint();
            }
            else if (different > 0)
            {
                int currentNum = coins[index];
                if (different - currentNum >= 0)
                {
                    //getCoins.Add(currentNum);
                    Combinations(index, different - currentNum);
                }
                
                Combinations(index + 1, different);
            }
        }

        private static void MakePrint()
        {
            countComb++;
            //comb.AppendLine($"{sum} = " + string.Join(" + ", getCoins));
            //getCoins.Clear();
        }
    }
}
