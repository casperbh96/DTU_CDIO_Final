package rest;

import rest.DTO.RestDTO_DataType_1;
import rest.I_JsonConverter;
import rest.I_JsonHandler;
import rest.JsonHandler_Web;

public class JsonConverter implements I_JsonConverter {

    public RestDTO_DataType_1 httpGET_Data1(RestDTO_DataType_1 restDto){
        switch (restDto.getSystem()){
            case "web":
                I_JsonHandler jsonHandler = new JsonHandler_Web();
                jsonHandler.handleJson(restDto);
            case "weight":
                return null;
            default:
                return null;
        }
    }

    public void httpPost_Data1(RestDTO_DataType_1 restDto){

    }
}



