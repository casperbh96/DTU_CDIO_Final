package main.java.Weight;

import java.io.BufferedReader;
import java.io.IOException;

public class WeightTester {

    public static void main(String[] args) throws IOException, InterruptedException {

        WeightConverter weightConverter = new WeightConverter();
        String result = weightConverter.getWeight();
        System.out.println(result);

        //System.out.println(weightConverter.weightTara());

        //weightConverter.writeLongTextToDisplay("Tryk OK hvis korrekt");
        //weightConverter.respondWithEnter();

        //weightConverter.backToWeightDisplay();

/*
        while (weightConverter.StatusForUserResponse() == false) {


            String send = weightConverter.writeInWeightDisplay("UserID");
            System.out.println(weightConverter.convertInputFromDisplayToString(send));
        }

*/
            //System.out.println(weightConverter.getWeight3());

            //System.out.println(weightConverter.writeInWeightDisplay("userID"));


        //}


    }
}
