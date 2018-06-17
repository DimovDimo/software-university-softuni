package p05_StaticIdAndRate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
                    create(accounts);
                    break;
                case "Deposit":
                    deposit(accounts, arguments);
                    break;
                case "SetInterest":
                    setInterst(arguments[1]);
                    break;
                case "GetInterest":
                    getInterest(accounts, arguments);
                    break;
                default:
                    break;
            }
        }
    }

    private static void getInterest(HashMap<Integer, BankAccount> accounts, String[] arguments) {
        int id = Integer.valueOf(arguments[1]);
        try{
        if (accounts.containsKey(id)){
            int years = Integer.valueOf(arguments[2]);
            System.out.printf("%.2f%n", accounts.get(id).getInterest(years));
        } else {
            System.out.println("Account does not exist");
        }
        } catch (Exception e){

        }
    }

    private static void setInterst(String argument) {
        double interestRate = Double.parseDouble(argument);
        BankAccount.setInterest(interestRate);
    }

    private static void deposit(HashMap<Integer, BankAccount> accounts, String[] arguments) {
        int id = Integer.valueOf(arguments[1]);
        try{
            if (accounts.containsKey(id)){
                double amount = Integer.valueOf(arguments[2]);
                accounts.get(id).deposit(amount);
                System.out.printf("Deposited %.0f to %s%n", amount, accounts.get(id).toString());
            } else {
                System.out.println("Account does not exist");
            }
        } catch (Exception e){

        }

    }

    private static void create(HashMap<Integer, BankAccount> accounts) {
        BankAccount client = new BankAccount();
        accounts.put(client.getId(), client);
        System.out.printf("Account %s created%n", client.toString());
    }
}
