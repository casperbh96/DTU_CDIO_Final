package main.java.Rest;


import main.java.Rest.DTO.RestDTO_DataType_1;

import java.util.List;

public interface I_JsonConverter {

    List<Object> httpGET_Data1(RestDTO_DataType_1 restDto);
void httpPost_Data1(RestDTO_DataType_1 restDto);


}
