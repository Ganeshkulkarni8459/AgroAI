package org.agriopredict.controller;

import org.agriopredict.dto.request.AgronomistRequest;
import org.agriopredict.dto.request.UserNoteRequest;
import org.agriopredict.dto.response.AgronomistResponse;
import org.agriopredict.service.AgronomistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgronomistController {

	@Autowired
	private AgronomistService agronomistService;

	@PostMapping("/agronomist/api/v1/add")
	public ResponseEntity<AgronomistResponse> addOrUpdateAgronomist(@RequestBody AgronomistRequest request) {
		AgronomistResponse response = agronomistService.addOrUpdateAgronomist(request);
		return ResponseEntity.status(getHttpStatus(response.getResponseCode())).body(response);
	}

	@GetMapping("/agronomist/api/v1/getByAddress/{address}")
	public ResponseEntity<AgronomistResponse> getAgronomistByAddress(@PathVariable String address) {
		AgronomistResponse response = agronomistService.getAgronomistByAddress(address);
		return ResponseEntity.status(getHttpStatus(response.getResponseCode())).body(response);
	}

	private int getHttpStatus(String responseCode) {
		switch (responseCode) {
		case "200":
			return 200; // OK
		case "201":
			return 201; // Created
		case "400":
			return 400; // Bad Request
		case "404":
			return 404; // Not Found
		default:
			return 500; // Internal Server Error
		}
	}

}
