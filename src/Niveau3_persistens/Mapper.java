package Niveau3_persistens;

import Niveau2_logic.TerminalInput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    static void update() {
        udskriv();

        String sql = "update  Navne set navne = ?  where idNavne = ?";


        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            String kundeNavn = TerminalInput.getString("angiv nyt navn");

            ps.setString(1, kundeNavn);


            // det er det her jeg søger på.
            ps.setInt(2, TerminalInput.getInt("Skriv et tal"));


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

    static void slet() {
        udskriv();

        String sql = "delete from Navne where navne = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            String kundeNavn = TerminalInput.getString("Skriv navnet på den kunde der skal slettes ");

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

    static void indsæt() {
        String sql = "INSERT INTO Navne (navne) VALUES (?)";


        // se lige try-with-resources f.eks. her  https://www.baeldung.com/java-try-with-resources
        try (Connection con = DBConnection.createConnection();  // får en connection

             // se evt. https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            // her klargøres mit prepared statement ved at min parametre indsættes.
            ps.setString(1, TerminalInput.getString("Skriv et navn"));


            ps.executeUpdate();    //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);


            System.out.println("vi er nået til række : " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void udskriv() {
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
}
