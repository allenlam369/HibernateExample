package epd;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main2 {
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("epd");

	public static void main(String[] args) {
		long id = 2L;
		String name = "aaa";
		String creator = "bbb";
		create(id, name, creator, new Date());

		System.err.println("created User with id " + id);

		List<User> uList = readAll();
		for (User u : uList) {
			System.err.println("id=" + u.getId() + ", name=" + u.getUsername());
		}
	}

	// CRUD
	private static void create(long id, String name, String creator, Date date) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			User user = new User();
			user.setId(id);
			user.setUsername(name);
			user.setCreatedBy(creator);
			user.setCreatedDate(date);

		} catch (Exception ex) {
			if (trans != null) {
				trans.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static List<User> readAll() {
		List<User> uList = null;
		EntityManager em = EMF.createEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			// HQL query
			uList = em.createQuery("from User", User.class).getResultList();
			trans.commit();
		} catch (Exception ex) {
			if (trans != null) {
				trans.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
		return uList;
	}

	public static void delete(int id) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			User user = em.find(User.class, id);
			em.remove(user);
			trans.commit();
		} catch (Exception ex) {
			if (trans != null) {
				trans.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(int id, String name, String creator, Date d) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			User u = em.find(User.class, id);
			u.setUsername(name);

		} catch (Exception ex) {
			if (trans != null) {
				trans.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

}
