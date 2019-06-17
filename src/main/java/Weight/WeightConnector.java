package main.java.Weight;

import com.sun.deploy.net.proxy.SunAutoProxyHandler;
import main.java.BusinessLogic.BLLUser;
import main.java.BusinessLogic.I_BLLUser;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.I_DAL_User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class WeightConnector{


    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        WeightConverter weightConverter = new WeightConverter();
        I_BLLUser user = new BLLUser();
        String userInput = null;
        int userId;
        String userName;


        while (weightConverter.StatusForUserResponse() == false) {
            try {
                userInput = weightConverter.writeInWeightDisplay("Indtast UserID");
                userInput = weightConverter.convertInputFromDisplayToString(userInput);
                System.out.println(userInput);

            } catch (IOException e) {

            }
        }

        try{
            weightConverter.writeLongTextToDisplay(user.getUserById(userId = Integer.parseInt(userInput.replaceAll("\\D+",""))).getUsername());
        } catch (SQLException e){

        }


    }












                /*
                try {
                    if(user.getUserById(weightInput).getUserId() == weightInput){
                        weightConverter.writeShortTextToDisplay("Velkommen");
                        weightConverter.writeLongTextToDisplay(user.getUserById(weightInput).getUsername());

                    } else{
                        weightConverter.writeLongTextToDisplay("ID ikke fundet");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

*/



}


