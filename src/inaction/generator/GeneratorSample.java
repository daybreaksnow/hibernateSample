package inaction.generator;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class GeneratorSample {
	public static void main(String[] args) {
		saveIdentity();
	}

	private static void saveIdentity() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		IdentityGenerator id = new IdentityGenerator();
		id.setValue("IDENTITY");
		session.save(id);

		SequenceGenerator seq = new SequenceGenerator();
		seq.setValue("SEQUENCE");
		session.save(seq);

		// HiloGenerator hilo = new HiloGenerator();
		// hilo.setValue("HILO");
		// session.save(hilo);

		UuidGenerator uuid = new UuidGenerator();
		uuid.setValue("UUID");
		session.save(uuid);

		tx.commit();
		session.close();
	}
}
