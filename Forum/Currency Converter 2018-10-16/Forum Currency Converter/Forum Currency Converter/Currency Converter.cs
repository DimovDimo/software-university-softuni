using System;

class Program
{
    static void Main()
    {
        const double usd = 1.79549;
        const double eur = 1.95583;
        const double gbp = 2.53405;

        double money = double.Parse(Console.ReadLine());
        string firstCurrency = Console.ReadLine();
        string secondCurrency = Console.ReadLine();

        switch (firstCurrency)
        {
            case "USD": money = money * usd; break;
            case "EUR": money = money * eur; break;
            case "GBP": money = money * gbp; break;
        }

        switch (secondCurrency)
        {
            case "USD": money = money / usd; break;
            case "EUR": money = money / eur; break;
            case "GBP": money = money / gbp; break;
        }

        Console.WriteLine($"{money:F2} {secondCurrency}");
    }
}