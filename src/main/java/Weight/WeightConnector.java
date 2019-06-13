package Weight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class WeightConnector{

    private Scanner scanner = new Scanner(System.in);
    private WeightConverter weightConverter = new WeightConverter();


    public void verifyUserFromId() throws IOException {

        System.out.println("Indtast Laborant ID");
        String text = "Mette";

        if (scanner.next().equals("12")){
            weightConverter.weightCommand("P111 \"" + text + "\"");

            System.out.println("Indtast OK for at godkende navn");
        }
        else {
            System.out.println("ID findes ikke, prøv igen");
            verifyUserFromId();
        }
    }

    public void getProductBatchFromID() throws IOException {

        if(scanner.next().toUpperCase().equals("OK")){
            System.out.println("Indtast productbatch ID");
        }

        if(scanner.next().equals("1234")){
            String text = "Saltvand";
            weightConverter.weightCommand("P111 \"" + text + "\"" );
            System.out.println("Vægten skal være ubalanceret, skriv OK");
        }
        else{
            System.out.println("ID findes ikke, prøv igen");
            verifyUserFromId();
        }

    }



        }