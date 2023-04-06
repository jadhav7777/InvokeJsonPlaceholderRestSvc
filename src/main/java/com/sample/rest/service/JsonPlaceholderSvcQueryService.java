package com.sample.rest.service;

import com.sample.rest.model.dto.JsonPlaceholderSvcQueryDTO;
import com.sample.rest.model.dto.JsonPlaceholderSvcQueryResponseDTO;
import com.sample.rest.model.dto.JsonPlaceholderSvcQueryResponseWrapperDTO;
public interface JsonPlaceholderSvcQueryService {
	

	String sayHello(String appInsightQueryReq);
	JsonPlaceholderSvcQueryResponseWrapperDTO retrieveAlbumDetails(JsonPlaceholderSvcQueryDTO appInsightQueryReq);
}
