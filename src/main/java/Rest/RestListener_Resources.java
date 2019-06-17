package main.java.Rest;

import main.java.Core.ResourceDTO;
import main.java.Core.RoleDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("resource")
public class RestListener_Resources {


        private JsonHandler_unconnected jsonHandler = new JsonHandler_unconnected();
        //JsonHandler jsonHandler = new JsonHandler();

        @Path("create")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<ResourceDTO> createResource(@QueryParam("ResourceDTO") String resourceDTOs) {

            ResourceDTO resource = JsonDTOassembler.assembleRestResourceDTO(resourceDTOs);
            List<ResourceDTO> resourceList = new LinkedList<>();
            resourceList.add(resource);

            return jsonHandler.createResource(resourceList);
        }

        @Path("get")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<ResourceDTO> getResource(@QueryParam("searchMethod") String searchMethod, @QueryParam("Id") String Id){
            return jsonHandler.getResource(searchMethod, Id);
        }

        @Path("search")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<ResourceDTO> searchResource(@QueryParam("searchMethod") String searchMethod,@QueryParam("keyword") String keyWord) {

            return jsonHandler.searchResource( searchMethod , keyWord );

        }

        @Path("update")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<ResourceDTO> updateResource(@QueryParam("ResourceDTO") String ResourceDTOs) {

            ResourceDTO resource = JsonDTOassembler.assembleRestResourceDTO(ResourceDTOs);
            List<ResourceDTO> resourceList = new LinkedList<>();
            resourceList.add(resource);

            return jsonHandler.updateResource(resourceList);

        }

        @Path("delete")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<ResourceDTO> deleteRole(@QueryParam("ResourceDTO") String ResourceDTOs) {

            ResourceDTO resource = JsonDTOassembler.assembleRestResourceDTO(ResourceDTOs);
            List<ResourceDTO> resourceList = new LinkedList<>();
            resourceList.add(resource);

            return jsonHandler.deleteResource(resourceList);

        }

        @Path("reorders")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<ResourceDTO> getReOrderResources() {

            return jsonHandler.ReOrdersResources();

        }


}



