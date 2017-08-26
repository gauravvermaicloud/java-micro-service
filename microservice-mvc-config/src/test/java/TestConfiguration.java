import static org.junit.Assert.*;

import org.junit.Test;

import com.microservice.mvc.microservice_mvc.controller.ConfigurationController;

public class TestConfiguration {

	@Test
	public void test() {
		ConfigurationController cc = new ConfigurationController();
		cc.get();
	}

}
