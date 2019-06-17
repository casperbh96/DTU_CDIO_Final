package main.java.Weight;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static java.lang.Thread.*;

public class WeightConverter {

    private Scanner scanner = new Scanner(System.in);
    private String inRead = "";
    private BufferedReader in = null;
    private PrintWriter out = null;
    private boolean resultFromUserInput = false;
    private String send = "";

    public void weightCommand(String cmd){
        String command = cmd;
        try (Socket socket = new Socket("169.254.2.3", 8000)){
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.flush();
            out.println(command);
            inRead = in.readLine();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWeight() throws IOException {
        weightCommand("S");
        String[] split = inRead.split(" ");
        String send = "";
        for(int i = 0; i < split.length - 1 ; i++){
            send = split[i];
        }

        //double result = Double.parseDouble(send);
        return send;
    }

    public String WeightTara() throws IOException, InterruptedException {
        weightCommand("T");
        String[] split = inRead.split(" ");
        String send = "";
        for(int i = 0; i < split.length - 1 ; i++){
            send = split[i];
        }
        //double result = Double.parseDouble(send);
        return send;
    }

    public void backToWeightDisplay() throws IOException{
        weightCommand("DW");
    }

    public String writeInWeightDisplay(String message) throws IOException, InterruptedException {
        weightCommand("RM20 8 \"" + message + "\" \"\" \"&3\"");
        weightCommand("RM20 B crlf");

        //sleep(100);
        String[] split = inRead.split(" ");
        String send = "";
        for (int i = 0; i < split.length ; i++) {
            send = split[i];
        }

        return send;
    }

    public boolean StatusForUserResponse() throws IOException {
        boolean inputStatus = false;

        if (inRead.contains("RM20 B")) {
            inputStatus = false;
        } else if (inRead.contains("RM20 A")){
            inputStatus = true;
        } else {
            inputStatus = false;
        }

        return inputStatus;
    }

    public String convertInputFromDisplayToString(String str){
            String send = str.replaceAll("\\D+", "");
        return send;

    }

    public void writeLongTextToDisplay(String text) throws IOException{
        weightCommand("P111 \"" + text + "\"");
    }

    public void writeShortTextToDisplay(String text) throws IOException{
        weightCommand("D \"" + text + "\" crlf");
    }


}
