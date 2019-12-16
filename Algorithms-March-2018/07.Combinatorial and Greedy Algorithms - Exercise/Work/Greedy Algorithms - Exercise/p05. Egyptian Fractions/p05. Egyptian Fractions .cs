using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p05.Egyptian_Fractions
{
    class Program
    {
        static void Main(string[] args)
        {
            double[] nums = Console.ReadLine().Split('/')
                .Select(Double.Parse)
                .ToArray();
            double purpose = nums[0] / nums[1];
            double currentSum = 0;
            bool isFirst = true;
            StringBuilder stringBuilder = new StringBuilder();
            if(purpose >= 1)
            {
                stringBuilder.Append("Error (fraction is equal to or greater than 1)");
            }
            else
            {
                stringBuilder.Append($"{nums[0]}/{nums[1]} = ");
                double index = 2;
                while (purpose > currentSum)
                {
                    double up = 1 / index;
                    double freeCapacity = purpose - (currentSum + up);
                    if (freeCapacity >= 0)
                    {
                        currentSum += up;
                        if (isFirst)
                        {
                            stringBuilder.Append($"1/{index}");
                            isFirst = false;
                        }
                        else
                        {
                            stringBuilder.Append($" + 1/{index}");
                        }
                    }

                    index++;
                }
            }

            Console.WriteLine(stringBuilder.ToString());
        }
    }
}
