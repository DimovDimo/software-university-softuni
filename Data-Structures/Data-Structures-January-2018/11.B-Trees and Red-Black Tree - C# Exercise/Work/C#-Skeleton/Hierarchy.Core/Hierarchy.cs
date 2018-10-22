namespace Hierarchy.Core
{
    using System;
    using System.Collections.Generic;
    using System.Collections;

    public class Hierarchy<T> : IHierarchy<T>
    {
        private Node root;
        private Dictionary<T, Node> nodesByValue;

        public Hierarchy(T root)
        {
            this.root = new Node(root);
            this.nodesByValue = new Dictionary<T, Node>
            {
                { root, this.root }
            };
        }

        public int Count
        {
            get
            {
                return this.nodesByValue.Count;
            }
        }

        public void Add(T parent, T child)
        {
            if (!this.nodesByValue.ContainsKey(parent))
            {
                throw new ArgumentException();
            }
            else if (this.nodesByValue.ContainsKey(child))
            {
                throw new ArgumentException();
            }

            Node parentNode = this.nodesByValue[parent];
            Node childNode = new Node(child, parentNode);

            parentNode.Children.Add(childNode);
            this.nodesByValue.Add(child, childNode);
        }

        public void Remove(T element)
        {
            if (!this.nodesByValue.ContainsKey(element))
            {
                throw new ArgumentException();
            }
            Node nodeToRemove = this.nodesByValue[element];
            if (nodeToRemove.Equals(this.root))
            {
                throw new ArgumentException();
            }

            List<Node> children = nodeToRemove.Children;
            Node parent = nodeToRemove.Parent;
            parent.Children.Remove(nodeToRemove);
            foreach (Node child in children)
            {
                child.Parent = parent;
                parent.Children.Add(child);
            }

            this.nodesByValue.Remove(element);
        }

        public IEnumerable<T> GetChildren(T item)
        {
            if (!this.nodesByValue.ContainsKey(item))
            {
                throw new ArgumentException();
            }

            Node parent = this.nodesByValue[item];
            return parent.Children.Select(x => x.Value).ToList();
        }

        public T GetParent(T item)
        {
            if (!this.nodesByValue.ContainsKey(item))
            {
                throw new ArgumentException();
            }
            else if (this.root == this.nodesByValue[item])
            {
                return default(T);
            }

            return this.nodesByValue[item].Parent.Value;
        }

        public bool Contains(T value)
        {
            return this.nodesByValue.ContainsKey(value);
        }

        public IEnumerable<T> GetCommonElements(Hierarchy<T> other)
        {
            HashSet<T> result = new HashSet<T>(this.nodesByValue.Keys);                
            result.IntersectWith(new HashSet<T>(other.nodesByValue.Keys));
            return result;
        }

        public IEnumerator<T> GetEnumerator()
        {
            Queue<Node> queue = new Queue<Node>();

            Node current = this.root;
            queue.Enqueue(current);

            while(queue.Count > 0)
            {
                current = queue.Dequeue();
                yield return current.Value;
                foreach (var child in current.Children)
                {
                    queue.Enqueue(child);
                }
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }

        private class Node
        {

            public T Value { get; set; }
            public Node Parent { get; set; }
            public List<Node> Children { get; set; }

            public Node(T value, Node parent = null)
            {
                this.Value = value;
                this.Children = new List<Node>();
                this.Parent = parent;
            }
        }
    }
}