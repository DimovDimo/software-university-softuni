using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Wintellect.PowerCollections;

public class Chainblock : IChainblock
{
    Dictionary<int, Transaction> byId =
        new Dictionary<int, Transaction>();

    public int Count => byId.Count();

    public void Add(Transaction tx)
    {
        byId.Add(tx.Id, tx);
    }

    public void ChangeTransactionStatus(int id, TransactionStatus newStatus)
    {
        if (!byId.ContainsKey(id))
        {
            throw new ArgumentException();
        }

        byId[id].Status = newStatus;
    }

    public bool Contains(Transaction tx)
    {
        return byId.ContainsKey(tx.Id);
    }

    public bool Contains(int id)
    {
        return byId.ContainsKey(id);
    }

    public IEnumerable<Transaction> GetAllInAmountRange(double lo, double hi)
    {
        return byId.Values
            .Where(x => lo <= x.Amount && x.Amount <= hi);
    }

    public IEnumerable<Transaction> GetAllOrderedByAmountDescendingThenById()
    {
        return byId.Values
            .OrderByDescending(x => x.Amount)
            .ThenBy(x => x.Id);
    }

    public IEnumerable<string> GetAllReceiversWithTransactionStatus(TransactionStatus status)
    {
        var result = byId.Values
            .Where(x => x.Status == status)
            .Select(x => x.To);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<string> GetAllSendersWithTransactionStatus(TransactionStatus status)
    {
        var result = byId.Values
            .Where(x => x.Status == status)
            //.OrderBy(x => x.Amount)
            .Select(x => x.From);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public Transaction GetById(int id)
    {
        if (!byId.ContainsKey(id))
        {
            throw new InvalidOperationException();
        }

        return byId[id];
    }

    public IEnumerable<Transaction> GetByReceiverAndAmountRange(string receiver, double lo, double hi)
    {
        var result = byId.Values
            .Where(x => x.To == receiver
                    && lo <= x.Amount && x.Amount < hi)
            .OrderByDescending(x => x.Amount)
            .ThenBy(x => x.Id);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Transaction> GetByReceiverOrderedByAmountThenById(string receiver)
    {
        var result = byId.Values
            .Where(x => x.To == receiver)
            .OrderByDescending(x => x.Amount)
            .ThenBy(x => x.Id);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Transaction> GetBySenderAndMinimumAmountDescending(string sender, double amount)
    {
        var result = byId.Values
            .Where(x => x.From == sender && x.Amount > amount)
            .OrderByDescending(x => x.Amount);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Transaction> GetBySenderOrderedByAmountDescending(string sender)
    {
        var result = byId.Values
            .Where(x => x.From == sender)
            .OrderByDescending(x => x.Amount);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Transaction> GetByTransactionStatus(TransactionStatus status)
    {
        var result = byId.Values
            .Where(x => x.Status == status)
            .OrderByDescending(x => x.Amount);

        if (result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<Transaction> GetByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount)
    {
        return byId.Values
            .Where(x => x.Status == status && x.Amount <= amount)
            .OrderByDescending(x => x.Amount);
    }

    public IEnumerator<Transaction> GetEnumerator()
    {
        return byId.Values.GetEnumerator();
    }

    public void RemoveTransactionById(int id)
    {
        byId.Remove(id);
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}

