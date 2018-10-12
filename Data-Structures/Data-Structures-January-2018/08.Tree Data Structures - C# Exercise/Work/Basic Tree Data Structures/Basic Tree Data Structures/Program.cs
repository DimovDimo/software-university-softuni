using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    static Dictionary<int, Tree<int>> tree = new Dictionary<int, Tree<int>>();

    static void Main(string[] args)
    {
        int numberOfNodes = int.Parse(Console.ReadLine());

        for (int i = 0; i < numberOfNodes; i++)
        {
            int[] nodes = Console.ReadLine()
                .Split()
                .Select(int.Parse)
                .ToArray();

            int parent = nodes[0];
            int child = nodes[1];

            if (!tree.ContainsKey(parent))
            {
                tree.Add(parent, new Tree<int>(parent));
            }

            if (!tree.ContainsKey(child))
            {
                tree.Add(child, new Tree<int>(child));
            }

            Tree<int> parentNode = tree[parent];
            Tree<int> childNode = tree[child];
            parentNode.Children.Add(childNode);
            childNode.Parent = parentNode;


        }

        Tree<int> root = tree.FirstOrDefault(x => x.Value.Parent == null).Value;

        //01. Root Node ___ root.PrintRootNode(tree);
        //02. Print Tree __ root.Print();
        //03. Leaf Nodes __ root.PrintLeafNodes(tree);
        //04. Middle Nodes _ root.PrintMiddleNodes(tree);
    }

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

        public void PrintLeafNodes(Dictionary<int, Tree<int>> tree)
        {
            var nodes = tree.Values
                .Where(x => x.Parent != null 
                    && x.Children.Count == 0)
                .Select(x => x.Value)
                .OrderBy(x => x)
                .ToList();

            Console.WriteLine("Leaf nodes: " + string.Join(" ", nodes));
        }

        public void PrintMiddleNodes(Dictionary<int, Tree<int>> tree)
        {
            var nodes = tree.Values
                .Where(x => x.Parent != null
                    && x.Children.Count != 0)
                .Select(x => x.Value)
                .OrderBy(x => x)
                .ToList();

            Console.WriteLine("Middle nodes: " + string.Join(" ", nodes));
        }
    }
}


