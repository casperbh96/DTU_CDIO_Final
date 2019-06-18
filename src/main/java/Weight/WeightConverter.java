package main.java.Weight;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.*;
import static sun.misc.MessageUtils.out;

public class WeightConverter {

    private Scanner scanner = new Scanner(System.in);
    private String inRead = "";
    private BufferedReader in = null;
    private PrintWriter out = null;
    private boolean resultFromUserInput = false;
    private String send = "";
    private List<String> wrongInputList = new ArrayList<>(Arrays.asList("RM20 L", "RM20 I", "RM20 C"));
    private boolean shouldContinueFromInput = true;


    public void weightCommand(String cmd){
        String command = cmd;
        try (Socket socket = new Socket("169.254.2.3", 8000)){
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.flush();
            out.println(command);
            System.out.println(in.readLine());
            String tempReadLine = in.readLine();
            for(String stringFromList : wrongInputList){
                if(tempReadLine.toLowerCase().equals(stringFromList.toLowerCase())){
                    shouldContinueFromInput = false;
                }
            }
            if(!shouldContinueFromInput){
                inRead = in.readLine();
                System.out.println(inRead);
                shouldContinueFromInput = true;
            }


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
        for(int i = 0; i < split.length -1 ; i++){
            send = split[i];
        }
        if(inRead.contains("S S")){
            return send;
        } else {
            return null;
        }

        //double result = Double.parseDouble(send);
    }

    public String weightTara() throws IOException, InterruptedException {
        weightCommand("T");
        String[] split = inRead.split(" ");
        String send = "";
        for (int i = 0; i < split.length - 1; i++) {
            send = split[i];
        }
        if (inRead.contains("T S")) {
            return send;
        } else {
            return null;
        }
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

    public void getInputFromDisplay()throws IOException{
            while(!in.ready()){

        }
            in.readLine();
    }

    public void resetInputString(){
        inRead = "";
    }


}
