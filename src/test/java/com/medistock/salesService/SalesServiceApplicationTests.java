package com.medistock.salesService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesServiceApplicationTests {
	@Test
	void testApplication() {
		assertDoesNotThrow(() -> SalesServiceApplication.main(new String[]{}));
	}

}



