using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Wintellect.PowerCollections;

/// <summary>
/// The ThreadExecutor is the concrete implementation of the IScheduler.
/// You can send any class to the judge system as long as it implements
/// the IScheduler interface. The Tests do not contain any <e>Reflection</e>!
/// </summary>
public class ThreadExecutor : IScheduler
{
    Dictionary<int, Task> byId = 
        new Dictionary<int, Task>();
    int sumCyrcles = 0;
 
    int IScheduler.Count => byId.Count();

    public ThreadExecutor()
    {

    }

    public int Count()
    {
        return byId.Count();
    }


    public void ChangePriority(int id, Priority newPriority)
    {
        if (!byId.ContainsKey(id))
        {
            throw new ArgumentException();
        }

        byId[id].TaskPriority = newPriority;
    }

    public bool Contains(Task task)
    {
        return byId.ContainsKey(task.Id);
    }

    public int Cycle(int cycles)
    {
        if (byId.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        int startCount = byId.Count();

        Dictionary<int, Task> newDic = new Dictionary<int, Task>();
        
        sumCyrcles += cycles;
        foreach (var item in byId.Values)
        {
            if(item.Consumption - sumCyrcles > 0)
            {
                newDic.Add(item.Id, item);
            }            
        }
        
        byId = newDic;

        //byId.Where(x => x.Value.Consumption - sumCyrcles > 0);

        return startCount - byId.Count();

    }

    public void Execute(Task task)
    {
        if (byId.ContainsKey(task.Id))
        {
            throw new ArgumentException();
        }

        byId.Add(task.Id, task);
    }

    public IEnumerable<Task> GetByConsumptionRange(int lo, int hi, bool inclusive)
    {
        if (inclusive)
        {
            return byId.Values
                .Where(x => lo <= x.Consumption - sumCyrcles
                    && x.Consumption - sumCyrcles <= hi)
                .OrderBy(x => x.Consumption)
                .ThenByDescending(x => x.TaskPriority);
        }
        else
        {
            return byId.Values
                .Where(x => lo < x.Consumption - sumCyrcles
                    && x.Consumption - sumCyrcles < hi)
                .OrderBy(x => x.Consumption)
                .ThenByDescending(x => x.TaskPriority);
        }
        
    }

    public Task GetById(int id)
    {
        if (!byId.ContainsKey(id))
        {
            throw new ArgumentException();
        }

        return byId[id];
    }

    public Task GetByIndex(int index)
    {
        if (index < 0 
            || byId.Count() - 1 < index)
        {
            throw new ArgumentOutOfRangeException();
        }

        return byId.Values.ElementAt(index);
    }

    public IEnumerable<Task> GetByPriority(Priority type)
    {
        return byId.Values
            .Where(x => x.TaskPriority == type)
            .OrderByDescending(x => x.Id);
    }

    public IEnumerable<Task> GetByPriorityAndMinimumConsumption(Priority priority, int lo)
    {
        var result = byId.Values
                .Where(x => lo <= x.Consumption
                    && x.TaskPriority == priority)
                .OrderByDescending(x => x.Id);

        if (result.Count() == 0)
        {
            return new List<Task>();
        }

        return result;
    }


    public IEnumerator<Task> GetEnumerator()
    {
        foreach (var item in byId.Values)
        {
            yield return item;
        }
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}
