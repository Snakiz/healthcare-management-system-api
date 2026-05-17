package com.shreyas.healthcare.controller;

import com.shreyas.healthcare.dto.request.PatientRequestDto;
import com.shreyas.healthcare.dto.response.ApiResponse;
import com.shreyas.healthcare.dto.response.PatientResponseDto;
import com.shreyas.healthcare.service.PatientService;
import com.shreyas.healthcare.service.impl.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/registerPatients")
    public ResponseEntity<ApiResponse<PatientResponseDto>> registerPatient(@Valid @RequestBody PatientRequestDto patientRequestDto) {

        PatientResponseDto patientResponseDto = patientService.registerPatient(patientRequestDto);

        ApiResponse<PatientResponseDto> apiResponse = new ApiResponse<>(true, "Patient registered successfully", patientResponseDto);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }
}
