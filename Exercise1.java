import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) throws IOException {

        // Creating HashMap object called userNamePassword that stores username as String key and password as String value
        HashMap<String, String> userNamePassword = new HashMap<>();
        // Creating HashMap object called userNameFullName that stores username as String key and full name as String value
        HashMap<String, String> userNameFullName = new HashMap<>();

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the filename to read from: ");
        // prompting the user for input
        String filename = in.nextLine();
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        // While loop reads the input file
        while (inputFile.hasNext()) {
            // data splits at tab space and assigns appropriate values for every line in input file
            String[] Data = inputFile.nextLine().split("\t");
            String fullName = Data[0];
            String userName = Data[1];
            String passCode = Data[2];

            // putting the values username and password into the map
            userNamePassword.put(userName, passCode);
            // putting the values username and fullName into the map
            // helps in getting the full name of the user when successful login
            userNameFullName.put(userName, fullName);
        }

        // total attempts of login chances are limited to 3
        int chances = 3;
        boolean logIn = false;

        // do while loop checks if there are still chances for logging in and also the login status
        do {
            // prompting the user to enter username and passcode
            System.out.print("Login: ");
            String userName = in.nextLine();
            System.out.print("Password: ");
            String password = in.nextLine();

            // if condition checks whether the username and passcode matches with login database or not
            // if matches, prints the welcome message or else decrements the chance count and asks to prompt the correct credentials again
            if ((!userNamePassword.get(userName).equals(password))) {
                // decrements the chances
                chances--;

                // if the login details are wrong continuously for 3 times, it prints "Incorrect login"
                if (chances == 0) {
                    System.out.println("\nSorry. Incorrect login. Please contact the system administrator.");
                } else {

                    // if condition checks the number of attempts and if the attempts are more than one, plural keyword "attempts" should be placed at end of message
                    if (chances > 1) {
                        // message gets printed when either username or password is printed incorrectly
                        System.out.println("\nEither the username or password is incorrect. You have " + chances + " more attempts.");

                        // else, if the attempts left are only 1, singular keyword "attempt" should be placed at end of message
                    } else {
                        System.out.println("\nEither the username or password is incorrect. You have " + chances + " more attempt.");
                    }
                }

            } else {
                // if matches, prints the welcome message with user fullName
                System.out.println("\nLogin successful");
                System.out.println("Welcome " +userNameFullName.get(userName));
                logIn = true;
            }
        } while (chances > 0 && !logIn);
    }
}



