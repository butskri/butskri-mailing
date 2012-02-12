package be.butskri.automail;

import static junit.framework.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TemplateUtilTest {

	@Test
	public void vulParametersInSetWaardenVanDeParameters() {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("foo", "mijnFoeke");
		parameterMap.put("bar", "mijnBarreke");
		assertEquals("foo: mijnFoeke, bar: mijnBarreke", //
				TemplateUtil.vulParametersIn("foo: ${foo}, bar: ${bar}", parameterMap));
	}
}
