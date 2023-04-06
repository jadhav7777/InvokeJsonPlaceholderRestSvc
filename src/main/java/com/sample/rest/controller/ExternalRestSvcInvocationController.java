package com.sample.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.rest.model.dto.*;
import com.sample.rest.service.JsonPlaceholderSvcQueryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
/// Due to RequestMapping the service will be exposed as http://localhost:8080/queryService
@RequestMapping(path = "queryService")
public class ExternalRestSvcInvocationController {
	private final JsonPlaceholderSvcQueryService jsonPlaceholderQueryService;

	@GetMapping(value = "/sayHello")
	public String sayHello()
	{
		String response = 
				jsonPlaceholderQueryService.sayHello("WW1");
		return response;
	}
	//Since this is POST method and due to ReqMapping and method relative path
	// the service is accessible at http://localhost:8080/queryService/queryLog
	@PostMapping(value = "/queryLog")
	public JsonPlaceholderSvcQueryResponseWrapperDTO getLogInfo(@RequestBody JsonPlaceholderSvcQueryDTO appInsightQueryReq)
	{
		JsonPlaceholderSvcQueryResponseWrapperDTO response = 
				jsonPlaceholderQueryService.retrieveAlbumDetails(appInsightQueryReq);
		return response;

	}
}
