package rest;

import rest.DTO.RestDTO_DataType_1;

public class JsonHandler_Web implements I_JsonHandler{

    public RestDTO_DataType_1 handleJson(RestDTO_DataType_1 restDto){
        switch(restDto.getAction()){
            case "create":
                break;
            case "read":
                break;
            case "update":
                break;
            case "delete":
                break;
        }
    }

    private RestDTO_DataType_1 OptionCreate(RestDTO_DataType_1 restDto){

        switch (restDto.getValue_1()){
            case "users":
                break;
            case "recipes":
                break;
            case "product_batches":
                break;
            case "roles":
                break;
            case "resources":
                break;
            case "resource_batches":
                break;
        }
    }

}
