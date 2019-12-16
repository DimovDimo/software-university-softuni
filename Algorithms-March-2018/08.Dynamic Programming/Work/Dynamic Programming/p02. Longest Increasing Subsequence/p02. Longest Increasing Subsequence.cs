using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02.Longest_Increasing_Subsequence
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] seq = Console.ReadLine()
                .Split()
                .Select(int.Parse)
                .ToArray();

            int[] len = new int[seq.Length];
            int[] prev = new int[seq.Length];

            int maxLen = 0;
            int lastIndex = -1;

            for (int x = 0; x < seq.Length; x++)
            {
                len[x] = 1;
                prev[x] = -1;
                for (int i = 0; i < x; i++)
                {
                    if ((seq[i] < seq[x]) && (len[i] + 1 > len[x]))
                    {
                        len[x] = len[i] + 1;
                        prev[x] = i;
                    }
                }

                if (len[x] > maxLen)
                {
                    maxLen = len[x];
                    lastIndex = x;
                }
            }

            List<int> longestSeq = new List<int>();

            while (lastIndex != -1)
            {
                longestSeq.Add(seq[lastIndex]);
                lastIndex = prev[lastIndex];
            }

            longestSeq.Reverse();

            Console.WriteLine(string.Join(" ", longestSeq));
        }
    }
}
