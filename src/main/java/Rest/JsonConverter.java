package main.java.Rest;

import main.java.Rest.DTO.RestDTO_DataType_1;

public class JsonConverter {

    public <Any> Any httpGET_Data1(RestDTO_DataType_1 restDto){
        System.out.println(restDto.toString());

        switch (restDto.getSystem()){
            case "web":
                JsonHandler_Web jsonHandler = new JsonHandler_Web();
                return jsonHandler.handleJson(restDto);
            case "weight":
                return null;
            default:
                return null;
        }
    }

    public void httpPost_Data1(RestDTO_DataType_1 restDto){

    }
}



