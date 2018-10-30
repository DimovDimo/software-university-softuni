using System;
using System.Collections.Generic;

using Wintellect.PowerCollections;

class TextEditor : ITextEditor
{
    private Trie<BigList<char>> UsersText { get; set; }

    private Trie<Stack<string>> UsersStack { get; set; }

    public TextEditor()
    {
        this.UsersText = new Trie<BigList<char>>();
        this.UsersStack = new Trie<Stack<string>>();
    }

    public void Login(string username)
    {
        this.UsersText.Insert(username, new BigList<char>());
        this.UsersStack.Insert(username, new Stack<string>());
    }

    public void Logout(string username)
    {
        this.UsersText.Delete(username);
        this.UsersStack.Delete(username);
    }

    public void Prepend(string username, string str)
    {
        if (!this.UsersStack.Contains(username))
        {
            return;
        }

        var stack = this.UsersStack.GetValue(username);
        var text = this.UsersText.GetValue(username);
        stack.Push(string.Join("", text));

        text.AddRangeToFront(str);
    }

    public void Insert(string username, int index, string str)
    {
        if (!this.UsersStack.Contains(username))
        {
            return;
        }

        var stack = this.UsersStack.GetValue(username);
        var text = this.UsersText.GetValue(username);
        stack.Push(string.Join("", text));

        text.InsertRange(index, str);
    }

    public void Substring(string username, int startIndex, int length)
    {
        if (!this.UsersStack.Contains(username))
        {
            return;
        }

        var stack = this.UsersStack.GetValue(username);
        var text = this.UsersText.GetValue(username);
        stack.Push(string.Join("", text));

        BigList<char> newText = text.GetRange(startIndex, length);
        this.UsersText.Insert(username, newText);
    }

    public void Delete(string username, int startIndex, int length)
    {
        if (!this.UsersStack.Contains(username))
        {
            return;
        }

        var stack = this.UsersStack.GetValue(username);
        var text = this.UsersText.GetValue(username);
        stack.Push(string.Join("", text));

        this.UsersText.GetValue(username).RemoveRange(startIndex, length);
    }

    public void Clear(string username)
    {
        if (!this.UsersStack.Contains(username))
        {
            return;
        }

        var stack = this.UsersStack.GetValue(username);
        var text = this.UsersText.GetValue(username);
        stack.Push(string.Join("", text));

        this.UsersText.GetValue(username).Clear();
    }

    public int Length(string username)
    {
        return this.UsersText.GetValue(username).Count;
    }

    public string Print(string username)
    {
        if (!this.UsersStack.Contains(username))
        {
            throw new InvalidOperationException();
        }

        return string.Join("", this.UsersText.GetValue(username));
    }

    public void Undo(string username)
    {
        if (!this.UsersStack.Contains(username) || this.UsersStack.GetValue(username).Count == 0)
        {
            return;
        }

        var oldText = this.UsersStack.GetValue(username).Pop();
        BigList<char> newOldText = new BigList<char>(oldText);
        this.UsersText.Insert(username, newOldText);
    }

    public IEnumerable<string> Users(string prefix = "")
    {
        return this.UsersText.GetByPrefix(prefix);
    }
}