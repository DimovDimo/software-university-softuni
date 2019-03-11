using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Dynamic_Programming
{
    class Program
    {
        static void Main(string[] args)
        {
            int maxCapacity = int.Parse(Console.ReadLine());
            List<Item> itemsList = new List<Item>();
            string command;
            while ((command = Console.ReadLine()) != "end")
            {
                var tokens = command.Split();

                itemsList.Add(
                    new Item
                    {
                        Name = tokens[0],
                        Weight = int.Parse(tokens[1]),
                        Value = int.Parse(tokens[2])
                    });
            }

            Item[] items = itemsList
                .OrderBy(i => i.Name)
                .ToArray();

            var praices = new int[items.Length + 1, maxCapacity + 1];
            var itemIncluded = new bool[items.Length + 1, maxCapacity + 1];

            for (int i = 0; i < items.Length; i++)
            {
                var item = items[i];

                for (int capacity = 1; capacity <= maxCapacity; capacity++)
                {
                    if (item.Weight > capacity)
                    {
                        continue;
                    }

                    int included = item.Value + praices[i, capacity - item.Weight];

                    if (included > praices[i, capacity])
                    {
                        praices[i + 1, capacity] = included;
                        itemIncluded[i + 1, capacity] = true;
                    }
                    else
                    {
                        praices[i + 1, capacity] = praices[i, capacity];
                    }
                }
            }

            List<Item> result = new List<Item>();
            for (int i = items.Length; i > 0; i--)
            {
                if (!itemIncluded[i, maxCapacity]) continue;

                Item item = items[i - 1];
                result.Add(item);

                maxCapacity -= item.Weight;
            }

            result.Reverse();

            Console.WriteLine($"Total Weight: {result.Sum(i => i.Weight)}");
            Console.WriteLine($"Total Value: {result.Sum(i => i.Value)}");
            Console.WriteLine(String.Join(Environment.NewLine, result.Select(i => i.Name)));
        }

        private class Item
        {
            public string Name { get; set; }

            public int Weight { get; set; }

            public int Value { get; set; }
        }
    }
}
