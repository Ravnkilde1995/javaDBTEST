package Niveau3_persistens;

public class Facade {

    static MinListe minListe = new MinListe();

    public static void indsæt(){


        //Mapper.indsæt();

        minListe.inset();
    }

    public static void udskriv(){

        //Mapper.udskriv();

        minListe.udskriv();
    }

    public static void slet(){

        //Mapper.slet();

        minListe.slet();
    }

    public static void update(){

        Mapper.update();
    }


}
