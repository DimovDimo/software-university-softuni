using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Dynamic_Programming_Part_I___Exercise
{
    class Program
    {
        private static List<List<int>> matrix = new List<List<int>>();

        static void Main(string[] args)
        {
            int rows = int.Parse(Console.ReadLine());
            int cols = int.Parse(Console.ReadLine());
            
            for(int row = 0; row <= rows; row++)
            {
                matrix.Add(new List<int>());
                for (int col = 0; col <= row; col++)
                {
                    int upLeft = TryGetUpLeft(row, col);
                    int upRight = TryGetUpRight(row, col);
                    matrix.ElementAt(row).Add(upLeft + upRight);
                }
            }

            Console.WriteLine(matrix.ElementAt(rows).ElementAt(cols));
        }

        public static int TryGetUpLeft(int row, int col)
        {
            try
            {
                return matrix.ElementAt(row - 1).ElementAt(col - 1);
            }
            catch
            {
                return 0;
            }
        }

        public static int TryGetUpRight(int row, int col)
        {
            if(row == 0)
            {
                return 1;
            }

            try
            {
                return matrix.ElementAt(row - 1).ElementAt(col);
            }
            catch
            {
                return 0;
            }
        }
    }
}
