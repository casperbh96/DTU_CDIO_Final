package main.java.Rest;

import main.java.Core.UserDTO;

import javax.ws.rs.QueryParam;

public interface I_RestListener_Roles {

    // -- ROLE -- ROLE -- ROLE -- ROLE --
    public UserDTO createRole(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    public UserDTO getRole(@QueryParam("searchMethod") String searchMethod, @QueryParam("Id") String Id);
    public UserDTO searchRoles(@QueryParam("searchMethod") String searchMethod, @QueryParam("KeyWord") String keyWord);
    public UserDTO updateRole(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    public UserDTO deleteRole(@QueryParam("userDTO") String userDTO);

}
