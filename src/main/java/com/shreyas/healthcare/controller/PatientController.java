package com.shreyas.healthcare.controller;

import com.shreyas.healthcare.dto.request.PatientRequestDto;
import com.shreyas.healthcare.dto.response.ApiResponse;
import com.shreyas.healthcare.dto.response.PatientResponseDto;
import com.shreyas.healthcare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getPatient/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> getPatientById(@PathVariable Long id) {

        PatientResponseDto patientResponseDto = patientService.getPatientById(id);

        ApiResponse<PatientResponseDto> apiResponse = new ApiResponse<>(true, "Patient retrieved successfully", patientResponseDto);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllPatient")
    public ResponseEntity<ApiResponse<List<PatientResponseDto>>> getAllPatient() {

        List<PatientResponseDto> patients =
                patientService.getAllPatients();

        ApiResponse<List<PatientResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Patients fetched successfully",
                        patients);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePatientDetails/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatientDetails(@PathVariable Long id, @Valid @RequestBody PatientRequestDto patientRequestDto) {

        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDto);

        ApiResponse<PatientResponseDto> apiResponse = new ApiResponse<>(true, "Patient details updated with patient id - " + id, patientResponseDto);

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deletePatientDetails/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> deletePatient(@PathVariable Long id) {
        PatientResponseDto patientResponseDto = patientService.deletePatient(id);

        ApiResponse<PatientResponseDto> apiResponse = new ApiResponse<>(true, "Patient details deleted with patient id - " + id, patientResponseDto);

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteAllPatientDetails")
    public ResponseEntity<ApiResponse<String>> deleteAllPatients() {
        patientService.deleteAllPatients();

        ApiResponse<String> apiResponse = new ApiResponse<>(true, "All patient details deleted successfully", null);

        return ResponseEntity.ok(apiResponse);
    }
}
