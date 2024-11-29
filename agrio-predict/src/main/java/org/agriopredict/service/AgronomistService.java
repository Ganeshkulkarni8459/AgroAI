package org.agriopredict.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.agriopredict.dto.request.AgronomistRequest;
import org.agriopredict.dto.response.AgronomistDetails;
import org.agriopredict.dto.response.AgronomistResponse;
import org.agriopredict.entity.Agronomist;
import org.agriopredict.repo.AgronomistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgronomistService {

	@Autowired
	private AgronomistRepository agronomistRepository;

	@Autowired
	private AgronomistResponse agronomistResponse;

	public AgronomistResponse addOrUpdateAgronomist(AgronomistRequest request) {
		AgronomistResponse agronomistResponse = new AgronomistResponse();

		// Validate input data
		if (request.getAgronomistName() == null || request.getMobileNo() == null || request.getAddress() == null) {
			agronomistResponse.setMessage("Invalid input data");
			agronomistResponse.setResponseCode("400");
			return agronomistResponse;
		}

		Optional<Agronomist> existingAgronomist = agronomistRepository.findByMobileNo(request.getMobileNo());

		Agronomist agronomist;
		if (existingAgronomist.isPresent()) {
			// Update existing agronomist
			agronomist = existingAgronomist.get();
			agronomist.setAgronomistName(request.getAgronomistName());
			agronomist.setAddress(request.getAddress());
			agronomist.setMobileNo(request.getMobileNo());
			agronomist.setCreatedDate(LocalDate.now());
			agronomistResponse.setMessage("Agronomist updated successfully");
			agronomistResponse.setResponseCode("200");
		} else {
			// Create new agronomist
			agronomist = new Agronomist();
			agronomist.setAgronomistName(request.getAgronomistName());
			agronomist.setAddress(request.getAddress());
			agronomist.setMobileNo(request.getMobileNo());
			agronomist.setCreatedDate(LocalDate.now());
			agronomistResponse.setMessage("Agronomist created successfully");
			agronomistResponse.setResponseCode("200");
		}

		// Save the agronomist
		agronomistRepository.save(agronomist);

		// Create a list to hold the agronomist details (since we're aiming to return a
		// list format)
		List<AgronomistDetails> agronomistDetailsList = new ArrayList<>();

		AgronomistDetails agronomistDetails = new AgronomistDetails();
		agronomistDetails.setAgronomistId(agronomist.getAgronomistId());
		agronomistDetails.setAgronomistName(agronomist.getAgronomistName());
		agronomistDetails.setMobileNo(agronomist.getMobileNo());
		agronomistDetails.setAddress(agronomist.getAddress());
		agronomistDetails.setCreatedDate(agronomist.getCreatedDate());

		agronomistDetailsList.add(agronomistDetails);

		// Set the agronomist details list in the response
		agronomistResponse.setAgronomists(agronomistDetailsList);

		return agronomistResponse;
	}

	public AgronomistResponse getAgronomistByAddress(String address) {
		AgronomistResponse agronomistResponse = new AgronomistResponse();

		if (address == null) {
			agronomistResponse.setMessage("Invalid address");
			agronomistResponse.setResponseCode("400");
			return agronomistResponse;
		}

		List<Agronomist> agronomists = agronomistRepository.findByAddress(address);

		if (agronomists.isEmpty()) {
			agronomistResponse.setMessage("Agronomist not Found");
			agronomistResponse.setResponseCode("404");
		} else {
			// If multiple agronomists need to be returned, create a new class that holds a
			// list of agronomist details
			List<AgronomistDetails> agronomistDetailsList = new ArrayList<>();

			for (Agronomist agronomist : agronomists) {
				AgronomistDetails agronomistDetails = new AgronomistDetails();
				agronomistDetails.setMobileNo(agronomist.getMobileNo());
				agronomistDetails.setAgronomistName(agronomist.getAgronomistName());
				agronomistDetails.setAddress(agronomist.getAddress());
				agronomistDetails.setAgronomistId(agronomist.getAgronomistId());
				agronomistDetails.setCreatedDate(agronomist.getCreatedDate());

				agronomistDetailsList.add(agronomistDetails);
			}

			// Assuming AgronomistResponse can hold a list of agronomist details
			agronomistResponse.setAgronomists(agronomistDetailsList);
			agronomistResponse.setMessage("Agronomist(s) Found");
			agronomistResponse.setResponseCode("200");
		}

		return agronomistResponse;
	}

}
