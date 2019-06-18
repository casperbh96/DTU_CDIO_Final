package main.java.Weight;

import com.mysql.cj.util.StringUtils;
import com.sun.deploy.net.proxy.SunAutoProxyHandler;
import main.java.BusinessLogic.BLLUser;
import main.java.BusinessLogic.I_BLLUser;
import main.java.Core.ProductBatchDTO;
import main.java.Core.REL_ProductBatchResourceBatchDTO;
import main.java.Core.REL_RecipeResourceDTO;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class WeightConnector {


    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        WeightConverter weightConverter = new WeightConverter();
        BLLUser user = new BLLUser();
        UserDTO userObject = null;
        String userInput = null;
        String productbatchNumber = null;
        int userId;
        String[] strings = null;
        DAO_ProductBatch dao_productBatch = new DAO_ProductBatch();
        int productbatchId;
        DAO_REL_RecipeResource recipe = new DAO_REL_RecipeResource();
        List<String> ingredients = null;
        int recipeId;
        DAO_REL_ProductBatchResourceBatch productBatchResourceBatch = new DAO_REL_ProductBatchResourceBatch();
        REL_ProductBatchResourceBatchDTO productBatchResourceBatchDTO = null;






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

            while (weightConverter.StatusForUserResponse() == false){
                productbatchNumber = weightConverter.writeInWeightDisplay("Indtast produktbatcID");
                productbatchNumber = weightConverter.convertInputFromDisplayToString(productbatchNumber);
            }

            productbatchId = Integer.parseInt(productbatchNumber.replaceAll("\\D+", ""));

            //if(dao_productBatch.readSingleProductBatchById(productbatchId).getProductBatchId() == productbatchId){
            //recipeId = dao_productBatch.readSingleProductBatchById(productbatchId).getRecipeId();


            List<REL_ProductBatchResourceBatchDTO> resourceBatchList = productBatchResourceBatch.readProductBatchResourceBatchbySearch(productbatchNumber);
            List<REL_RecipeResourceDTO> recipeIngredientsList = recipe.readRecipeResourcebySearch(String.valueOf(dao_productBatch.readSingleProductBatchById(productbatchId).getRecipeId()));


                for(int i = 0 ; i < recipeIngredientsList.size() ; i++){
                    int integer = recipeIngredientsList.get(i).getResouceId();
                    String str = String.valueOf(integer);
                    while(weightConverter.StatusForUserResponse()){
                        weightConverter.writeLongTextToDisplay("RåvareBatchId" + str);
                        weightConverter.getInputFromDisplay();
                        weightConverter.writeLongTextToDisplay("Sæt tom beholder på vægt");

                    }

                    weightConverter.writeLongTextToDisplay("Er vægten afbalanceret? tryk enter");
                    weightConverter.getInputFromDisplay();




                }


            //}






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





