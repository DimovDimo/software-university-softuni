using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CountSymbol
{
    class Program
    {
        static void Main(string[] args)
        {
            var dictionary = new Dictionary<char, int>();
            var input = Console.ReadLine();
            foreach (char charElement in input)
            {
                if (!dictionary.ContainsKey(charElement))
                {
                    dictionary[charElement] = 0;
                }

                dictionary[charElement]++;
            }

            foreach (var keyValuePair in dictionary
                .OrderBy(x => x.Key))
            {
                Console.WriteLine(keyValuePair.Key + ": " 
                    + keyValuePair.Value + " time/s");
            }
        }
    }
}
