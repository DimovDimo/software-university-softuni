using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

class Program
{
    public static void Main(string[] args)
    {
        TextEditor textEditor = new TextEditor();
        while (true)
        {
            var input = Console.ReadLine();
            var text = input.Split('"');
            var commands = input.Split();
            if (commands[0] == "end")
            {
                break;
            }

            switch (commands[0])
            {
                case "login":
                    textEditor.Login(commands[1]);
                    break;
                case "logout":
                    textEditor.Logout(commands[1]);
                    break;
                case "users":
                    if (commands.Length > 1)
                    {
                        foreach (var user in textEditor.Users(commands[1]))
                        {
                            Console.WriteLine(user);
                        }
                    }
                    else
                    {
                        foreach (var user in textEditor.Users())
                        {
                            Console.WriteLine(user);
                        }
                    }
                    break;
                default:
                    var name = commands[0];
                    var command = commands[1];

                    switch (command)
                    {
                        case "insert":
                            var index = int.Parse(commands[2]);
                            textEditor.Insert(name, index, text[1]);
                            break;
                        case "prepend":
                            textEditor.Prepend(name, text[1]);
                            break;
                        case "substring":
                            var startIndex = int.Parse(commands[2]);
                            var length = int.Parse(commands[3]);
                            textEditor.Substring(name, startIndex, length);
                            break;
                        case "delete":
                            var startIndexD = int.Parse(commands[2]);
                            var lengthD = int.Parse(commands[3]);
                            textEditor.Delete(name, startIndexD, lengthD);
                            break;
                        case "clear":
                            textEditor.Clear(name);
                            break;
                        case "length":
                            Console.WriteLine(textEditor.Length(name));
                            break;
                        case "print":
                            try
                            {
                                Console.WriteLine(textEditor.Print(name));
                            }
                            catch (Exception)
                            {
                            }
                            break;
                        case "undo":
                            textEditor.Undo(name);
                            break;
                        default: throw new InvalidOperationException();
                    }
                    break;
            }
        }
    }
}
