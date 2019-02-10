using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Phonebook
{
    class Program
    {
        static void Main(string[] args)
        {
            Dictionary<string, string> dictionary = new Dictionary<string, string>();
            string keyValue = Console.ReadLine();
            while (keyValue != "search")
            {
                string[] elements = keyValue.Split('-');
                string person = elements[0];
                string number = elements[1];
                dictionary[person] = number;
                keyValue = Console.ReadLine();
            }

            string search = Console.ReadLine();
            while (search != "end")
            {
                if (dictionary.TryGetValue(search, out string value))
                {
                    Console.WriteLine(search + " -> " + value);
                }
                else
                {
                    Console.WriteLine($"Contact {search} does not exist.");
                }

                search = Console.ReadLine();
            }
        }
    }
}
