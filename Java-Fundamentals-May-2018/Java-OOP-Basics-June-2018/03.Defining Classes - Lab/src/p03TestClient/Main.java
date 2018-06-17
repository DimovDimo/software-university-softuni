package p03TestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, BankAccount> accounts = new HashMap<>();
        while (true){
            String line = reader.readLine();
            if ("End".equals(line)){
                break;
            }

            String[] arguments = line.split("\\s+");
            String command = arguments[0];
            switch (command){
                case "Create":
                    create(accounts, arguments[1]);
                    break;
                case "Deposit":
                    deposit(accounts, arguments);
                    break;
                case "Withdraw":
                    withdaw(accounts, arguments);
                    break;
                case "Print":
                    print(accounts, arguments[1]);
                    break;
                default:
                    break;
            }
        }
    }

    private static void print(HashMap<Integer, BankAccount> accounts, String argument) {
        int id = Integer.valueOf(argument);
        if (accounts.containsKey(id)){
            System.out.printf("%s%n",accounts.get(id).toString());
        } else {
            System.out.printf("Account does not exist%n");
        }
    }

    private static void withdaw(HashMap<Integer, BankAccount> accounts, String[] arguments) {
        int id = Integer.valueOf(arguments[1]);
        if (accounts.containsKey(id)){
            int amount = Integer.valueOf(arguments[2]);
            accounts.get(id).withdraw(amount);
        } else {
            System.out.printf("Account does not exist%n");
        }
    }

    private static void deposit(HashMap<Integer, BankAccount> accounts, String[] arguments) {
        int id = Integer.valueOf(arguments[1]);
        if (accounts.containsKey(id)){
            int amount = Integer.valueOf(arguments[2]);
            accounts.get(id).deposit(amount);
        } else {
            System.out.printf("Account does not exist%n");
        }
    }

    private static void create(HashMap<Integer, BankAccount> accounts, String argument) {
        int id = Integer.valueOf(argument);
        if (accounts.containsKey(id)){
            System.out.printf("Account already exists%n");
        } else {
            BankAccount account = new BankAccount();
            account.setId(id);
            accounts.put(id, account);
        }
    }
}
