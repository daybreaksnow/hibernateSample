package criteira;

import inaction.association.Item;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import techscore.sample.DaoSupport;

public class ResultTransformerSample {

	public static void main(String[] args) {
		transformerSample();
	}

	private static void transformerSample() {
		list(CriteriaSpecification.PROJECTION);
		list(CriteriaSpecification.ROOT_ENTITY);
		list(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
	}

	private static void list(ResultTransformer resultTransformer) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Criteria criteria = session.createCriteria(Item.class);
		criteria.add(Restrictions.eq("itemId", 2L));
		criteria.createAlias("bids", "bidAlias");
		criteria.setResultTransformer(resultTransformer);

		List resultList = criteria.list();
		System.out.println("size:" + resultList.size());
		for (Object result : resultList) {
			System.out.print(result + " ");
			if (result instanceof Object[]) {
				for (Object obj : (Object[]) result) {
					System.out.print(obj + " ");
				}
			}
		}
		System.out.println();
		session.close();
	}
}
