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
                case "2" -> deposit(scanner,bankService);
                case "3" -> withdraw(scanner,bankService);
                case "4" -> transfer(scanner , bankService);
                case "5" -> statement(scanner , bankService);
                case "6" -> listAccount(scanner , bankService);
                case "7" -> searchAccount(scanner , bankService);
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
        if (amountStr.isBlank()) amountStr = "0";
        Double initial = Double.valueOf(amountStr);
        String accountNumber = bankService.openAccount(name,email,type);
        if (initial > 0){
            bankService.deposit(accountNumber,initial, "Initial Deposit");
        }
        System.out.println("Account opened" + accountNumber);
    }

    private static void deposit(Scanner scanner , BankService bankService) {
        System.out.println("Account Number: ");
        String accountNumber = scanner.nextLine().trim();
        System.out.println("Amount: ");
        Double amount = Double.valueOf(scanner.nextLine().trim());
        bankService.deposit(accountNumber,amount,"Deposit");
        System.out.println("Deposited");
    }

    private static void withdraw(Scanner scanner , BankService bankService) {
        System.out.println("Account Number: ");
        String accountNumber = scanner.nextLine().trim();
        System.out.println("Amount: ");
        Double amount = Double.valueOf(scanner.nextLine().trim());
        bankService.withdraw(accountNumber,amount,"Withdrawal");
        System.out.println("Withdrawn");
    }

    private static void transfer(Scanner scanner, BankService bankService) {
        System.out.println("From Account: ");
        String from = scanner.nextLine().trim();
        System.out.println("To Account: ");
        String to = scanner.nextLine().trim();
        System.out.println("Amount: ");
        Double amount = Double.valueOf(scanner.nextLine().trim());
        bankService.transfer(from,to,amount,"Transfer");
    }

    private static void statement(Scanner scanner, BankService bankService){
        System.out.println("Account Number: ");
        String account = scanner.nextLine().trim();
        bankService.getStatement(account).forEach(t -> {
            System.out.println(t.getTimestamp() + " | " + t.getType() + " | " + t.getAmount() +  " | ");
        });
    }

    private static void listAccount(Scanner scanner , BankService bankService) {
        bankService.listAccount().forEach(a -> {
            System.out.println(a.getAccountNumber()+ " | " + a.getAccountType()+ " | " + a.getBalance());
        });
    }

    private static void searchAccount(Scanner scanner , BankService bankService) {
        System.out.println("Customer name contains: ");
        String q = scanner.nextLine().trim();
        bankService.searchAccountByCustomerName(q).forEach(account -> System.out.println(account.getAccountNumber() + " | " + account.getAccountType() + " | " + account.getBalance()));
    }
}
