using System;

public static class Heap<T> where T : IComparable<T>
{
    public static void Sort(T[] arr)
    {
        for (int i = arr.Length / 2; i >= 0; i--)
        {
            HeapifyDown(arr, i, arr.Length);
        }

        for (int i = arr.Length - 1; i > 0; i--)
        {
            Swap(arr, 0, i);
            HeapifyDown(arr, 0, i);
        }
    }

    private static void HeapifyDown(T[] arr, int current, int border)
    {
        while (current < border / 2)
        {
            int child = 2 * current + 1;
            if (child + 1 < border && IsGreater(arr, child + 1, child))
            {
                child++;
            }

            if (IsGreater(arr, current, child))
            {
                break;
            }

            Swap(arr, child, current);
            current = child;
        }
    }

    private static bool IsGreater(T[] arr, int index, int parent)
    {
        return arr[index].CompareTo(arr[parent]) > 0;
    }

    private static void Swap(T[] arr, int index, int parent)
    {
        var temp = arr[parent];
        arr[parent] = arr[index];
        arr[index] = temp;
    }
}
