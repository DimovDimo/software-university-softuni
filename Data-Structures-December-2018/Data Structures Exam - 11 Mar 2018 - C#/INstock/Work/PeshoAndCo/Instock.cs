using System;
using System.Collections;
using System.Collections.Generic;
using Wintellect.PowerCollections;
using System.Linq;

public class Instock : IProductStock
{
    Dictionary<string, Product> byLabel =
        new Dictionary<string, Product>();

    List<Product> listProducts;

    Dictionary<int, LinkedList<Product>> byQuantity =
        new Dictionary<int, LinkedList<Product>>();
    
    SortedSet<Product> byLabelSortedSet =
        new SortedSet<Product>();
    
    Dictionary<string, LinkedListNode<Product>> fastRetrievalByLabel =
        new Dictionary<string, LinkedListNode<Product>>();

    public Instock()
    {
        listProducts = new List<Product>();
    }

    public int Count => byLabel.Count();

    public void Add(Product product)
    {
        LinkedListNode<Product> node = new LinkedListNode<Product>(product);
        if (!this.byQuantity.ContainsKey(product.Quantity))
        {
            this.byQuantity.Add(product.Quantity, new LinkedList<Product>());
        }
        this.byQuantity[product.Quantity].AddLast(node);
        this.fastRetrievalByLabel.Add(product.Label, node);

        byLabel.Add(product.Label, product);
        listProducts.Add(product);
        byLabelSortedSet.Add(product);
    }

    public void ChangeQuantity(string product, int quantity)
    {
        if (!byLabel.ContainsKey(product))
        {
            throw new ArgumentException();
        }

        LinkedListNode<Product> node = this.fastRetrievalByLabel[label];
        int oldQuantity = node.Value.Quantity;
        this.byQuantity[oldQuantity].Remove(node);
        node.Value.Quantity = quantity;
        if (!this.byQuantity.ContainsKey(quantity))
        {
            this.byQuantity.Add(quantity, new LinkedList<Product>());
        }
        this.byQuantity[quantity].AddLast(node);
    }

    public bool Contains(Product product)
    {
        return this.byLabel.ContainsKey(product.Label);
    }

    public Product Find(int index)
    {
        if(index < 0 || byLabel.Count() - 1 < index)
        {
            throw new IndexOutOfRangeException();
        }

        return listProducts[index];
    }

    public IEnumerable<Product> FindAllByPrice(double price)
    {
        return byLabel.Values
            .Where(x => x.Price == price);
    }

    public IEnumerable<Product> FindAllByQuantity(int quantity)
    {
        return byQuantity[quantity];
    }

    public IEnumerable<Product> FindAllInRange(double lo, double hi)
    {
        return byLabel.Values
            .Where(x => lo < x.Price
                    && x.Price <= hi)
               .OrderByDescending(x => x.Price);
    }

    public Product FindByLabel(string label)
    {
        if (!this.fastRetrievalByLabel.ContainsKey(label))
        {
            throw new ArgumentException();
        }

        return this.fastRetrievalByLabel[label].Value;
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
        
        return byLabelSortedSet.Take(count);
    }

    public IEnumerable<Product> FindFirstMostExpensiveProducts(int count)
    {
        if (byLabel.Count()  < count)
        {
            throw new ArgumentException();
        }

        return byLabel.Values
            .OrderByDescending(x => x.Price)
            .Take(count);
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
