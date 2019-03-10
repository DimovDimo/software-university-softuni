using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03.Move_Down_or_Right
{
    class Program
    {
        private static int[,] matrix;
        private static int[,] sumMatrix;
        private static List<string> way = new List<string>();

        static void Main(string[] args)
        {
            int rows = int.Parse(Console.ReadLine());
            int cols = int.Parse(Console.ReadLine());

            matrix = new int[rows, cols];

            for (int row = 0; row < rows; row++)
            {
                var line = Console.ReadLine()
                    .Split()
                    .Select(int.Parse)
                    .ToArray();

                for (int col = 0; col < line.Length; col++)
                {
                    matrix[row, col] = line[col];
                }
            }

            sumMatrix = new int[rows, cols];

            for (int row = 0; row < rows; row++)
            {
                for (int col = 0; col < cols; col++)
                {
                    sumMatrix[row, col] = GetSum(row, col);
                }
            }

            PrintWay(rows, cols);
        }

        private static void PrintWay(int rows, int cols)
        {
            GetWay(rows - 1, cols - 1);
            way.Reverse();
            Console.WriteLine(string.Join(" ", way.ToArray()));
        }

        private static void GetWay(int row, int col)
        {
            try
            {
                var test = matrix[row, col];

                way.Add($"[{row}, {col}]");

                //int right = TryGetValue(row, col + 1);
                //int down = TryGetValue(row + 1, col);
                //
                //if (down >= right)
                //{
                //    GetWay(row + 1, col);
                //}
                //else
                //{
                //    GetWay(row, col + 1);
                //}

                int left = TryGetValue(row, col - 1);
                int up = TryGetValue(row - 1, col);

                if (left >= up)
                {
                    GetWay(row, col - 1);
                }
                else
                {
                    GetWay(row - 1, col);
                }
            }
            catch
            {
                return;
            }
        }

        private static int GetSum(int row, int col)
        {
            int up = TryGetValue(row - 1, col);
            int left = TryGetValue(row, col - 1);
            int current = matrix[row, col];

            return Math.Max(up, left) + current;
        }

        private static int TryGetValue(int row, int col)
        {
            try
            {
                return sumMatrix[row, col];
            }
            catch
            {
                return 0;
            }
        }
    }
}
