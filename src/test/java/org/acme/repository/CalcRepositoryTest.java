package org.acme.repository;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.model.Calc;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalcRepositoryTest {

    @Inject
    PreTestSkuska preTestSkuska;

    @Inject
    CalcRepositoryWihEMF calcRepositoryWihEMF;

    @BeforeAll
    void insertDataIntoDB(){
        //preTestSkuska.insertCalcIntoDB();
        preTestSkuska.insertDataIntoDB();
    }

    @AfterAll
    void deleteDataFromDB(){
        preTestSkuska.deleteDataInDB();
    }

    @Test
    public void testGetAllCalcFromDB(){
        List<Calc> allCalcFromDB=calcRepositoryWihEMF.getAllCalcFromDB();

        Assertions.assertEquals(2,allCalcFromDB.size());
    }

    @Test
    public void testGetCalcByName(){
        Calc calc= calcRepositoryWihEMF.getCalcByCount(13);

        Assertions.assertEquals(8,calc.getNumber2());
    }

}