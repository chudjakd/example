package org.acme.repository;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.model.Fruit;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FruitRepositoryTest {

    @Inject
    FruitRepository fruitRepository;

    @Inject
    PreTestSkuska preTestSkuska;


    @BeforeAll
    void insertDataIntoDB(){
        //preTestSkuska.insertCalcIntoDB();
        //preTestSkuska.insertDataIntoDB();
        preTestSkuska.insertFruitsIntoDBWithJpa();
    }

    @AfterAll
    void deleteDataInDB(){
        //preTestSkuska.insertCalcIntoDB();
        //preTestSkuska.deleteDataInDB();
        preTestSkuska.deleteFruitsFromDBWithJpa();
    }
    @Test
    public void testGetAllFruitsFromDB(){
        List<Fruit> allFruitsFromDB=fruitRepository.getAll();

        Assertions.assertEquals(2,allFruitsFromDB.size());
    }

    @Test
    public void testGetFruitByName(){
        Fruit fruit= fruitRepository.getFruitByName("Jablcko1");
        Assertions.assertEquals("Leticko1",fruit.getSeason());
    }
}
