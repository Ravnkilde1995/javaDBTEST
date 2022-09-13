import java.util.Scanner;

public class TerminalInput {
    public static int getInt(String s){

        while (true) {
            try {
                int ans = Integer.parseInt(getString(s));
                return ans;
            } catch (NumberFormatException e) {
                System.out.println("Husk ingen tal ord");
            }
        }

    }

    public static String getString(String s) {

        System.out.println(s + " : ");
        Scanner scan = new Scanner(System.in);

        return scan.nextLine();


    }
}
