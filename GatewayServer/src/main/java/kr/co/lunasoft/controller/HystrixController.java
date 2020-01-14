package kr.co.lunasoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import kr.co.lunasoft.vo.ResponseInfo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hystrix")
@Slf4j
public class HystrixController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/test")
	@HystrixCommand(fallbackMethod = "fallback", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			   @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
			   @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			   @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			   @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")})
	public Map<String, Object> test() {
		String reqUrl = "http://eureka-client/client/long-time";

		ResponseInfo output = restTemplate.getForObject(reqUrl, ResponseInfo.class);
		log.info(""+output);

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", output);
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
