package inaction.usertype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Currency;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * 6.1.2 金額をDBにUSD型で保存する。 これはアプリケーション上では複数の通貨を利用しているが、DBスキーマに通貨カラムがなく、
 * 常にUSDで保存、読み込みをするという背景のもとの実装である。
 * 
 */
public class MonetaryAmountUserType implements UserType {

	private static final int[] SQL_TYPES = { Types.NUMERIC };

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	// マッピングされるJAVA型
	@Override
	public Class returnedClass() {
		return MonetaryAmount.class;
	}

	// 自動ダーティチェック用
	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		BigDecimal valueInUSD = rs.getBigDecimal(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		return new MonetaryAmount(valueInUSD, Currency.getInstance("USD"));
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.NUMERIC);
		} else {
			MonetaryAmount anyCurrency = (MonetaryAmount) value;
			MonetaryAmount amountInUSD = MonetaryAmount.convert(anyCurrency,
					Currency.getInstance("USD"));
			st.setBigDecimal(index, amountInUSD.getValue());
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// immutableなのでそのまま返す。そうでなければnewする
		return value;
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

}
