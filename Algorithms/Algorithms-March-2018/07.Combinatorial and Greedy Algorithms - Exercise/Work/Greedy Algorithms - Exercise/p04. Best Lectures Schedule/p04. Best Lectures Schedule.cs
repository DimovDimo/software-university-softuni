using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04.Best_Lectures_Schedule
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = Int32.Parse(Console.ReadLine().Split(' ').ToArray()[1]);
            char[] delimiters = new char[] { ':', ' ', '-' };
            List<Lecture> lectures = ReadLectures(n);
            List<Lecture> maxLectures = GetMaxLectures(lectures);
            PrintLectures(maxLectures);
        }

        private static void PrintLectures(List<Lecture> maxLectures)
        {
            Console.WriteLine($"Lectures ({maxLectures.Count}):");
            foreach (var lecture in maxLectures)
            {
                Console.WriteLine($"{lecture.start}-{lecture.end} -> {lecture.name}");
            }
        }

        private static List<Lecture> GetMaxLectures(List<Lecture> lectures)
        {
            List<Lecture> maxLectures = new List<Lecture>();
            foreach (var lecture in lectures.OrderBy(l => l.end))
            {
                if (!lecture.isRemoved)
                {
                    maxLectures.Add(lecture);
                    foreach (var l in lectures)
                    {
                        if (l.start < lecture.end)
                        {
                            l.isRemoved = true;
                        }
                    }
                }
            }

            return maxLectures;
        }

        private static List<Lecture> ReadLectures(int n)
        {
            List<Lecture> lectures = new List<Lecture>();
            char[] delimiters = new char[] { ':', ' ', '-' };
            for (int row = 0; row < n; row++)
            {
                string[] tokens = Console.ReadLine()
                    .Split(delimiters, StringSplitOptions.RemoveEmptyEntries)
                    .ToArray();
                string name = tokens[0];
                int start = Int32.Parse(tokens[1]);
                int end = Int32.Parse(tokens[2]);
                lectures.Add(new Lecture(name, start, end));
            }

            return lectures;
        }

        private class Lecture
        {
            public string name { get; set; }
            public int start { get; set; }
            public int end { get; set; }
            public bool isRemoved { get; set; }

            public Lecture(string name, int start, int end)
            {
                this.name = name;
                this.start = start;
                this.end = end;
                isRemoved = false;
            }
        }
    }
}
