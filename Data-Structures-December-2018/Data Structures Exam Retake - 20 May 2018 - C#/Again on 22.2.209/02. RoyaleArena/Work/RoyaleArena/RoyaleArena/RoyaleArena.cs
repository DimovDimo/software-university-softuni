using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Wintellect.PowerCollections;

public class RoyaleArena : IArena
{
    Dictionary<int, Battlecard> byId =
        new Dictionary<int, Battlecard>();

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
        if (byId.Count < n)
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
        var result = new Dictionary<string, Battlecard>();
        foreach (var item in byId.Values)
        {
            if (!result.ContainsKey(item.Name))
            {
                result.Add(item.Name, item);
            }
            else if (result[item.Name].Swag < item.Swag)
            {
                result[item.Name] = item;
            }
        }

        return result.Values;
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
        var result = byId.Values
            .Where(x => x.Type == type)
            .OrderByDescending(x => x.Damage)
            .ThenBy(x => x.Id);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Battlecard> GetByCardTypeAndMaximumDamage(CardType type, double damage)
    {
        var result = byId.Values
            .Where(x => x.Type == type && x.Damage <= damage)
            .OrderByDescending(x => x.Damage)
            .ThenBy(x => x.Id);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
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
        var result = byId.Values
            .Where(x => lo <= x.Swag && x.Swag < hi)
            .OrderByDescending(x => x.Swag)
            .ThenBy(x => x.Id);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Battlecard> GetByNameOrderedBySwagDescending(string name)
    {
        var result = byId.Values
            .Where(x => x.Name == name)
            .OrderByDescending(x => x.Swag)
            .ThenBy(x => x.Id);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Battlecard> GetByTypeAndDamageRangeOrderedByDamageThenById(CardType type, int lo, int hi)
    {
        var result = byId.Values
            .Where(x => lo < x.Damage 
                   && x.Damage <= hi
                   && x.Type == type)
            .OrderByDescending(x => x.Damage)
            .ThenBy(x => x.Id);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerator<Battlecard> GetEnumerator()
    {
        return byId.Values.GetEnumerator();
    }

    public void RemoveById(int id)
    {
        byId.Remove(id);
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}
