package main.java.Weight;

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
import java.util.Scanner;

public class WeightConnector{

        public static void main(String[] args) throws IOException, InterruptedException, SQLException {

            WeightConverter weightConverter = new WeightConverter();
            I_BLLUser user = new BLLUser();
            int userInput;


            while(user == null){
                userInput = weightConverter.writeInWeightDisplay("Indtast UserID");
                try {
                    if(user.getUserById(userInput).getUserId() == userInput){
                        weightConverter.writeShortTextToDisplay("Velkommen");
                        weightConverter.writeLongTextToDisplay(user.getUserById(userInput).getUsername());

                    } else{
                        weightConverter.writeLongTextToDisplay("ID ikke fundet");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }





        }


        }