using System;
using System.Collections.Generic;

public class EdmondsKarp
{
    private static int[][] graph;
    private static int[] parents;

    public static int FindMaxFlow(int[][] targetGraph)
    {
        graph = targetGraph;
        parents = new int[graph.Length];

        for (int i = 0; i < graph.Length; i++)
        {
            parents[i] = -1;
        }

        int maxFlow = 0;
        int start = 0;
        int end = graph.Length - 1;
        while (BreadthFirstSearch(start, end))
        {
            int pathFlow = int.MaxValue;
            int currentNode = end;

            while (currentNode != start)
            {
                int previousNode = parents[currentNode];
                if(pathFlow > graph[previousNode][currentNode])
                {
                    pathFlow = graph[previousNode][currentNode];
                }

                currentNode = previousNode;
            }

            maxFlow += pathFlow;
            currentNode = end;
            while (currentNode != start)
            {
                int previousNode = parents[currentNode];
                graph[previousNode][currentNode] -= pathFlow;
                graph[currentNode][previousNode] += pathFlow;
                currentNode = previousNode;
            }
        }

        return maxFlow;
    }

    private static bool BreadthFirstSearch(int start, int end)
    {
        bool[] visited = new bool[graph.Length];

        var queue = new Queue<int>();
        queue.Enqueue(start);
        while (queue.Count > 0)
        {
            var current = queue.Dequeue();
            visited[current] = true;
            for (int child = 0; child < graph[current].Length; child++)
            {
                if (!visited[child] &&
                    graph[current][child] > 0)
                {
                    parents[child] = current;
                    queue.Enqueue(child);
                }
            }
        }

        return visited[end];
    }

    //private static void BFS(int start, int end, bool[] visited)
    //{
    //    for (int child = 0; child < graph[start].Length; child++)
    //    {
    //        if (!visited[child] &&
    //            graph[start][child] > 0)
    //        {
    //            parents[child] = start;
    //        }
    //    }
    //}
}
