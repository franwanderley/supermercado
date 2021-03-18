package projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("supermecado");

	public  EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static void close(EntityManager em) {
		em.close();
	}
}
