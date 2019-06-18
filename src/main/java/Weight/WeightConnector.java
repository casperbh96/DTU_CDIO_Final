package main.java.Weight;

import com.mysql.cj.util.StringUtils;
import com.sun.deploy.net.proxy.SunAutoProxyHandler;
import main.java.BusinessLogic.BLLRecipe;
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
        BLLRecipe bllRecipe = new BLLRecipe();
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

            weightConverter.resetInputString();

            while (weightConverter.StatusForUserResponse() == false){
                productbatchNumber = weightConverter.writeInWeightDisplay("Indtast produktbatcID");
                productbatchNumber = weightConverter.convertInputFromDisplayToString(productbatchNumber);
            }

            productbatchId = Integer.parseInt(productbatchNumber.replaceAll("\\D+", ""));

            //if(dao_productBatch.readSingleProductBatchById(productbatchId).getProductBatchId() == productbatchId){
            recipeId = dao_productBatch.readSingleProductBatchById(productbatchId).getRecipeId();


            List<REL_ProductBatchResourceBatchDTO> resourceBatchList = productBatchResourceBatch.readAllProductBatchResourceBatchByProductBatchId(productbatchId);
            List<REL_RecipeResourceDTO> recipeIngredientsList = bllRecipe.getAllResourcesForRecipe(recipeId);

            for(int i = 0; i < recipeIngredientsList.size(); i++){
                int resId = recipeIngredientsList.get(i).getResouceId();

                weightConverter.writeLongTextToDisplay("RåvareBatchId: " + String.valueOf(resId));
                Thread.sleep(3000);
                weightConverter.writeLongTextToDisplay("Sæt tom beholder på vægt");
                Thread.sleep(10000);
                int tara = Integer.valueOf(weightConverter.weightTara());


            }

        } catch (IOException e) {

        }catch(SQLException e){

        }

    }

}






