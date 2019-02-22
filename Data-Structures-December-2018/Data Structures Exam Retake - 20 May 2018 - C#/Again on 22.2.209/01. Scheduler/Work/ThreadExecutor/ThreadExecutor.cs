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

    int steps = 0;
    int countOldById = 0;
  
    public ThreadExecutor()
    {

    }

    public int Count
    {
        get
        {
            return byId.Count();
        }        
    }

    int IScheduler.Count => byId.Count();

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
        if (byId.Count == 0)
        {
            throw new InvalidOperationException();
        }

        steps += cycles;
        countOldById = byId.Count();

        byId = byId.Values
            .Where(x => x.Consumption > steps)
            .ToDictionary(x => x.Id, y => y);

        return countOldById - byId.Count();
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
        return byId.Values
            .Where(x =>
                inclusive ?
                lo <= x.Consumption - steps && x.Consumption - steps <= hi :
                lo < x.Consumption - steps && x.Consumption - steps < hi)
            .OrderBy(x => x.Consumption)
            .ThenByDescending(x => x.TaskPriority);
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
        if (index < 0 || byId.Count - 1 < index)
        {
            throw new ArgumentOutOfRangeException();
        }

        return byId.Values.ToList()[index];
    }

    public IEnumerable<Task> GetByPriority(Priority type)
    {
        return byId.Values
            .Where(x => x.TaskPriority == type)
            .OrderByDescending(x => x.Id);
    }

    public IEnumerable<Task> GetByPriorityAndMinimumConsumption(Priority priority, int lo)
    {
        return byId.Values
            .Where(x => lo <= x.Consumption && x.TaskPriority == priority)
            .OrderByDescending(x => x.Id);
    }

    public IEnumerator<Task> GetEnumerator()
    {
        return byId.Values.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}
