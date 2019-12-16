using System;
using System.Collections.Generic;

public class TopologicalSorter
{
    private Dictionary<string, List<string>> graph;
    private HashSet<string> visited = new HashSet<string>();
    private HashSet<string> cycleNodes = new HashSet<string>();

    public TopologicalSorter(Dictionary<string, List<string>> graph)
    {
        this.graph = graph;
    }

    private void DFS(string node, LinkedList<string> sorted)
    {
        if (cycleNodes.Contains(node))
        {
            throw new InvalidOperationException("Cycle detected.");
        }

        if (!visited.Contains(node))
        {
            visited.Add(node);
            cycleNodes.Add(node);
            foreach (var child in graph[node])
            {
                DFS(child, sorted);
            }

            cycleNodes.Remove(node);
            sorted.AddFirst(node);
        }
    }

    public ICollection<string> TopSort()
    {
        LinkedList<string> sorted = new LinkedList<string>();

        foreach (var node in graph.Keys)
        {
            DFS(node, sorted);
        }

        return sorted;
    }
}
