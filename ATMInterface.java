import java.util.Scanner;

public class ATMInterface {

    // Attributes for storing account details
    private double balance;
    private double previousTransaction;
    private String customerName;
    private String customerId;

    // Constructor
    public ATMInterface(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    // Method for depositing money
    public void deposit(double amount) {
        if (amount != 0) {
            balance += amount;
            previousTransaction = amount;
        }
    }

    // Method for withdrawing money
    public void withdraw(double amount) {
        if (amount != 0) {
            if (balance >= amount) {
                balance -= amount;
                previousTransaction = -amount;
            } else {
                System.out.println("Insufficient balance!");
            }
        }
    }

    // Method for displaying the previous transaction
    public void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Deposited: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrawn: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction occurred.");
        }
    }

    // Method to show the main menu
    public void showMenu() {
        char option;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome, " + customerName + "!");
        System.out.println("Your ID is: " + customerId);
        System.out.println();
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Exit");

        do {
            System.out.println("================================================");
            System.out.print("Enter an option: ");
            char input = scanner.next().charAt(0);
            option = Character.toUpperCase(input);
            System.out.println();

            switch (option) {
                case 'A':
                    System.out.println("------------------------------------------------");
                    System.out.println("Balance = $" + balance);
                    System.out.println("------------------------------------------------");
                    System.out.println();
                    break;

                case 'B':
                    System.out.println("------------------------------------------------");
                    System.out.print("Enter an amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    System.out.println("Deposited: $" + depositAmount);
                    System.out.println("------------------------------------------------");
                    System.out.println();
                    break;

                case 'C':
                    System.out.println("------------------------------------------------");
                    System.out.print("Enter an amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    System.out.println("Withdrawn: $" + withdrawAmount);
                    System.out.println("------------------------------------------------");
                    System.out.println();
                    break;

                case 'D':
                    System.out.println("------------------------------------------------");
                    getPreviousTransaction();
                    System.out.println("------------------------------------------------");
                    System.out.println();
                    break;

                case 'E':
                    System.out.println("****************");
                    break;

                default:
                    System.out.println("Invalid option! Please enter again.");
                    break;
            }

        } while (option != 'E');

        System.out.println("Thank you for using our services!");
        scanner.close();
    }

    public static void main(String[] args) {
        ATMInterface atm = new ATMInterface("Shital Teli", "JD7846");
        atm.showMenu();
    }
}
