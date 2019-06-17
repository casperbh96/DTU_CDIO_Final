package main.java.DataAccess.test;

import main.java.Core.REL_ProductBatchResourceBatchDTO;
import main.java.DataAccess.dao.DAO_REL_ProductBatchResourceBatch;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_ProductBatchResourceBatchTest {
    DAO_REL_ProductBatchResourceBatch dao = new DAO_REL_ProductBatchResourceBatch();
    // region CREATE

    // endregion

    // region READ
    @org.junit.Test
    public void readAllProductBatchesTest() {
        try {
            List<REL_ProductBatchResourceBatchDTO> pbrbDTO = dao.readAllProductBatchResourceBatchs();
            assertNotNull(pbrbDTO);
            assertNotEquals(pbrbDTO.isEmpty(), pbrbDTO);

            for (REL_ProductBatchResourceBatchDTO i : pbrbDTO) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // endregion

    // region UPDATE

    // endregion

    // region DELETE

    // endregion

}
