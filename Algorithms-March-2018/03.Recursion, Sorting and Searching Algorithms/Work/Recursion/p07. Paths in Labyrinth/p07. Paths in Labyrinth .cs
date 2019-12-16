using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p07.Paths_in_Labyrinth
{
    class Program
    {
        static List<char> path = new List<char>();
        private static char[,] lab;

        static void Main(string[] args)
        {
            int rows = int.Parse(Console.ReadLine());
            int cols = int.Parse(Console.ReadLine());
            lab = ReadLab(rows, cols);
            FindPath(0, 0, 'S');
        }

        private static char[,] ReadLab(int rows, int cols)
        {
            char[,] lab = new char[rows, cols];
            for (int row = 0; row < rows; row++)
            {
                char[] line = Console.ReadLine().ToCharArray();
                for (int col = 0; col < cols; col++)
                {
                    lab[row, col] = line[col];
                }
            }

            return lab;
        }

        private static void FindPath(int row, int col, char direction)
        {
            if(!IsInBounds(row, col))
            {
                return;
            }

            path.Add(direction);

            if (IsExit(row, col))
            {
                PrintPath();
            }
            else if(!IsVisited(row, col) && IsFree(row, col))
            {
                Mark(row, col);
                FindPath(row, col + 1, 'R');
                FindPath(row + 1, col, 'D');
                FindPath(row, col - 1, 'L');
                FindPath(row - 1, col, 'U');
                Unmark(row, col);
            }

            path.RemoveAt(path.Count - 1);
        }

        private static bool IsFree(int row, int col)
        {
            return lab[row, col] == '-';
        }

        private static bool IsVisited(int row, int col)
        {
            return lab[row, col] == 'x';
        }

        private static void Unmark(int row, int col)
        {
            lab[row, col] = '-';
        }

        private static void Mark(int row, int col)
        {
            lab[row, col] = 'x';
        }

        private static void PrintPath()
        {
            Console.WriteLine(string.Join("", path).TrimStart('S'));
        }

        private static bool IsExit(int row, int col)
        {
            return lab[row, col] == 'e';
        }

        private static bool IsInBounds(int row, int col)
        {
            return 0 <= row && row < lab.GetLength(0)
                && 0 <= col && col < lab.GetLength(1)
                && lab[row, col] != '*';
        }
    }
}
