using System;
using System.Collections;
using System.Collections.Generic;
//using Wintellect.PowerCollections;
using System.Linq;

public class Instock : IProductStock
{
    Dictionary<string, Product> byLabel =
        new Dictionary<string, Product>();

    public int Count => byLabel.Count();

    public void Add(Product product)
    {
        if (byLabel.ContainsKey(product.Label))
        {
        
        }

        byLabel.Add(product.Label, product);
    }

    public void ChangeQuantity(string product, int quantity)
    {
        if (!byLabel.ContainsKey(product))
        {
            throw new ArgumentException();
        }

        byLabel[product].Quantity = quantity;

        //Product item = byLabel[product];
        //item.Quantity = quantity;
        //byLabel.Remove(product);
        //byLabel.Add(item.Label, item);
        
        //var newDic = byLabel.Values
        //    .Where(x => x.Label != product)
        //    .ToDictionary(x => x.Label, y => y);
        //newDic.Add(item.Label, item);
        //byLabel = newDic;
    }

    public bool Contains(Product product)
    {
        return byLabel.ContainsKey(product.Label);
    }

    public Product Find(int index)
    {
        if (index < 0 || byLabel.Count() - 1 < index)
        {
            throw new IndexOutOfRangeException();
        }

        return byLabel.Values.ToList()[index];
    }

    public IEnumerable<Product> FindAllByPrice(double price)
    {
        return byLabel.Values
            .Where(x => x.Price == price);
    }

    public IEnumerable<Product> FindAllByQuantity(int quantity)
    {
        return byLabel.Values
            .Where(x => x.Quantity == quantity);
    }

    public IEnumerable<Product> FindAllInRange(double lo, double hi)
    {
        return byLabel.Values
            .Where(x => lo < x.Price && x.Price <= hi)
            .OrderByDescending(x => x.Price);
    }

    public Product FindByLabel(string label)
    {
        if (!byLabel.ContainsKey(label))
        {
            throw new ArgumentException();
        }

        return byLabel[label];
    }

    public IEnumerable<Product> FindFirstByAlphabeticalOrder(int count)
    {
        if (count == 0)
        {
            return new List<Product>();
        }

        if (count < 0 || byLabel.Count() < count)
        {
            throw new ArgumentException();
        }        

        return byLabel.Values
            .OrderBy(x => x.Label)
            .Take(count);
    }

    public IEnumerable<Product> FindFirstMostExpensiveProducts(int count)
    {
        if (byLabel.Count() < count)
        {
            throw new ArgumentException();
        }

        return byLabel.Values
            .OrderByDescending(x => x.Price)
            .Take(count);

        //var result = byLabel.Values
        //    .OrderByDescending(x => x.Price)
        //    .Take(count);
        //
        //if (result.Count() < count)
        //{
        //    throw new ArgumentException();
        //}
        //
        //return result;
    }

    public IEnumerator<Product> GetEnumerator()
    {
        return byLabel.Values.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}
