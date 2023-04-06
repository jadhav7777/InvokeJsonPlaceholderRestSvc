package com.sample.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sample.rest.model.dto.JsonPlaceholderSvcQueryDTO;
import com.sample.rest.model.dto.JsonPlaceholderSvcQueryResponseDTO;
import com.sample.rest.model.dto.JsonPlaceholderSvcQueryResponseWrapperDTO;
import com.sample.rest.service.JsonPlaceholderSvcQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JsonPlaceholderSvcQueryServiceImpl implements JsonPlaceholderSvcQueryService {
	@Value("${demo.value}")
	private String idVal;
	@Value("${demo.targetHost}")
	private String targetHost;
	@Value("${demo.targetMethodRelativePath}")
	private String targetMethodRelativePath;
	public String sayHello(String indvLogDetails) {
		return "Yuppy  " + indvLogDetails;
	}

	public JsonPlaceholderSvcQueryResponseWrapperDTO retrieveAlbumDetails(JsonPlaceholderSvcQueryDTO appInsightQueryReq)
		 {
		System.out.println("### retreive JSONPlaceholder Query method: Start ###");

		JsonPlaceholderSvcQueryResponseWrapperDTO tokenResponse = new JsonPlaceholderSvcQueryResponseWrapperDTO();
		try {
			//headers.add("Authorization","Bearer "+oauthToken);
			System.out.println("@@@@"+idVal);
			idVal=appInsightQueryReq.queryKey;
			System.out.println(idVal+"targetHost"+targetHost);
			//String tokenUrl="https://jsonplaceholder.typicode.com/albums?id=4";
			String tokenUrl="https://{targetHost}/{targetMethodRelativePath}?id={id}";
			System.out.println("tokenUrl"+tokenUrl);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("accept","application/json");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			Map<String, String> params = new HashMap<String, String>();
			// query is passed as an input argument
			params.put("targetHost", targetHost);
			params.put("targetMethodRelativePath", targetMethodRelativePath);
			params.put("id", idVal);
			/// @@@ REST call to app Insight API to retreive details @@@///
			ResponseEntity<ArrayList> response = restTemplate.exchange(tokenUrl, 
					HttpMethod.GET, entity, ArrayList.class,
					params);
			System.out.println("### Response status code:" + response.getStatusCodeValue()+ " ###");
			System.out.println("### Resp"+response.getBody().toString());
			ArrayList respData = (ArrayList) response.getBody();
			List<JsonPlaceholderSvcQueryResponseDTO> allAlbumDetails = new ArrayList<JsonPlaceholderSvcQueryResponseDTO>();
			JsonPlaceholderSvcQueryResponseDTO indvAlbumDetails = new JsonPlaceholderSvcQueryResponseDTO();
			for (int clmnCntr = 0; clmnCntr < respData.size(); clmnCntr++)
			{
				HashMap indvColName = (HashMap) respData.get(clmnCntr);
				indvColName.forEach((key, value) -> System.out.println(key + " " + value));
				indvAlbumDetails.setId((int)indvColName.get("id"));
				indvAlbumDetails.setUserId((int)indvColName.get("userId"));
				indvAlbumDetails.setTitleObj((String)indvColName.get("title"));
				allAlbumDetails.add(indvAlbumDetails);
				indvAlbumDetails = new JsonPlaceholderSvcQueryResponseDTO();
			}
			
			tokenResponse.setTableOfAlbums(allAlbumDetails);
		} catch (Exception e) {
			System.out.println("### Exception occured while calling retrieveAlbumDetails():" + e.toString()+ " ###");

		}
		return tokenResponse;
	}
}
