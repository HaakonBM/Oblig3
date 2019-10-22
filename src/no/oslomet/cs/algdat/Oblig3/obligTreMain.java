package no.oslomet.cs.algdat.Oblig3;

import java.util.Comparator;

public class obligTreMain {

    public static void main (String[] args) {

        //Oppgave 0
        ObligSBinTre<String> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        System. out .println(tre.antall());

    }
}
