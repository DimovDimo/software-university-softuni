using System;
using System.Collections;
using System.Collections.Generic;
using Wintellect.PowerCollections;
using System.Linq;

public class Organization : IOrganization
{
    List<Person> byInsertion = new List<Person>();

    Dictionary<string, Bag<Person>> byName =
        new Dictionary<string, Bag<Person>>();

    HashSet<Person> unique = new HashSet<Person>();

    public IEnumerator<Person> GetEnumerator()
    {
        return byInsertion.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }

    public int Count
    {
        get
        {
            return byInsertion.Count;
        }
    }

    public bool Contains(Person person)
    {
        return unique.Contains(person);
    }

    public bool ContainsByName(string name)
    {
        return byName.ContainsKey(name);
    }

    public void Add(Person person)
    {
        byInsertion.Add(person);
        unique.Add(person);

        if (!byName.ContainsKey(person.Name))
        {
            byName.Add(person.Name, new Bag<Person>());
        }
        byName[person.Name].Add(person);
    }

    public Person GetAtIndex(int index)
    {
        if(index < 0 || byInsertion.Count < index)
        {
            throw new IndexOutOfRangeException();
        }

        return byInsertion[index];
    }

    public IEnumerable<Person> GetByName(string name)
    {
        if (!byName.ContainsKey(name))
        {
            return new List<Person>();
        }

        return byName[name];
    }

    public IEnumerable<Person> FirstByInsertOrder(int count = 1)
    {
        if(count < 0)
        {
            yield break;
        }

        if (byInsertion.Count < count)
        {
            count = byInsertion.Count;
        }

        for (int i = 0; i < count; i++)
        {
            yield return byInsertion[i];
        }
    }

    public IEnumerable<Person> SearchWithNameSize(int minLength, int maxLength)
    {
        var keys = byName.Keys
            .Select(x => x.ToString())
            .Where(x => minLength <= x.Length
                   && x.Length <= maxLength);

        if (keys.Count() == 0)
        {
            return new List<Person>();
        }

        List<Person> result = new List<Person>();
        foreach(var key in keys)
        {
            byName[key]
                .ForEach(x => result.Add(x));
        }

        return result;
    }

    public IEnumerable<Person> GetWithNameSize(int length)
    {
        var keys = byName.Keys
            .Where(x => x.Length == length);

        if (keys.Count() == 0)
        {
            throw new ArgumentException();
        }

        List<Person> result = new List<Person>();
        foreach (var key in keys)
        {
            byName[key]
                .ForEach(x => result.Add(x));
        }

        return result;
    }

    public IEnumerable<Person> PeopleByInsertOrder()
    {
        return byInsertion;
    }
}