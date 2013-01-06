package inaction.usertype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 * 6.1.2 immutableクラス
 * 
 */
public class MonetaryAmount implements Serializable {
	private final BigDecimal value;
	private final Currency currency;

	public MonetaryAmount(BigDecimal value, Currency currency) {
		super();
		this.value = value;
		this.currency = currency;
	}

	public BigDecimal getValue() {
		return value;
	};

	public Currency getCurrency() {
		return currency;
	}

	public static MonetaryAmount convert(MonetaryAmount anyCurrency,
			Currency currency) {
		if (anyCurrency.getCurrency().equals(currency)) {
			return anyCurrency;
		}
		// JPY→USDのみサポート 1/80に。
		final BigDecimal rate = BigDecimal.valueOf(80);
		if (anyCurrency.getCurrency().equals(Currency.getInstance("JPY"))
				&& currency.getCurrencyCode().equals("USD")) {
			return new MonetaryAmount(anyCurrency.getValue().divide(rate, 0,
					RoundingMode.DOWN), currency);
		}
		return null;
	}
}
