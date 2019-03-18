using System;
using System.Collections.Generic;
using System.Linq;

public class GraphConnectedComponents
{
    private static bool[] visited;
    private static List<int>[] graph;

    //static List<int>[] graph = new List<int>[]
    //{
    //    new List<int>() { 3, 6 },
    //    new List<int>() { 3, 4, 5, 6 },
    //    new List<int>() { 8 },
    //    new List<int>() { 0, 1, 5 },
    //    new List<int>() { 1, 6 },
    //    new List<int>() { 1, 3 },
    //    new List<int>() { 0, 1, 4 },
    //    new List<int>() { },
    //    new List<int>() { 2 }
    //};

    public static void Main()
    {
        graph = ReadGraph();
        FindGraphConnectedComponents();

        //visited = new bool[graph.Length];
        //DFS(0);
        //Console.WriteLine();
    }

    private static List<int>[] ReadGraph()
    {
        int n = int.Parse(Console.ReadLine());
        //var graph = new List<int>[n];
        graph = new List<int>[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = Console.ReadLine()
                .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
                .Select(int.Parse).ToList();
        }
        return graph;
    }

    private static void FindGraphConnectedComponents()
    {
        visited = new bool[graph.Length];

        for(int strtNode = 0; strtNode < graph.Count(); strtNode++)
        {
            if (!visited[strtNode])
            {
                Console.Write("Connected component:");
                DFS(strtNode);
                Console.WriteLine();
            }
        }
    }

    private static void DFS(int vertex)
    {
        if (!visited[vertex])
        {
            visited[vertex] = true;
            foreach(var child in graph[vertex])
            {
                DFS(child);
            }

            Console.Write(" " + vertex);
        }
    }
}
