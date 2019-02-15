using System;
using System;
using System.Linq;

namespace Palindrome_Integers
{
    class Program
    {
        static void Main(string[] args)
        {
            string command = string.Empty;
            IsItPalindrome(command);
        }

        private static void IsItPalindrome(string command)
        {
            string reversed = string.Empty;
            while (command != "END")
            {
                command = Console.ReadLine();
                if (command == "END")
                {
                    break;
                }

                for (int i = command.Length - 1; i >= 0; i--)
                {
                    reversed += command[i];
                }

                if (reversed != command)
                {
                    Console.WriteLine("false");
                }
                else if (reversed == command)
                {
                    Console.WriteLine("true");
                }

                //Before: reversed is not empty
                reversed = string.Empty;
                //After: reversed is empty
            }
        }
    }
}
