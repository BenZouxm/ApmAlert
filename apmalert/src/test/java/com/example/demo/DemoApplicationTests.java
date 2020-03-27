package com.example.demo;

import com.oneapm.alter.service.ApmAlertService;
import com.oneapm.alter.service.impl.ApmAlertServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApmAlertServiceImpl.class)
public class DemoApplicationTests {
	@Autowired
	private ApmAlertServiceImpl apmAlertServiceImpl;

	@Test
	public void contextLoads() {
		String testStr = apmAlertServiceImpl.port;
		System.out.print(testStr);

	}

}
