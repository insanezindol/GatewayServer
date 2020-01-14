package kr.co.lunasoft.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hystrix")
@Slf4j
public class HystrixController {

	@GetMapping(value = "/test")
	@HystrixCommand(fallbackMethod = "fallback", commandProperties = { @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public Map<String, Object> test() {

		int rand = new Random().nextInt(5) + 2;
		try {
			Thread.sleep(rand * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("sec : " + rand);

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", "");
		return obj;
	}

	private Map<String, Object> fallback() {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100103");
		obj.put("msg", "error");
		obj.put("data", "");
		return obj;
	}

}
