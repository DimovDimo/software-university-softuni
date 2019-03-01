using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04.Towers_of_Hanoi
{
    class Program
    {
        private static int stepsTaken = 0;

        private static Stack<int> source;
        private static Stack<int> destination = new Stack<int>();
        private static Stack<int> spare = new Stack<int>();

        public static void Main(string[] args)
        {
            int numbeOfDisks = int.Parse(Console.ReadLine());
            source = new Stack<int>(Enumerable.Range(1, numbeOfDisks).Reverse());
            PrintRods();
            MoveDisks(numbeOfDisks, source, spare, destination);
        }

        private static void MoveDisks(int bottomDisk, Stack<int> source, Stack<int> spare, Stack<int> destination)
        {
            if (bottomDisk == 1)
            {
                stepsTaken++;
                int disk = source.Pop();
                destination.Push(disk);
                Console.WriteLine($"Step #{stepsTaken}: Moved disk");
                PrintRods();
            }
            else
            {
                MoveDisks(bottomDisk - 1, source, destination, spare);
                destination.Push(source.Pop());
                stepsTaken++;

                Console.WriteLine($"Step #{stepsTaken}: Moved disk");

                PrintRods();
                MoveDisks(bottomDisk - 1, spare, source, destination);
            }            
        }

        private static void PrintRods()
        {
            Console.WriteLine($"Source: {string.Join(", ", source.Reverse())}");
            Console.WriteLine($"Destination: {string.Join(", ", destination.Reverse())}");
            Console.WriteLine($"Spare: {string.Join(", ", spare.Reverse())}");
            Console.WriteLine();
        }
    }
}
