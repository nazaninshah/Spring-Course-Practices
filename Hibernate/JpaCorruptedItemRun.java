package ir.freeland.springboot.practice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.EntityManager;

public class JpaCorruptedItemRun {
	private EntityManager entityManager;

	public void CorruptedItemRun() {
		entityManager = entityManager.getEntityManagerFactory().createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = session.getTransaction();
		
		//find corrupted item from items
		transaction.begin();
		List<Item> allItems = session.createQuery("from Item i where i.category like '%category2%'", Item.class).getResultList();
		allItems.stream().forEach(i -> System.out.println(i));
		transaction.commit();
		
		// save
		transaction.begin();
		corruptedItem cItem1 = new corruptedItem();
		cItem1.setItem(allItems.get(0));
		cItem1.setReason("Expired");
		session.persist(cItem1);
		transaction.commit();

		// Find
		transaction.begin();
		List<corruptedItem> allCorruptedItems = session.createQuery("from corruptdItems", corruptedItem.class).getResultList();
		allCorruptedItems.stream().forEach(c -> System.out.println(c));
		transaction.commit();

		// Update
		transaction.begin();
		allCorruptedItems.get(0).setReason("Bad product");
		transaction.commit();

		// Delete
		transaction.begin();
		allCorruptedItems.remove( allItems.get(0) );
		transaction.commit();
	}
}
