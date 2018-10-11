using System;

public class Tree<T>
{
    public T Value { get; set; }
    public Tree<T> Parent { get; set; }
    public List<Tree<T>> Children { get; set; }

    public Tree(T value, params Tree<T>[] children)
    {
        this.Value = value;
        this.Children = new List<Tree<T>>();
        foreach (var child in children)
        {
            this.Children.Add(child);
            child.Parent = this;
        }
    }

    public void Print(int indent = 0)
    {
        Console.WriteLine($"{new string(' ', indent)}{this.Value}");
        foreach (var child in this.Children)
        {
            child.Print(indent + 2);
        }
    }

    public void PrintRootNode(Dictionary<int, Tree<int>> tree)
    {
        Tree<int> root = tree.FirstOrDefault(x => x.Value.Parent == null).Value;

        Console.WriteLine($"Root node: {root.Value}");
    }
}
