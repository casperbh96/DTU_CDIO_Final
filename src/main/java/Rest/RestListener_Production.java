package main.java.Rest;

import main.java.Core.RoleDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("production")
public class RestListener_Production {


        private JsonHandler_unconnected jsonHandler = new JsonHandler_unconnected();
        //JsonHandler jsonHandler = new JsonHandler();

        @Path("create")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<RoleDTO> createRole(@QueryParam("roleDTOs") String roleDTOs) {

            List<RoleDTO> roleList = JsonDTOassembler.assembleRestRoleDTO(roleDTOs);
            return jsonHandler.createRoles(roleList);


        }

        @Path("get")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<RoleDTO> getRole(@QueryParam("searchMethod") String searchMethod, @QueryParam("Id") String Id){

            return jsonHandler.getRoles( searchMethod , Id );
        }

        @Path("search")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<RoleDTO> searchRoles(@QueryParam("keyword") String keyWord) {
            return jsonHandler.searchRolesByKeyword(keyWord);
        }

        @Path("update")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<RoleDTO> updateRole(@QueryParam("roleDTOs") String roleDTOs) {
            List<RoleDTO> roleList = JsonDTOassembler.assembleRestRoleDTO(roleDTOs);
            return jsonHandler.updateRoles(roleList);
        }
        @Path("delete")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<RoleDTO> deleteRole(@QueryParam("roleDTOs") String roleDTOs) {

            List<RoleDTO> roleList = JsonDTOassembler.assembleRestRoleDTO(roleDTOs);
            return jsonHandler.deleteRoles(roleList);
        }

}
