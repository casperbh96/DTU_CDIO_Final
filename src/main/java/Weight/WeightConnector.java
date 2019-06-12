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
    private String inRead = "";
    private BufferedReader in = null;
    private PrintWriter out = null;

    /*public void weightCommand(String cmd){
        String command = cmd;
        try (Socket socket = new Socket("localhost", 8000)){
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(command);
            out.flush();
            inRead = in.readLine();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void verifyUserFromId() throws IOException {

        System.out.println("Indtast Laborant ID");
        String text = "Mette";

        if (scanner.next().equals("12")){
            weightCommand("P111 \"" + text + "\"");

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
            weightCommand("P111 \"" + text + "\"" );
            System.out.println("Vægten skal være ubalanceret, skriv OK");
        }
        else{
            System.out.println("ID findes ikke, prøv igen");
            verifyUserFromId();
        }

    }

    */

        }