package epd;

import java.util.Date;

import org.hibernate.Session;

public class Main {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User user = new User();
		user.setId(0L);
//		user.setId(user.getNextId());
		user.setUsername("JJ");
		user.setCreatedBy("Google");
		user.setCreatedDate(new Date());
		session.save(user);

		User user1 = new User();
		user1.setId(1L);
		user1.setUsername("KK");
		user1.setCreatedBy("Yahoo");
		user1.setCreatedDate(new Date());
		session.save(user1);

		session.getTransaction().commit();
	}
}
