using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04.Generating_01_Vectors
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = Int32.Parse(Console.ReadLine());
            int[] vector = new int[n];
            GenZeroOne(vector, 0);
        }

        private static void GenZeroOne(int[] vector, int index)
        {
            if(vector.Length == index)
            {
                Console.WriteLine(string.Join("", vector));
            }
            else
            {
                for(int i = 0; i <= 1; i++)
                {
                    vector[index] = i;
                    GenZeroOne(vector, index + 1);
                }
            }
        }
    }
}
