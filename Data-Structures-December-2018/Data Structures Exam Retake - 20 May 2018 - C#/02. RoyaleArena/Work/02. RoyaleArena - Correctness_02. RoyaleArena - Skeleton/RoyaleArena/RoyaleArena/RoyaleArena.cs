using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Wintellect.PowerCollections;

public class RoyaleArena : IArena
{
    Dictionary<int, Battlecard> byId =
        new Dictionary<int, Battlecard>();
    Dictionary<string, Battlecard> byName = 
        new Dictionary<string, Battlecard>();

    public int Count => byId.Count();

    public void Add(Battlecard card)
    {
        byId.Add(card.Id, card);
    }

    public void ChangeCardType(int id, CardType type)
    {
        if (!byId.ContainsKey(id))
        {
            throw new ArgumentException();
        }

        byId[id].Type = type;
    }

    public bool Contains(Battlecard card)
    {
        return byId.ContainsKey(card.Id);
    }

    public IEnumerable<Battlecard> FindFirstLeastSwag(int n)
    {
        if (byId.Count() < n)
        {
            throw new InvalidOperationException();
        }

        return byId.Values
            .OrderBy(x => x.Swag)
            .ThenBy(x => x.Id)
            .Take(n);
    }

    public IEnumerable<Battlecard> GetAllByNameAndSwag()
    {
        byName.Clear();

        foreach(var item in byId.Values)
        {
            if (byName.ContainsKey(item.Name))
            {
                if(byName[item.Name].Swag < item.Swag)
                {
                    byName[item.Name] = item;
                }
            }
            else
            {
                byName.Add(item.Name, item);
            }
        }

        if (byName.Count() == 0)
        {
            return new List<Battlecard>();
        }

        return byName.Values;
    }

    public IEnumerable<Battlecard> GetAllInSwagRange(double lo, double hi)
    {
        return byId.Values
            .Where(x => lo <= x.Swag
                       && x.Swag <= hi)
            .OrderBy(x => x.Swag);
    }

    public IEnumerable<Battlecard> GetByCardType(CardType type)
    {
        IOrderedEnumerable<Battlecard> results = byId.Values
            .Where(x => x.Type == type)
            .OrderByDescending(x => x.Damage)
            .ThenBy(x => x.Id);

        if (results.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return results;
    }

    public IEnumerable<Battlecard> GetByCardTypeAndMaximumDamage(CardType type, double damage)
    {
        IOrderedEnumerable<Battlecard> results = byId.Values
            .Where(x => x.Type == type
                     && x.Damage <= damage)
            .OrderByDescending(x => x.Damage)
            .ThenBy(x => x.Id);

        if (results.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return results;
    }

    public Battlecard GetById(int id)
    {
        if (!byId.ContainsKey(id))
        {
            throw new InvalidOperationException();
        }

        return byId[id];
    }

    public IEnumerable<Battlecard> GetByNameAndSwagRange(string name, double lo, double hi)
    {
        IOrderedEnumerable<Battlecard> results = byId.Values
            .Where(x => x.Name == name
                      && lo <= x.Swag
                      && x.Swag < hi)
            .OrderByDescending(x => x.Swag)
            .ThenBy(x => x.Id);

        if (results.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return results;
    }

    public IEnumerable<Battlecard> GetByNameOrderedBySwagDescending(string name)
    {
        IOrderedEnumerable<Battlecard> results = byId.Values
            .Where(x => x.Name == name)
            .OrderByDescending(x => x.Swag)
            .ThenBy(x => x.Id);

        if (results.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return results;
    }

    public IEnumerable<Battlecard> GetByTypeAndDamageRangeOrderedByDamageThenById(CardType type, int lo, int hi)
    {
        IOrderedEnumerable<Battlecard> results = byId.Values
            .Where(x => x.Type == type
                     && lo < x.Damage
                     && x.Damage <= hi)
            .OrderByDescending(x => x.Damage)
            .ThenBy(x => x.Id);

        if (results.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return results;
    }

    public IEnumerator<Battlecard> GetEnumerator()
    {
        foreach (var item in byId.Values)
        {
            yield return item;
        }
    }

    public void RemoveById(int id)
    {
        if (!byId.ContainsKey(id))
        {
            throw new InvalidOperationException();
        }
        
        byId.Remove(id);
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}
