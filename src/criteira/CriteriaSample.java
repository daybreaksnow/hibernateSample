package criteira;

import inaction.association.Bid;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import techscore.sample.DaoSupport;

public class CriteriaSample {

	public static void main(String[] args) {
		projectionSample();
		successSqlRestriction();
		successSqlRestrictionNest();
		// failSqlRestriction();
	}

	private static void failSqlRestriction() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Bid.class);
		criteria.createAlias("item", "aliasItem");
		criteria.add(Restrictions.sqlRestriction("{alias}.amount = 1"));
		// joinしたエイリアス名は変化してしまい、sqlRestrictionからでは取得できない！
		criteria.add(Restrictions.sqlRestriction("aliasItem.name = 'hoge'"));
		List bids = criteria.list();

		System.out.println(bids.size());

		tx.commit();
		session.close();
	}

	private static void successSqlRestriction() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Bid.class);
		criteria.add(Restrictions.sqlRestriction("{alias}.amount = 1"));
		Criteria subCriteria = criteria.createCriteria("item");
		subCriteria.add(Restrictions.sqlRestriction("{alias}.name = 'hoge'"));
		List bids = criteria.list();

		System.out.println(bids.size());

		tx.commit();
		session.close();
	}

	// bid→item→mapでcreateAliasとcreateCriteriaを両方使うサンプル
	private static void successSqlRestrictionNest() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Bid.class);
		criteria.createAlias("item", "aliasItem");
		Criteria Subcriteria = criteria
				.createCriteria("aliasItem.itemCategoryMappings");
		criteria.add(Restrictions.sqlRestriction("{alias}.amount = 2"));
		criteria.add(Restrictions.eq("aliasItem.name", "1"));
		Subcriteria.add(Restrictions.sqlRestriction("{alias}.mapping_id = 1"));
		List bids = criteria.list();

		System.out.println(bids.size());

		tx.commit();
		session.close();
	}

	private static void projectionSample() {
		listProjection(Projections.rowCount());
		listProjection(Projections.count("bidId"));
		listProjection(Projections.property("amount"));
		listProjection(Projections.groupProperty("amount"));
		listProjection(Projections.projectionList()
				.add(Projections.groupProperty("itemId"))
				.add(Projections.sum("amount")));
	}

	private static void listProjection(Projection projection) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		Criteria criteria = session.createCriteria(Bid.class);
		criteria.setProjection(projection);
		List resultList = criteria.list();
		for (Object result : resultList) {
			System.out.println(result);
			if (result instanceof Object[]) {
				for (Object obj : (Object[]) result) {
					System.out.println(obj);
				}
			}
		}

		session.close();
	}
}
