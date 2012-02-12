package be.butskri.automail;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Gezin {

	private String email;
	private Klantnummer klantnummer;

	public static Gezin create(List<String> lines) {
		String klantnummer = getKlantnummer(lines);
		if (klantnummer == null) {
			return null;
		}
		return new Gezin(klantnummer, getEmail(lines));
	}

	private static String getKlantnummer(List<String> lines) {
		for (String line : lines) {
			if (Klantnummer.isGeldig(line)) {
				return line.trim();
			}
		}
		return null;
	}

	private static String getEmail(List<String> lines) {
		for (String line : lines) {
			if (isEmailAdres(line)) {
				return line.trim();
			}
		}
		return null;
	}

	private static boolean isEmailAdres(String line) {
		return line.contains("@");
	}

	public Gezin(String klantnummer, String email) {
		this.klantnummer = new Klantnummer(klantnummer);
		this.email = email;
	}

	public String getKlantnummer() {
		return klantnummer.getFormatted();
	}

	public String getGebruikersnaam() {
		return klantnummer.getGebruikersnaam();
	}

	public String getEmail() {
		return email;
	}

	public boolean heeftEmail() {
		return email != null;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getGebruikersnaam());
		result.append(" ");
		result.append(StringUtils.leftPad(getKlantnummer(), 10, ' '));
		result.append(" ");
		result.append(getEmail());
		return result.toString();
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
