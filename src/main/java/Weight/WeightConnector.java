package main.java.Weight;

import com.mysql.cj.util.StringUtils;
import com.sun.deploy.net.proxy.SunAutoProxyHandler;
import main.java.BusinessLogic.*;
import main.java.Core.*;
import main.java.DataAccess.dao.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class WeightConnector {

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        WeightConverter weightConverter = new WeightConverter();
        BLLUser user = new BLLUser();
        BLLRecipe bllRecipe = new BLLRecipe();
        BLLProductBatchResourceBatch bllProductBatchResourceBatch = new BLLProductBatchResourceBatch();
        UserDTO userObject = null;
        String userInput = null;
        String productbatchNumber = null;
        int userId;
        int[] resourceBatchList = null;
        DAO_ProductBatch dao_productBatch = new DAO_ProductBatch();
        int productbatchId;
        DAO_REL_RecipeResource recipe = new DAO_REL_RecipeResource();
        List<String> ingredients = null;
        int recipeId;
        DAO_REL_ProductBatchResourceBatch productBatchResourceBatch = new DAO_REL_ProductBatchResourceBatch();
        REL_ProductBatchResourceBatchDTO productBatchResourceBatchDTO = null;
        DAO_ResourceBatch dao_resourceBatch = new DAO_ResourceBatch();
        String respond = null;
        String netAmount;
        boolean status = false;

        try {
            // while loop som kører indtil der kommer svar fra user input på vægten
            while (weightConverter.statusForUserResponse() == false) { //sørger for at input fra vægten bliver læst korrekt

                userInput = weightConverter.writeInWeightDisplay("Indtast UserID");
                userInput = weightConverter.convertInputFromDisplayToString(userInput);

                //System.out.println(userInput);
            }

            boolean isUserInputEmpty = false;


            if(userInput.equals("")){
                //hvis userInput er en tom string kører programmet forfra
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("proev igen");
                }
                weightConverter.resetInputString();
                main(args);
            }


            // konvertere input fra vægt fra string til integer
            userId = Integer.parseInt(userInput.replaceAll("\\D", ""));
            boolean isUserFound = false;

            try{
                isUserFound = user.getUserById(userId).getUserId() == userId;
            } catch(SQLException ex){
                //hvis useren ikke findes i databasen kører programmet forfra
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("proev igen");
                }
                weightConverter.resetInputString();
                main(args);
            }

            //sørger for at useren findes i databasen
            if (isUserFound) {
                userObject = user.getUserById(userId);
                System.out.println(userObject.toString());
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay(user.getUserById(userId).getUsername());
                }
                //weightConverter.writeLongTextToDisplay(user.getUserById(userId).getUsername());
            }

            // nulstiller string i vægtinput
            weightConverter.resetInputString();

            //while loop kører indtil der kommer productbatchnumber som svar til fra user input
            while (weightConverter.statusForUserResponse() == false){
                productbatchNumber = weightConverter.writeInWeightDisplay("Indtast produktbatcID");
                productbatchNumber = weightConverter.convertInputFromDisplayToString(productbatchNumber);
            }


            if(productbatchNumber.equals("")){
                //hvis userInput er en tom string kører programmet forfra
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("proev igen");
                }
                weightConverter.resetInputString();
                main(args);
            }

            productbatchId = Integer.parseInt(productbatchNumber.replaceAll("\\D+", ""));
            boolean isProductBatchFound = false;

            try{
                isProductBatchFound = dao_productBatch.readSingleProductBatchById(productbatchId).getProductBatchId() == productbatchId;
            } catch(SQLException ex){
                //hvis productbatchID ikke findes i databasen kører programmet forfra
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("proev igen");
                }
                weightConverter.resetInputString();
                main(args);
            }

            // henter productBatchDTO som skal gemmes i senere i proceduren
            ProductBatchDTO productBatchDTO = dao_productBatch.readSingleProductBatchById(productbatchId);

            // henter recipeId af productBatchDTO
            recipeId =productBatchDTO.getRecipeId();

            // henter en liste af REL_ProductResourceBatchDTOér
            List<REL_ProductBatchResourceBatchDTO> resourceBatchDTOList = productBatchResourceBatch.readAllProductBatchResourceBatchByProductBatchId(productbatchId);

            if(resourceBatchDTOList.size() == 0){
                //hvis listen har nul elementer kører programmet forfra
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("proev igen");
                }
                weightConverter.resetInputString();
                main(args);
            }

            // sætter alle resourcebatchnr ind som en ny resourcebatchId liste
            for(REL_ProductBatchResourceBatchDTO rel : resourceBatchDTOList){
                System.out.println(rel);
                for(int i = 0 ; i < resourceBatchDTOList.size() ; i++){
                    resourceBatchList = new int[resourceBatchDTOList.size()];
                    resourceBatchList[i] = rel.getResourceBatchId();
                }
            }

            // henter en liste over recipeIngrediens som skal bruges i opskriften
            List<REL_RecipeResourceDTO> recipeIngredientsList = bllRecipe.getAllResourcesForRecipe(recipeId);

            // starter afvejningsprocedure ved at loope over hver råvarebatch som er i den udhentede resourceBatchList
            for(int i = 0; i < resourceBatchDTOList.size(); i++){
                int resId = recipeIngredientsList.get(i).getResouceId();
                weightConverter.writeLongTextToDisplay("raavareBatch: " + resourceBatchList[i] );

                // skriver en besked til brugeren på vægten, som så skal svare ok på vægten
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("Afbalanceret vaegt");
                }

                // skriver en besked til brugeren på vægten, som så skal svare ok på vægten
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("saet beholder");
                }

                // gemmer taravægt i variablen tara
                String tara = weightConverter.weightTara();

                // henter tolerence og ressourceAmount fra databasen, og regner max og min Amount med tolerence
                double Tolerence = recipe.readSingleRecipeResourcebyId(resId,recipeId, Date.valueOf("9999-12-31")).getTolerance();
                double resourceAmount = recipe.readSingleRecipeResourcebyId(resId, recipeId, Date.valueOf("9999-12-31")).getResourceAmount();
                double amountWithTolerencePos = resourceAmount + (resourceAmount * (Tolerence/100));
                double amountWithTolerenceNeg = resourceAmount - (resourceAmount * (Tolerence/100));

                // vejprocedure på vægten
                do {

                    //sender besked til brugeren i vægtdisplay for det batchnr og hvor meget der skal vejes af denne ingrediens
                    weightConverter.writeLongTextToDisplay("Batchnr: " + resourceBatchList[i] + ", afvej " + resourceAmount + " kg ");
                    weightConverter.backToWeightDisplay();

                    // sleep i 10 sekunder for at brugeren kan veje ressourcebatch
                    Thread.sleep(10000);

                    // sender vægten til variablen netAmount
                    netAmount = weightConverter.getWeight();
                    System.out.println(netAmount);

                    // status på om netAmount er inden for max og min tolerence, hvis ikke vejes resourcebatch indtil den rette netAmount er fundet
                    status = (Double.valueOf(netAmount) >= amountWithTolerenceNeg) & (Double.valueOf(netAmount) <= amountWithTolerencePos);

                    System.out.println(status);


                } while(status == false);

                // efter første vægtprocedure gemmes startdate og status i productbatch tabellen i databasen
                if (i == 0) {
                    productBatchDTO.setCreationDate(new Date(System.currentTimeMillis()));
                    productBatchDTO.setProductionStatus("under produktion");
                    dao_productBatch.updateSingleProductBatch(productBatchDTO);
                }

                // instantiere resourceBatchDTO med resourceBatchnummer, det samme med rel_productBatchResourceBatchDTO
                ResourceBatchDTO resourceBatchDTO = dao_resourceBatch.readSingleResourceBatchById(resourceBatchList[i]);
                REL_ProductBatchResourceBatchDTO rel_productBatchResourceBatchDTO = productBatchResourceBatch.readSingleProductBatchResourceBatchbyId(resourceBatchList[i], productbatchId);

                // Når råvarebatch i er mindre eller samme størrelse som listen gemmes resourceAmount - nettovægt som er vejet i resourceBatch
                // Derudover gemmes tara og nettoAmount fra vægtproceduren under den bestemte råvarebatchid-procedure
                if (i <= resourceBatchDTOList.size()) {

                    resourceBatchDTO.setResourceBatchAmount(resourceBatchDTO.getResourceBatchAmount() - Double.parseDouble(netAmount));
                    dao_resourceBatch.updateSingleResourceBatch(resourceBatchDTO);

                    rel_productBatchResourceBatchDTO.setTara(Double.valueOf(tara));
                    rel_productBatchResourceBatchDTO.setNetAmount(Double.valueOf(netAmount));
                    productBatchResourceBatch.updateSingleProductBatchResourceBatch(rel_productBatchResourceBatchDTO);
                }

                // Hvis råvaren er den sidste i listen, og der ikke skal vejes flere råvarebatches afslutter programmet
                if (i == resourceBatchDTOList.size() -1) {

                    productBatchDTO.setProductionStatus("afsluttet");
                    productBatchDTO.setProductionEndDate(new Date(System.currentTimeMillis()));
                    dao_productBatch.updateSingleProductBatch(productBatchDTO);
                    weightConverter.writeInWeightDisplay("farvel");
                    Thread.sleep(3000);

                    //programmet slutter
                    System.exit(0);
                }


            }

                //TOD0 gemme opdatering i databasen
                //productBatchResourceBatch.updateSingleProductBatchResourceBatch(resId,productbatchId,netAmountDouble,taraToDouble);



        } catch (IOException e) {

        }catch(SQLException e){

        }

    }

}






