package com.sample.rest.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonPlaceholderSvcQueryResponseDTO {

	private int userId;
	private int id;
	private String titleObj;

}
