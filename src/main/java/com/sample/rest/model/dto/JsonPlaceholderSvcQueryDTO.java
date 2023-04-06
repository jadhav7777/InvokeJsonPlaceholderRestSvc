
package com.sample.rest.model.dto;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonPlaceholderSvcQueryDTO {
	public String queryKey;
}
