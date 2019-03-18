using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Dynamic_Programming_Part_II___Exercise
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] second = Console.ReadLine().Split().Select(int.Parse).ToArray();
            int[] first = new int[second.Length];

            for(int i = 0; i < first.Length; i++)
            {
                first[i] = i + 1;
            }

            int size = first.Length + 1;
            int[,] mtrix = new int[size, size];

            for (int row = 1; row <= first.Length; row++)
            {
                for (int col = 1; col <= second.Length; col++)
                {
                    int insert = Math.Max(mtrix[row - 1, col], mtrix[row, col - 1]);
                    if(first[col - 1] == second[row - 1])
                    {
                        insert = mtrix[row - 1, col - 1] + 1;
                    }

                    mtrix[row, col] = insert;
                }
            }

            Console.WriteLine("Maximum pairs connected: " + mtrix[size - 1, size - 1]);

            //for (int row = 0; row <= first.Length; row++)
            //{
            //    for (int col = 0; col <= second.Length; col++)
            //    {
            //        Console.Write(mtrix[row, col] + " ");
            //    }
            //
            //    Console.WriteLine();
            //}
        }
    }
}
