using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ListsExe
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> nums = Console.ReadLine()
                .Split(' ')
                .Select(s => int.Parse(s))
                .ToList();

            double sum = nums.Sum();
            double average = nums.Average();
            Console.WriteLine($"Sum={sum}; Average={average:F2}");
        }
    }
}
