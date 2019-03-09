using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03.Knight_s_Tour
{
    class Program
    {
        private static int[,] board;
        private static int n;
        private static Position bestPosition;

        static void Main(string[] args)
        {
            n = Int32.Parse(Console.ReadLine());
            board = new int[n, n];
            KnightTour(0, 0, 1);
            Print();
        }

        private static void Print()
        {
            for (int row = 0; row < n; row++)
            {
                for (int col = 0; col < n; col++)
                {
                    Console.Write($"{board[row, col]}".PadLeft(4));
                }

                Console.WriteLine();
            }
        }

        private static void KnightTour(int row, int col, int step)
        {
            board[row, col] = step;
            Position position = GetPosition(row, col);
            if (!(position == null))
            {
                KnightTour(position.row, position.col, step + 1);
            }
        }

        private static Position GetPosition(int row, int col)
        {
            List<Position> positions = GetPossiblePositions(row, col);
            GetCountNextSteps(positions);
            bestPosition = positions
                .OrderBy(p => p.nextSteps)
                .ThenBy(p => p.priority)
                .FirstOrDefault();

            return bestPosition;
        }

        private static void GetCountNextSteps(List<Position> positions)
        {
            foreach (var p in positions)
            {
                p.nextSteps = GetPossiblePositions(p.row, p.col).Count();
            }
        }

        private static List<Position> GetPossiblePositions(int row, int col)
        {
            List<Position> positions = new List<Position>();
            positions.Add(new Position(1, row + 1, col + 2));
            positions.Add(new Position(2, row + 2, col + 1));
            positions.Add(new Position(3, row + 2, col - 1));
            positions.Add(new Position(4, row + 1, col - 2));
            positions.Add(new Position(5, row - 1, col - 2));
            positions.Add(new Position(6, row - 2, col - 1));
            positions.Add(new Position(7, row - 2, col + 1));
            positions.Add(new Position(8, row - 1, col + 2));

            List<Position> possiblePositions = new List<Position>();
            foreach (var p in positions)
            {
                if (IsPossible(p))
                {
                    possiblePositions.Add(p);
                }
            }

            return possiblePositions;
        }

        private static bool IsPossible(Position p)
        {
            try
            {
                return board[p.row, p.col] == 0;
            }
            catch
            {
                return false;
            }
            //return 0 <= p.row && p.row <= n
            //    && 0 <= p.col && p.col <= n
            //    && board[p.row, p.col] == 0;
        }

        private class Position
        {
            public int priority { get; set; }
            public int row { get; set; }
            public int col { get; set; }
            public int nextSteps { get; set; }

            public Position(int priority, int row, int col)
            {
                this.priority = priority;
                this.row = row;
                this.col = col;
            }

            public Position(int row, int col)
            {
                this.row = row;
                this.col = col;
            }

            public Position()
            {
            }
        }
    }
}
