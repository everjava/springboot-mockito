package br.com.ever.springbootmockito;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * para sobreescrever o application.properties, criar nova pasta resources e arquivo test/resources/application.properties.
 * ou criar annotation abaixo com o classpath (test/resources)
  
  http://xunitpatterns.com
 *
 */

@SpringBootTest
//@TestPropertySource(locations= {"classpath:test-configuration.properties"})
class SpringbootMockitoApplicationTests {

	@Test
	void contextLoads() {
	}

}
