package kr.co.lunasoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.co.lunasoft.vo.ResponseInfo;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/gateway")
@Slf4j
public class GatewayController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/call-gateway")
	public Map<String, Object> callGateway() {
		String reqUrl = "http://eureka-client/client/get-map-data";

		ResponseInfo output = restTemplate.getForObject(reqUrl, ResponseInfo.class);
		log.info(""+output);

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", output);
		return obj;
	}

}
