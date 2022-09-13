public class Main {


    public static void main(String[] args) {

       String svar;

        while (true) {

            svar = TerminalInput.getString("Hvad ønsker du? opret/udskriv/slet/update");

            switch (svar){

                case "opret":
                    Mapper.indsæt();
                    break;

                case "udskriv":
                    Mapper.udskriv();
                    break;

                case "slet":
                    Mapper.slet();
                    break;

                case "update":
                    Mapper.update();
                    break;

                    default:
                    System.out.println("Den fangede jeg sgu ikke lige");


            }
        }
    }


}
