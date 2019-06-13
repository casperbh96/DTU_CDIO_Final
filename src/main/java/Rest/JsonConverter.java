package rest;

import rest.DTO.RestDTO_DataType_1;

public class JsonConverter implements I_JsonConverter{

    public RestDTO_DataType_1 httpGET_Data1(RestDTO_DataType_1 restDto){
        switch (restDto.getSystem()){
            case "web":
                I_JsonHandler jsonHandler = new JsonHandler_Web();
                jsonHandler
                break;
            case "weight":
                break;

        }
    }

    public void httpPost_Data1(RestDTO_DataType_1 restDto){

    }
}



