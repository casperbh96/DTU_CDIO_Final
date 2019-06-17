package main.java.Weight;

import com.mysql.cj.util.StringUtils;
import com.sun.deploy.net.proxy.SunAutoProxyHandler;
import main.java.BusinessLogic.BLLUser;
import main.java.BusinessLogic.I_BLLUser;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.DAO_User;
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

public class WeightConnector {


    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        WeightConverter weightConverter = new WeightConverter();
        BLLUser user = new BLLUser();
        UserDTO userObject = null;
        String userInput = null;
        int userId;



        try {
            do {

                while (weightConverter.StatusForUserResponse() == false) { //sørger for at input fra vægten bliver læst korrekt

                    userInput = weightConverter.writeInWeightDisplay("Indtast UserID");
                    //weightConverter.getInputFromDisplay();
                    userInput = weightConverter.convertInputFromDisplayToString(userInput);

                    //System.out.println(userInput);

                }

                // konvertere input fra vægt fra string til integer
                userId = Integer.parseInt(userInput.replaceAll("\\D", ""));

                //sørger for at useren findes i databasen
                if (user.getUserById(userId).getUserId() == userId) {
                    userObject = user.getUserById(userId);
                    System.out.println(userObject.toString());
                    weightConverter.writeLongTextToDisplay(user.getUserById(userId).getUsername());
                }

            } while(!(user.getUserById(userId).getUserId() == userId));






        } catch (IOException e) {

        }catch(SQLException e){

        }

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





