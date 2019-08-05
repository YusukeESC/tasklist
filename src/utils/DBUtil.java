package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final String UNIT_NAME = "tasklist";
    private static EntityManagerFactory emf;

    public static EntityManager createEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        }
        return emf;
    }
}
