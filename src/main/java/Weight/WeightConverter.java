package Weight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Scanner;

public class WeightConverter {

    private Scanner scanner = new Scanner(System.in);
    private String inRead = "";
    private BufferedReader in = null;
    private PrintWriter out = null;

    public void weightCommand(String cmd){
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

    public double getWeight() throws IOException{
        weightCommand("S");
        String str = inRead.replaceAll("\\D+", "");
        double weight = Double.parseDouble(str);
        return weight;

    }

    public String getWeight2(){
        weightCommand("S");
        return inRead;
    }


    public double WeightTara() throws IOException{
        weightCommand("T");
        String str = inRead.replaceAll("\\D+", "");
        double weight = Double.parseDouble(str);
        return weight;
    }

    public void backToWeightDisplay() throws IOException{
        weightCommand("DW");
    }

    public String writeInWeightDisplay(String message) throws IOException {
        weightCommand("RM20 8 \"" + message + "\" \"\" \"&3\"");
        weightCommand("RM20 B crlf");
        String str = inRead;
        return inRead;
    }

    public void writeLongTextToDisplay(String text) throws IOException{
        weightCommand("P111 \"" + text + "\" crlf");
    }

    public void writeShortTextToDisplay(String text) throws IOException{
        weightCommand("D \"" + text + "\" crlf");


    }












}
