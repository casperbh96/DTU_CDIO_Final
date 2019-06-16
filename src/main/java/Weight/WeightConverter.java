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

    public void weightCommand(String cmd){
        String command = cmd;
        try (Socket socket = new Socket("localhost", 8000)){
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

    public double getWeight() throws IOException {
        weightCommand("S");
        String[] split = inRead.split(" ");
        String send = "";
        for(int i = 0; i < split.length ; i++){
            send = split[i];
        }
        double result = Double.parseDouble(send);
        return result;
    }

    public double WeightTara() throws IOException, InterruptedException {
        weightCommand("T");
        String[] split = inRead.split(" ");
        String send = "";
        for(int i = 0; i < split.length ; i++){
            send = split[i];
        }
        double result = Double.parseDouble(send);
        return result;
    }

    public void backToWeightDisplay() throws IOException{
        weightCommand("DW");
    }

    public int writeInWeightDisplay(String message) throws IOException, InterruptedException {
        weightCommand("RM20 8 \"" + message + "\" \"\" \"&3\"");
        weightCommand("RM20 B crlf");
        //sleep(100);
        String[] split = inRead.split(" ");
        String send = "";
        for (int i = 0; i < split.length ; i++) {
            send = split[i];
        }
        int result = Integer.parseInt(send);
        return result;
    }

    public boolean StatusForUserResponse() throws IOException {
        boolean inputStatus = false;

        if (!inRead.contains("RM20 A")) {
            inputStatus = false;
        } else {
            inputStatus = true;
        }

        return inputStatus;
    }

    public void writeLongTextToDisplay(String text) throws IOException{
        weightCommand("P111 \"" + text + "\"");
    }

    public void writeShortTextToDisplay(String text) throws IOException{
        weightCommand("D \"" + text + "\" crlf");
    }


}
