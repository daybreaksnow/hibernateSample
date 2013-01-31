package interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class CreatedDateSaveInterceptor extends EmptyInterceptor {
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		for (int cnt = 0; cnt < propertyNames.length; cnt++) {
			String propertyName = propertyNames[cnt];
			if (propertyName.equals("createdDate") && state[cnt] == null) {
				state[cnt] = new Date();
			}
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}
}
