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
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class WeightProgram {

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        WeightConverter weightConverter = new WeightConverter();
        BLLUser user = new BLLUser();
        BLLProductBatchResourceBatch bllProductBatchResourceBatch = new BLLProductBatchResourceBatch();
        String userInput = null;
        String productbatchNumber = null;
        int userId;
        int[] resourceBatchList = null;
        DAO_ProductBatch dao_productBatch = new DAO_ProductBatch();
        int productbatchId;
        DAO_REL_RecipeResource recipe = new DAO_REL_RecipeResource();
        int recipeId;
        DAO_REL_ProductBatchResourceBatch productBatchResourceBatch = new DAO_REL_ProductBatchResourceBatch();
        REL_ProductBatchResourceBatchDTO productBatchResourceBatchDTO = null;
        DAO_ResourceBatch dao_resourceBatch = new DAO_ResourceBatch();
        String netAmount;
        boolean status = false;
        int resourceBatchId;
        String resourceBatchIdAsString = null;
        int resourceId;

        try {
            // while loop som kører indtil der kommer svar fra user input på vægten
            while (weightConverter.statusForUserResponse() == false) {

                userInput = weightConverter.writeInWeightDisplay("Indtast UserID");
                userInput = weightConverter.convertInputFromDisplayToString(userInput);

            }

            boolean isUserInputEmpty = false;

            //hvis userInput er en tom string kører programmet forfra
            if(userInput.equals("")){

                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("proev igen");
                }
                weightConverter.resetInputString();
                main(args);
            }

            // konvertere input fra vægt fra string til integer
            userId = Integer.parseInt(userInput.replaceAll("\\D", ""));

            // sørger for at useren findes i databasen
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

                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay(user.getUserById(userId).getUsername());
                }

            // nulstiller string i vægtinput
            weightConverter.resetInputString();

            //while loop kører indtil der kommer productbatchnumber som svar til fra user input
            while (weightConverter.statusForUserResponse() == false){
                productbatchNumber = weightConverter.writeInWeightDisplay("Indtast produktbatcID");
                productbatchNumber = weightConverter.convertInputFromDisplayToString(productbatchNumber);
            }

            //hvis userInput er en tom string kører programmet forfra
            if(productbatchNumber.equals("")){

                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("proev igen");
                }
                weightConverter.resetInputString();
                main(args);
            }

            productbatchId = Integer.parseInt(productbatchNumber.replaceAll("\\D+", ""));

            // tjekker om productbatchen findes i databasen, hvis ikke startes programmet forfra
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
            recipeId = productBatchDTO.getRecipeId();

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
            int index = 0;
            resourceBatchList =  new int[resourceBatchDTOList.size()];
            // sætter alle resourcebatchnr ind som en ny resourcebatchId liste
            for(REL_ProductBatchResourceBatchDTO rel : resourceBatchDTOList){
                System.out.println(rel);
                resourceBatchList[index] = rel.getResourceBatchId();
                /*for(int i = 0 ; i < resourceBatchDTOList.size() ; i++){
                    resourceBatchList = new int[resourceBatchDTOList.size()];
                    resourceBatchList[i] = rel.getResourceBatchId();
                }*/
                index++;
            }

            if(resourceBatchDTOList.size() == 0){
                // skriver en besked til brugeren på vægten, som så skal svare ok på vægten
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("ingen ingredienser");
                }
                main(args);
            }

            //loop som gøre over afvejningsproceduren, når alle råvarebatches er afvejet afslutter programmet
            for(int i = 0; i < resourceBatchDTOList.size(); i++){

                //nulstiller vægtdisplay for at gøre klar til ny tare og afvejning på næste råvarebatchID
                weightConverter.backToWeightDisplay();

                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    resourceBatchIdAsString = weightConverter.writeInWeightDisplay("Indtast raavarebatchId");
                    resourceBatchIdAsString = weightConverter.convertInputFromDisplayToString(resourceBatchIdAsString);
                }

                if(resourceBatchIdAsString.equals("")){

                    weightConverter.resetInputString();
                    while(weightConverter.statusForUserResponse() == false){
                        weightConverter.writeInWeightDisplay("proev igen");
                    }
                    weightConverter.resetInputString();
                    main(args);
                }

                resourceBatchId = Integer.valueOf(resourceBatchIdAsString);

                boolean isResourceBatchIdFound = false;
                try{
                    isResourceBatchIdFound = dao_resourceBatch.readSingleResourceBatchById(resourceBatchId).getResourceBatchId() == productbatchId;
                } catch(SQLException ex){
                    //hvis råvarebatchID ikke findes i databasen kører programmet forfra
                    weightConverter.resetInputString();
                    while(weightConverter.statusForUserResponse() == false){
                        weightConverter.writeInWeightDisplay("proev igen");
                    }
                    weightConverter.resetInputString();
                    main(args);
                }

                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("Afbalanceret vaegt?");
                }

                // skriver en besked til brugeren på vægten, som så skal svare ok på vægten
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("saet beholder");
                }

                // gemmer taravægt i variablen tara
                String tara = weightConverter.weightTara();

                // henter tolerence og ressourceAmount fra databasen, og regner max og min Amount med tolerence
                resourceId = dao_resourceBatch.readSingleResourceBatchById(resourceBatchId).getResourceId();
                REL_RecipeResourceDTO recResDTO = recipe.readSingleRecipeResourcebyId(resourceId,recipeId, new StringToSqlDateConverter().convertStringToDate("9999-12-30"));
                double Tolerence = recResDTO.getTolerance();
                double resourceAmount = recResDTO.getResourceAmount();
                double amountWithTolerencePos = resourceAmount + (resourceAmount * (Tolerence/100));
                double amountWithTolerenceNeg = resourceAmount - (resourceAmount * (Tolerence/100));

                do {

                    //sender besked til brugeren i vægtdisplay for det batchnr og hvor meget der skal vejes af denne ingrediens
                    weightConverter.writeLongTextToDisplay("Batchnr: " + resourceBatchId + ", afvej " + resourceAmount + " kg ");
                    weightConverter.backToWeightDisplay();

                    // sleep i 10 sekunder for at brugeren kan veje ressourcebatch
                    Thread.sleep(10000);

                    // sender vægten til variablen netAmount
                    netAmount = weightConverter.getWeight();
                    System.out.println(netAmount);

                    // status på om netAmount er inden for max og min tolerence, hvis ikke vejes resourcebatch indtil den rette netAmount er fundet
                    status = (Double.valueOf(netAmount) >= amountWithTolerenceNeg) & (Double.valueOf(netAmount) <= amountWithTolerencePos);

                    // hvis afvejningen er korrekt skrives dette til brugeren som skal trykke ok på vægten
                    if(status == true){
                        weightConverter.resetInputString();
                        while(weightConverter.statusForUserResponse() == false){
                            weightConverter.writeInWeightDisplay("afvejning korrekt");
                        }
                        //nulstiller vægtdisplay for at gøre klar til ny tare og afvejning på næste råvarebatchID
                        weightConverter.backToWeightDisplay();
                    }

                } while(status == false);

                //besked bliver sendt til vægtdisplay om at brugeren skal fjerne beholderen fra vægten
                weightConverter.resetInputString();
                while(weightConverter.statusForUserResponse() == false){
                    weightConverter.writeInWeightDisplay("fjern beholder");
                }



                //efter første råvarebatch er afvejet opdateres creationDate og gemmes i databasen, samt produktionsstatus som opdateres til "under produktion".
                if (i == 0) {
                    productBatchDTO.setCreationDate(new StringToSqlDateConverter().convertStringToDate(new Date(System.currentTimeMillis()).toString()));
                    productBatchDTO.setProductionStatus("under produktion");
                    dao_productBatch.updateSingleProductBatch(productBatchDTO);
                }

                // resourceBatchDTO mmed resourceBatchnummer hentes som objekt, det samme med rel_productBatchResourceBatchDTO
                ResourceBatchDTO resourceBatchDTO = dao_resourceBatch.readSingleResourceBatchById(resourceBatchId);
                REL_ProductBatchResourceBatchDTO rel_productBatchResourceBatchDTO = productBatchResourceBatch.readSingleProductBatchResourceBatchbyId(resourceBatchId, productbatchId);

                // Når råvarebatch i er mindre eller samme størrelse som listen gemmes resourceAmount - nettovægt som er vejet i resourceBatch
                // Derudover gemmes tara og nettoAmount fra vægtproceduren under den bestemte råvarebatchid-procedure
                if (i <= resourceBatchList.length-1) {

                    resourceBatchDTO.setResourceBatchAmount(resourceBatchDTO.getResourceBatchAmount() - Double.parseDouble(netAmount));
                    dao_resourceBatch.updateSingleResourceBatch(resourceBatchDTO);

                    rel_productBatchResourceBatchDTO.setTara(Double.valueOf(tara));
                    rel_productBatchResourceBatchDTO.setNetAmount(Double.valueOf(netAmount));
                    productBatchResourceBatch.updateSingleProductBatchResourceBatch(rel_productBatchResourceBatchDTO);
                }

                // Hvis råvaren er den sidste i listen, og der ikke skal vejes flere råvarebatches afslutter programmet
                if (i == resourceBatchList.length-1) {

                    productBatchDTO.setProductionStatus("afsluttet");
                    productBatchDTO.setProductionEndDate(new StringToSqlDateConverter().convertStringToDate(new Date(System.currentTimeMillis()).toString()));
                    dao_productBatch.updateSingleProductBatch(productBatchDTO);
                    weightConverter.writeInWeightDisplay("farvel");
                    Thread.sleep(3000);

                    //programmet slutter
                    System.exit(0);
                }

                //i++;

            }

        } catch (IOException e) {

        }catch(SQLException e){

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}






