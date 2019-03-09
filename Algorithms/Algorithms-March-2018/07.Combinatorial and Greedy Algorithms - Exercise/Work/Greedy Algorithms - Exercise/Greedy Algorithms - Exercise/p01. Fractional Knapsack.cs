using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Greedy_Algorithms___Exercise
{
    class Program
    {
        static void Main(string[] args)
        {
            int capacity = Int32.Parse(Console.ReadLine().Split(' ').ToArray()[1]);
            int itemsCount = Int32.Parse(Console.ReadLine().Split(' ').ToArray()[1]);

            Dictionary<double, List<double>> items = new Dictionary<double, List<double>>();
            for (int i = 0; i < itemsCount; i++)
            {
                string[] tokens = Console.ReadLine().Split(' ').ToArray();
                double key = Int32.Parse(tokens[0]);
                double value = Int32.Parse(tokens[2]);
                if (!items.ContainsKey(key))
                {
                    items.Add(key, new List<double>());
                }

                items[key].Add(value);
            }

            bool isBreak = false;
            double usedCapacity = 0;
            Dictionary<double, List<double>> GetItems = new Dictionary<double, List<double>>();
            foreach (var item in items.OrderByDescending(x => x.Key))
            {
                foreach (var token in item.Value.OrderBy(x => x))
                {
                    if (capacity <= usedCapacity)
                    {
                        isBreak = true;
                        break;
                    }
                    else
                    {
                        double freeCapacity = capacity - usedCapacity;
                        if (token <= freeCapacity)
                        {
                            Console.WriteLine($"Take 100% of item with price {item.Key:f2} and weight {token:f2}");
                            if (!GetItems.ContainsKey(item.Key))
                            {
                                GetItems.Add(item.Key, new List<double>());
                            }

                            GetItems[item.Key].Add(1);
                            usedCapacity += token;
                        }
                        else
                        {
                            double fillSpace = freeCapacity / token;
                            Console.WriteLine($"Take {fillSpace * 100:f2}% of item with price {item.Key:f2} and weight {token:f2}");
                            if (!GetItems.ContainsKey(item.Key))
                            {
                                GetItems.Add(item.Key, new List<double>());
                            }

                            GetItems[item.Key].Add(fillSpace);
                            isBreak = true;
                            break;
                        }
                    }
                }

                if (isBreak)
                {
                    break;
                }
            }

            double totalPrice = 0;
            foreach (var item in GetItems)
            {
                foreach (var token in item.Value)
                {
                    totalPrice += (item.Key * token);
                }
            }

            Console.WriteLine($"Total price: {totalPrice:f2}");
        }
    }
}
