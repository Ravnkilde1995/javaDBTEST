import Niveau2_logic.TerminalInput;
import Niveau3_persistens.Facade;
import Niveau3_persistens.Mapper;

public class Main {


    public static void main(String[] args) {

       String svar;

        while (true) {

            svar = TerminalInput.getString("Hvad ønsker du? opret/udskriv/slet/update");

            switch (svar){

                case "opret":
                    Facade.indsæt();
                    break;

                case "udskriv":
                    Facade.udskriv();
                    break;

                case "slet":
                    Facade.slet();
                    break;

                case "update":
                    Facade.update();
                    break;

                    default:
                    System.out.println("Den fangede jeg sgu ikke lige");


            }
        }
    }


}
