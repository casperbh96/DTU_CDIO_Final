package main.java.DataAccess.test;

import main.java.Core.ProductBatchDTO;
import main.java.DataAccess.dao.DAO_ProductBatch;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_ProductBatchTest {
    DAO_ProductBatch dao = new DAO_ProductBatch();

    // region CREATE

    // endregion

    // region READ
    @org.junit.Test
    public void readAllProductBatchesTest() {
        try {
            List<ProductBatchDTO> pbDTO = dao.readAllProductBatchs();
            assertNotNull(pbDTO);
            assertNotEquals(pbDTO.isEmpty(), pbDTO);

            for (ProductBatchDTO i : pbDTO) {
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
