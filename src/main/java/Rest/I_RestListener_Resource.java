package main.java.Rest;

import main.java.Core.ResourceDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_Resource {
    Response createResource(ResourceDTO res);
    Response getAllResources();
    Response getResourceById(int resId);
    Response getResourcesBySearch(String search);
    Response updateResource(ResourceDTO res);
    Response deleteResource(ResourceDTO res);
}
