import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

class OnlineExam 
{
    HashMap<String, String> loginto = new HashMap<>();
    public static String userID;
    Scanner sc = new Scanner(System.in);

    // Method to hash passwords
    public static String hashPassword(String password) 
    {
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) 
            {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) 
        {
            throw new RuntimeException(e);
        }
    }

    // Non-static login method
    public void login() {
        System.out.println("---------------- WELCOME TO ONLINE EXAM SYSTEM ----------------");
        System.out.print("Enter User-Id: ");
        userID = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();
        loginto.put("Soumya", hashPassword("13579"));
        loginto.put("Soumyadip", hashPassword("20026"));
        loginto.put("Soumy", hashPassword("12345"));

        if (loginto.containsKey(userID) && loginto.get(userID).equals(hashPassword(password))) 
        {
            System.out.println("\nLOGIN SUCCESSFULLY...!");
            homepage(); // This now works since both methods are non-static
        } 
        else 
        {
            System.out.println("\nInvalid login credentials.!!! Please try again...");
            login();
        }
    }

    // Non-static homepage method
    public void homepage() 
    {
        System.out.println("-----------------------------------------------------");
        System.out.println("Online Examination System ---> Welcome " + OnlineExam.userID + "!");
        System.out.println("-----------------------------------------------------");
        System.out.println("Select 1 to Update password...");
        System.out.println("Select 2 to Start the test...");
        System.out.println("Select 3 to Logout...");
        System.out.println("-----------------------------------------------------");
        System.out.print("Select Option: ");
        int sp = sc.nextInt();
        switch (sp) 
        {
            case 1:
                loginto = update();
                homepage();
                break;
            case 2:
                solve();
                homepage();
                break;
            case 3:
                System.out.println("\nLogout Successfully...!");
                System.out.println("Thank You for Attending the Test...");
                System.out.println("-----------------------------------------------------");
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid Option Choice.! Try again...");
                homepage();
                break;
        }
    }

    // Password update method
    public HashMap<String, String> update() 
    {
        System.out.println("\n------------------ UPDATE PASSWORD ------------------");
        System.out.print("Enter Username: ");
        String uid = sc.next();
        System.out.print("Enter Old Password: ");
        String pass = sc.next();
        if (loginto.containsKey(uid) && loginto.get(uid).equals(hashPassword(pass))) 
        {
            System.out.println("Old Password Verified...");
            while (true) 
            {
                System.out.print("\nEnter New Password: ");
                String newPass = sc.next();
                System.out.print("ReEnter/Verify New Password: ");
                String conNewPass = sc.next();
                if (newPass.equals(conNewPass)) 
                {
                    loginto.replace(uid, hashPassword(newPass));
                    System.out.println("Password Updated Successfully...!");
                    break;
                }
                else 
                {
                    System.out.println("Passwords did not match. Please try again.");
                }
            }
        } else {
            System.out.println("Wrong password or user does not exist.");
        }
        return loginto;
    }

    // Solve method (test questions)
    public void solve() 
    {
        long start = System.currentTimeMillis();
        long terminate = start + 60 * 1000;
        int score = 0;
        int correct = 0, wrong = 0;
        char ans;

        System.out.println("\n\n----------------- STARTING THE TEST -----------------");
        System.out.println("You have 1 minute to answer as many questions as possible. Select the options wisely.");
        System.out.println("Each correct answer gives +5 points, and each wrong answer deducts 1 point.");

        while (System.currentTimeMillis() < terminate) 
        {
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("(Coding)-Q1. System.out.println(?; ===> Here System & out is...?");
            System.out.println("a. System & out both are classes\nb. System is class and out is object reference of OutputPrintStream\nc. System is class and out is object reference of PrintStream\nd. System is class and out is object reference of FilePrintStream");
            System.out.print("\nAnswer: ");
            ans = sc.next().charAt(0);
            if (ans == 'c') correct++; else wrong++;

            System.out.println("(Aptitude)-Q2. What is the least number to be subtracted from 945 to make it a perfect square?");
            System.out.println("a. 45\nb. 16\nc. 30\nd. 24");
            System.out.print("\nAnswer: ");
            ans = sc.next().charAt(0);
            if (ans == 'a') correct++; else wrong++;

            if (System.currentTimeMillis() >= terminate) 
            {
                break;
            }
        }

        score = (correct * 5) - wrong;
        System.out.println("Test Completed. Correct answers: " + correct + ", Wrong answers: " + wrong);
        System.out.println("Your score: " + score);
    }

    // Main method
    public static void main(String[] args) 
    {
        OnlineExam exam = new OnlineExam();
        exam.login();  // Non-static method call through instance
    }
}
