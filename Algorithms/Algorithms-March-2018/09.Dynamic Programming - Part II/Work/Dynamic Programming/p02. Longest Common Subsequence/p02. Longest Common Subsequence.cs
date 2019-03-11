using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02.Longest_Common_Subsequence
{
    class Program
    {
        static void Main(string[] args)
        {
            string first = Console.ReadLine();
            string second = Console.ReadLine();
            int[,] lcs = new int[first.Length + 1, second.Length + 1];

            for(int row = 1; row <= first.Length; row++)
            {
                for (int col = 1; col <= second.Length; col++)
                {
                    int up = lcs[row - 1, col];
                    int left = lcs[row, col - 1];
                    int maxOfUpLeft = Math.Max(up, left);
                    
                    if (first[row - 1] == second[col - 1])
                    {
                        int diagonal = lcs[row - 1, col - 1] + 1;
                        lcs[row, col] = Math.Max(diagonal, maxOfUpLeft);
                    }
                    else
                    {
                        lcs[row, col] = maxOfUpLeft;
                    }
                }
            }

            Console.WriteLine(lcs[first.Length, second.Length]);
        }
    }
}
