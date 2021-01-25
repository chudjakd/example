package org.acme.repository;

import org.acme.model.Fruit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@ApplicationScoped
public class PreTestSkuska {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void insertFruitIntoDB(){
        Query query= entityManager.createNativeQuery("insert into fruit(id, name, season) values(13,'Jablcko','Picka')");
        query.executeUpdate();

        Query query2= entityManager.createNativeQuery("insert into fruit(id, name, season) values(14,'Ajeto','Pod')");
        query2.executeUpdate();
    }

    @Transactional
    public void insertCalcIntoDB(){
        Query query= entityManager.createNativeQuery("insert into calc(id, countofnumbers, number1, number2) values(22,5,2,3)");
        query.executeUpdate();

        Query query2= entityManager.createNativeQuery("insert into calc(id, countofnumbers, number1, number2) values(23,10,2,7)");
        query2.executeUpdate();
    }

    @Transactional
    public void insertDataIntoDB(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:/Users/David Chudjak/quarkus-projects/example/src/test/java/org/acme/skuskainsert.sql"));
            scanner.useDelimiter(";");

            while(scanner.hasNext()) {
                //System.out.println("SQL statement: " + scanner.next());
                Query query= entityManager.createNativeQuery(scanner.next());
                query.executeUpdate();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public void insertFruitsIntoDBWithJpa(){
        Fruit fruit= new Fruit();
        fruit.setId(536L);
        fruit.setName("Jablcko1");
        fruit.setSeason("Leticko1");

        Fruit fruit2= new Fruit();
        fruit2.setId(537L);
        fruit2.setName("Jablcko2");
        fruit2.setSeason("Leticko2");

        entityManager.persist(fruit);

        entityManager.persist(fruit2);
    }

    @Transactional
    public void deleteFruitsFromDBWithJpa(){
        Fruit fruit= entityManager.find(Fruit.class,536L);
        Fruit fruit2= entityManager.find(Fruit.class,537L);

        entityManager.remove(fruit);

        entityManager.remove(fruit2);
    }

    @Transactional
    public void deleteDataInDB(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:/Users/David Chudjak/quarkus-projects/example/src/test/java/org/acme/skuskadelete.sql"));
            scanner.useDelimiter(";");

            while(scanner.hasNext()) {
                //System.out.println("SQL statement: " + scanner.next());
                Query query= entityManager.createNativeQuery(scanner.next());
                query.executeUpdate();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
