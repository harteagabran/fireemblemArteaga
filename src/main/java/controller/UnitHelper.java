package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Unit;

/**
 * @author Halmar Arteaga - harteagabran
 * CIS175 - Spring 2024
 * Jan 28, 2024
 */
public class UnitHelper {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("fireemblem");

	/**
	 * @param toAdd
	 */
	public void insertUnit(Unit toAdd) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Unit> showAllUnits() {
		EntityManager em = emfactory.createEntityManager();
		List<Unit> allItems = em.createQuery("SELECT i FROM Unit i").getResultList();
		return allItems;
	}

	/**
	 * @param toDelete
	 */
	public void deleteUnit(Unit toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Unit> typedQuery = em.createQuery("select u from Unit u where u.name = :selectedName and u.type = :selectedClass", Unit.class);
		
		//Substitute with actual data
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedClass", toDelete.getType());
		
		//only want one result
		typedQuery.setMaxResults(1);
		
		//get resutl save in list item
		Unit result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit
	 * @return
	 */
	public Unit searchForUnitById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Unit found = em.find(Unit.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updateUnit(Unit toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param itemName
	 * @return
	 */
	public List<Unit> searchForUnitByName(String name) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Unit> typedQuery = em.createQuery("select u from Unit u where u.name = :selectedName", Unit.class);
		typedQuery.setParameter("selectedName", name);
		
		List<Unit> foundUnits = typedQuery.getResultList();
		em.close();
		return foundUnits;
	}

	/**
	 * @param storeName
	 * @return
	 */
	public List<Unit> searchForUnitByClass(String className) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Unit> typedQuery = em.createQuery("select u from Unit u where u.type = :selectedClass", Unit.class);
		typedQuery.setParameter("selectedStore", className);
		
		List<Unit> foundUnits = typedQuery.getResultList();
		em.close();
		
		return foundUnits;

	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
