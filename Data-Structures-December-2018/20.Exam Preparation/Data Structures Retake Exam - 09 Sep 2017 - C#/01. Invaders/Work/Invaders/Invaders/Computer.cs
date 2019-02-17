using System;
using System.Collections.Generic;
using System.Linq;

public class Computer : IComputer
{
    private LinkedList<Invader> byInsertion = new LinkedList<Invader>();
    private Dictionary<int, List<LinkedListNode<Invader>>> byDistance =
        new Dictionary<int, List<LinkedListNode<Invader>>>();
    private int energy;
    private int steps = 0;

    public Computer(int energy)
    {
        if(energy < 0)
        {
            throw new ArgumentException();
        }

        this.Energy = energy;
    }

    public int Energy
    {
        get
        {
            if (this.energy < 0)
            {
                return 0;
            }

            return this.energy;
        }

        private set { this.energy = value; }
    }

    public void Skip(int turns)
    {
        this.steps += turns;

        this.byDistance = this.byDistance
            .Where(x =>
            {
                if(x.Key - this.steps <= 0)
                {
                    this.Energy -= x.Value.Sum(y => y.Value.Damage);
                    x.Value.ForEach(y => this.byInsertion.Remove(y));
                }

                return x.Key - this.steps > 0;
            })
            .ToDictionary(x => x.Key, y => y.Value);
    }

    public void AddInvader(Invader invader)
    {
        LinkedListNode<Invader> node = new LinkedListNode<Invader>(invader);
        if (!this.byDistance.ContainsKey(invader.Distance))
        {
            this.byDistance.Add(invader.Distance, new List<LinkedListNode<Invader>>();
        }

        this.byDistance[invader.Distance].Add(node);
        this.byInsertion.AddLast(node);
    }

    public void DestroyHighestPriorityTargets(int count)
    {
        foreach(var lln in this.byDistance
            .SelectMany(x => x.Value)
            .Take(count))
        {
            this.byInsertion.Remove(lln);
        }

        var dic = this.byDistance
            .SelectMany(x => x.Value)
            .Skip(count);

        this.byDistance = 
            new Dictionary<int, List<LinkedListNode<Invader>>>();

        foreach (var item in dic)
        {
            if (!this.byDistance.ContainsKey(item.Value.Distance))
            {
                this.byDistance.Add(item.Value.Distance, new List<LinkedListNode<Invader>>());
            }

            this.byDistance[item.Value.Distance].Add(item);
        }
    }

    public void DestroyTargetsInRadius(int radius)
    {
        this.byDistance = this.byDistance
            .Where(x => 
            {
                bool result = x.Key - this.steps > radius;

                if (!result)
                {
                    x.Value.ForEach(y => this.byInsertion.Remove(y));
                }

                return result;
            })
            .ToDictionary(x => x.Key, y => y.Value);
    }

    public IEnumerable<Invader> Invaders()
    {
        return byInsertion;
    }
}
