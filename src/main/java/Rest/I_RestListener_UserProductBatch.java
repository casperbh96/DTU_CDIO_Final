package main.java.Rest;

import main.java.Core.REL_UserProductBatchDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_UserProductBatch {
    Response createRoleUser(REL_UserProductBatchDTO userProductBatch);
    Response getAllRoleUsers();
    Response getRoleUserByUserId(int userId);
    Response deleteRoleUser(REL_UserProductBatchDTO userProductBatch);
}
