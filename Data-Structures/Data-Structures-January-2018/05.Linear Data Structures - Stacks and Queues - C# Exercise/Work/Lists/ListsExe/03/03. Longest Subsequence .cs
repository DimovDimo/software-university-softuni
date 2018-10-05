using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _03
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> nums = Console.ReadLine()
                .Split(' ')
                .Select(s => int.Parse(s))
                .ToList();

            List<int> longestSubsequence = new List<int>();
            List<int> currentSubsequence = new List<int>();
            int lastElement = 0;

            for (int i = 0; i < nums.Count; i++)
            {
                
            }

            if (longestSubsequence.Count < currentSubsequence.Count)
            {
                longestSubsequence = currentSubsequence;
            }

            Console.WriteLine(String.Join(" ", longestSubsequence));
        }
    }
}
