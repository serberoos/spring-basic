package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {


	@Test
	void contextLoads() {
	}

}    private final LogDemoService logDemoService;
	private final MyLogger myLogger;

	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request){
		String requestURL = request.getRequestURL().toString();
		myLogger.setRequestURL(requestURL);

		myLogger.log("controller test");
		logDemoService.logic("testId");
		return "OK";
	}

