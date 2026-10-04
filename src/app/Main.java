package app;

import service.impl.BankService;
import service.impl.BankServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();
        boolean running = true;
        System.out.println("Welcome to Console Bank:");
        while (running){
            System.out.println("""
                1) Open Account
                2) Deposit
                3) Withdraw
                4) Transfer
                5) Account Statement
                6) List Accounts
                7) Search Account by Customer Name
                0) Exit
                """);

            System.out.print("CHOOSE:  ");
            String choice = scanner.nextLine().trim();
            System.out.println("Choice : " + choice);

            switch (choice){
                case "1" -> openAccount(scanner,bankService);
                case "2" -> deposit(scanner);
                case "3" -> withdraw(scanner);
                case "4" -> transfer(scanner);
                case "5" -> statement(scanner);
                case "6" -> listAccount(scanner , bankService);
                case "7" -> searchAccount(scanner);
                case "0" -> running = false;
            }
        }
    }

    private static void openAccount(Scanner scanner, BankService bankService) {
        System.out.println("Customer Name: ");
        String name = scanner.nextLine().trim();
        System.out.println("Customer email: ");
        String email = scanner.nextLine().trim();
        System.out.println("Account Type (SAVINGS/CURRENT): ");
        String type = scanner.nextLine().trim().toUpperCase();
        System.out.println("Initial deposit (optional,blank for 0): ");
        String amountStr = scanner.next().trim();
        Double initial = Double.valueOf(amountStr);
        bankService.openAccount(name,email,type);
    }

    private static void deposit(Scanner scanner) {
    }

    private static void withdraw(Scanner scanner) {
    }

    private static void transfer(Scanner scanner) {
    }

    private static void statement(Scanner scanner) {
    }

    private static void listAccount(Scanner scanner , BankService bankService) {
        bankService.listAccount().forEach(a -> {
            System.out.println(a.getAccountNumber()+ " | " + a.getAccountType()+ " | " + a.getBalance());
        });
    }

    private static void searchAccount(Scanner scanner) {
    }
}
