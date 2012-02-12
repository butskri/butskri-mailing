package be.butskri.automail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailSender {

	private static final File BASE_DIR = new File("C:/projects/Home/automail/automail");
	// private static final File COMMUNICATIE_FILE = new File(BASE_DIR,
	// "communicatie.txt");
	private static final File COMMUNICATIE_FILE = new File(BASE_DIR, "communicatie2.txt");
	private static final File MAIL_TEMPLATE_FILE = new File(BASE_DIR, "mailTemplate.txt");

	private static final String MAIL_LK_HERENTALS = "lkherentals@landelijkekinderopvang.be";
	private static final String LK_HERENTALS = "LK Herentals";
	private static final String SUBJECT = "klantnummer en gebruikersnaam - TE BEWAREN !!!";

	public static void main(String[] args) throws EmailException {
		Set<Gezin> gezinnen = getUniekeGezinnenMetMail();
		System.out.println("Aantal gevonden gezinnen: " + gezinnen.size());
		for (Gezin gezin : gezinnen) {
			sendMail(gezin);
		}
	}

	private static void sendMail(Gezin gezin) throws EmailException {
		System.out.println("sending mail to: " + gezin.toString());
		// System.out.println("message:" + buildMessage(gezin));
		buildEmail(gezin).send();
	}

	private static Email buildEmail(Gezin gezin) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.pandora.be");
		email.setSmtpPort(25);
		email.setFrom(MAIL_LK_HERENTALS, LK_HERENTALS);
		// email.addCc(MAIL_LK_HERENTALS, LK_HERENTALS);
		// email.addTo(gezin.getEmail());
		email.addTo("kristof.buts@gmail.com");
		email.setSubject(SUBJECT);
		email.setMsg(buildMessage(gezin));
		return email;
	}

	static String buildMessage(Gezin gezin) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("klantnummer", gezin.getKlantnummer());
		parameterMap.put("gebruikersnaam", gezin.getGebruikersnaam());
		return TemplateUtil.vulParametersIn(getMailTemplate(), parameterMap);
	}

	private static String getMailTemplate() {
		try {
			return FileUtils.readFileToString(MAIL_TEMPLATE_FILE);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public static Set<Gezin> getUniekeGezinnenMetMail() {
		Set<Gezin> result = new HashSet<Gezin>();
		for (Gezin gezin : getGezinnen()) {
			if (gezin.heeftEmail()) {
				result.add(gezin);
			}
		}
		return result;
	}

	public static List<Gezin> getGezinnen() {
		GezinParser gezinParser = new GezinParser(COMMUNICATIE_FILE);
		List<Gezin> result = new ArrayList<Gezin>();
		Gezin gezin = gezinParser.nextGezin();
		while (gezin != null) {
			result.add(gezin);
			gezin = gezinParser.nextGezin();
		}
		return result;
	}

}
