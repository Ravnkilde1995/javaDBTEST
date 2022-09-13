import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

       String svar;

        while (true) {

            svar = getString("Hvad ønsker du? opret/udskriv/slet/update");

            switch (svar){

                case "opret":
                    indsæt();
                    break;

                case "udskriv":
                    udskriv();
                    break;

                case "slet":
                    slet();
                    break;

                case "update":
                    update();
                    break;

                    default:
                    System.out.println("Den fangede jeg sgu ikke lige");


            }
        }
    }

    private static void update() {
        udskriv();

        String sql = "update  Navne set navne = ?  where idNavne = ?";


        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            String kundeNavn = getString("angiv nyt navn");

            ps.setString(1, kundeNavn);


            // det er det her jeg søger på.
            ps.setInt(2, getInt("Skriv et tal"));


            int res = ps.executeUpdate();    //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            if (res > 0) {

                System.out.println("Kunden med navnet " + "\"" + kundeNavn + "\"" + " er nu blevet opdateret");
            } else {

                System.out.println("en kunde med navnet " + "\"" + kundeNavn + "\"" + " fandtes ikke i listen");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        udskriv();
    }

    private static void slet() {
        udskriv();

        String sql = "delete from Navne where navne = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            String kundeNavn = getString("Skriv navnet på den kunde der skal slettes ");

            ps.setString(1, kundeNavn);


            int res = ps.executeUpdate();       //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            if (res > 0) {

                System.out.println("Kunden med navnet " + "\"" + kundeNavn + "\"" + " er nu blevet slettet");
            } else {

                System.out.println("en kunde med navnet " + "\"" + kundeNavn + "\"" + " fandtes ikke i listen");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        udskriv();
    }

    private static void indsæt() {
        String sql = "INSERT INTO Navne (navne) VALUES (?)";


        // se lige try-with-resources f.eks. her  https://www.baeldung.com/java-try-with-resources
        try (Connection con = DBConnection.createConnection();  // får en connection

             // se evt. https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            // her klargøres mit prepared statement ved at min parametre indsættes.
            ps.setString(1, getString("Skriv et navn"));


            ps.executeUpdate();    //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);


            System.out.println("vi er nået til række : " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void udskriv() {
        List<String> kundeList = new ArrayList<>();

        String sql = "select * from Navne ";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {           // https://en.wikipedia.org/wiki/Prepared_statement


            ResultSet resultSet = ps.executeQuery();   //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            while (resultSet.next()) {
                int id = resultSet.getInt("idNavne");
                String navn = resultSet.getString("Navne");

                kundeList.add(id + " : " + navn);

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        for (String s : kundeList) {

            System.out.println(s);

        }
    }

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
