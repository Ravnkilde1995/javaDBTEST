package Niveau3_persistens;

import Niveau2_logic.TerminalInput;

import java.util.LinkedList;
import java.util.List;

public class MinListe {

    List<String> list = new LinkedList<>();


    public MinListe() {
        list.add("Palle");
        list.add("Ole");
        list.add("OleBingo");
        list.add("OleBango");
    }

    protected void inset(){
        list.add(TerminalInput.getString("skriv et navn"));
    }


    protected void slet(){

        list.remove(TerminalInput.getString("Skriv navnet på den kunde som skal slettes"))
    }

    protected void udskriv(){

        for (String s : list) {
            System.out.println(s);
            
        }
    }

}
