package projeto.dao;

import java.math.BigInteger;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import projeto.modelo.Cliente;

public class DAO<T> extends java.lang.Object {

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}
        
        
	public boolean adiciona(T t) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

                try {		
                    
                    // persiste o objeto
                    em.persist(t);

                    // commita a transacao
		    em.getTransaction().commit();


                } catch (Exception e) {
		    em.getTransaction().rollback();

                    FacesContext.getCurrentInstance().addMessage("login",
					new FacesMessage(e.getMessage()));
                	// fecha a entity manager
         		em.close();
        		return false;
                }


		// fecha a entity manager
		em.close();
                return true;
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
                try {	
		em.remove(em.merge(t));
		em.getTransaction().commit();
                
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage("login",
					new FacesMessage(e.getMessage()));
                }

		em.close();
	}

	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
                try {	

		em.merge(t);

		em.getTransaction().commit();
                } catch (Exception e) {
		    em.getTransaction().rollback();
                }    
                em.close();

        }

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

	public T buscaPorId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}

	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from login n")
				.getSingleResult();
		em.close();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}

}
