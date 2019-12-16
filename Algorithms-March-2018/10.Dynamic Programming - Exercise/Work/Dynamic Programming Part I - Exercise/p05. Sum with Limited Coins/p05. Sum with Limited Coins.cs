using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p05.Sum_with_Limited_Coins
{
    class Program
    {
        private static int sum;
        private static int[] coins;
        private static HashSet<string> distinctComb = new HashSet<string>();
        private static List<int> combs = new List<int>();

        static void Main(string[] args)
        {
            coins = Console.ReadLine().Split().Select(int.Parse).Reverse().ToArray();
            sum = int.Parse(Console.ReadLine());
            Combinations(0, sum);
            Result();
        }

        private static void Result()
        {
            Console.WriteLine(distinctComb.Count());
        }

        private static void Combinations(int index, int different)
        {
            for (int i = index; i < coins.Length; i++)
            {
                int currentNum = coins[i];
                if (different - currentNum == 0)
                {
                    combs.Add(currentNum);
                    distinctComb.Add(string.Join(" ", combs));
                    Console.WriteLine(string.Join(" ", combs));
                    //combs.Clear();
                }
            
                Combinations(i + 1, different - currentNum);
            }

            //for (int i = index; i < coins.Length; i++)
            //{
            //    int currentNum = coins[i];
            //    Console.Write(currentNum + " ");
            //
            //    Combinations(i + 1, different);
            //    Console.WriteLine();
            //}
        }
    }
}
