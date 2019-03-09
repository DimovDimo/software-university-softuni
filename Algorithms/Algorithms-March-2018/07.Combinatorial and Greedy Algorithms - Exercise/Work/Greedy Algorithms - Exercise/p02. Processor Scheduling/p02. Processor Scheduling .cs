using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02.Processor_Scheduling
{
    class Program
    {
        static void Main(string[] args)
        {
            int itemsCount = Int32.Parse(Console.ReadLine().Split(' ').ToArray()[1]);
            int maxDeadline = 0;
            //Dictionary<int, List<int>> items = new Dictionary<int, List<int>>();
            Dictionary<int, int> items = new Dictionary<int, int>();
            for (int i = 0; i < itemsCount; i++)
            {
                string[] tokens = Console.ReadLine().Split(' ').ToArray();
                int value = Int32.Parse(tokens[0]);
                int deadline = Int32.Parse(tokens[2]);
                if(maxDeadline < deadline)
                {
                    maxDeadline = deadline;
                }

                //if (!items.ContainsKey(value))
                //{
                //    items.Add(value, new List<int>());
                //}
                //
                //items[value].Add(deadline);
                items.Add(value, deadline);
            }

            List<int> itemsKeys = items.Keys.ToList();
            Dictionary<int, int> getItems = new Dictionary<int, int>();
            for (int i = 1; i <= maxDeadline; i++)
            {
                foreach(var item in items
                    .OrderByDescending(x => x.Key))
                {
                    //if(item.Value >= i)
                    //{
                    //    getItems.Add(item.Key, item.Value);
                    //    items.Remove(item.Key);
                    //    break;
                    //}
                    getItems.Add(item.Key, item.Value);
                    items.Remove(item.Key);
                    break;
                }
            }

            //List<int> itemsKeys = items.Keys.ToList();
            List<int> numbers = getItems
                .OrderBy(x => x.Value)
                .ThenByDescending(x => x.Key)
                .Select(x => itemsKeys.IndexOf(x.Key) + 1)
                .ToList();

            Console.WriteLine($"Optimal schedule: {string.Join(" -> ", numbers)}");
            Console.WriteLine($"Total value: {getItems.Keys.Sum()}");
        }
    }
}
