package alPay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import alPay.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestConfiguration {

	@Test
	public void testJu() {
		try {
			String response = Util.httpPost("http://localhost:8080/v1.0/alipay", "");
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
