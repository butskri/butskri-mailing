package be.butskri.automail;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

public class GezinTest {

	@Test
	public void createMaaktGezinIndienEersteRegelTelefoonnummers() {
		Gezin gezin = Gezin.create(Arrays.asList("Telefoonnummer(s)", //
				"60 - 116", //
				"014/21 53 80 - Thuis", //
				"0477/56 45 22 - mama", //
				"014/21 85 95 - Dr Yves Wouters", //
				"0472/44 70 86 - Moeke", //
				"veroniquefonrijn@skynet.be"));

		assertEquals("60-116", gezin.getKlantnummer());
		assertEquals("160-00116", gezin.getGebruikersnaam());
		assertEquals("veroniquefonrijn@skynet.be", gezin.getEmail());
	}

	@Test
	public void createMaaktGezinIndienEersteRegelKlantnummerMetEmailAdres() {
		Gezin gezin = Gezin.create(Arrays.asList("60 - 63", //
				"0497/46 25 77 - mama", //
				"j.n-druyts@hotmail.com", //
				"0498/11 40 45 - papa"));

		assertEquals("60-63", gezin.getKlantnummer());
		assertEquals("160-00063", gezin.getGebruikersnaam());
		assertEquals("j.n-druyts@hotmail.com", gezin.getEmail());
	}

	@Test
	public void createMaaktGezinZonderEmailAdres() {
		Gezin gezin = Gezin.create(Arrays.asList("60 - 51", //
				"0477/906 543 - thuis en mama", //
				"0494/ 72 70 41 - werk papa", //
				"014/26 48 00 - Dr sprengers Frans (Westerlo)"));

		assertEquals("60-51", gezin.getKlantnummer());
		assertEquals("160-00051", gezin.getGebruikersnaam());
		assertNull(gezin.getEmail());
	}

	@Test
	public void createGeeftNullTerugIndienGeenKlantnummer() {
		assertNull(Gezin.create(Arrays.asList("")));
	}
}
