package be.butskri.automail;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Klantnummer {

	private static final Pattern REGEXP = Pattern.compile("\\d{2}\\s*-\\s*\\d{1,5}");

	public static boolean isGeldig(String klantnummer) {
		return REGEXP.matcher(klantnummer).matches();
	}

	private String klantnummer;

	public Klantnummer(String klantnummer) {
		this.klantnummer = StringUtils.remove(klantnummer, ' ');
	}

	public String getFormatted() {
		return klantnummer;
	}

	public String getGebruikersnaam() {
		StringBuilder result = new StringBuilder();
		result.append("1");
		result.append(eersteGetal());
		result.append("-");
		result.append(StringUtils.leftPad(tweedeGetal(), 5, '0'));
		return result.toString();
	}

	private String eersteGetal() {
		return klantnummer.substring(0, minIndex());
	}

	private String tweedeGetal() {
		return klantnummer.substring(minIndex() + 1);
	}

	private int minIndex() {
		return klantnummer.indexOf('-');
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
