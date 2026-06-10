package com.shreyas.healthcare.service;

import com.shreyas.healthcare.dto.request.PatientRequestDto;
import com.shreyas.healthcare.dto.response.PatientResponseDto;

import java.util.List;

public interface PatientService {

    PatientResponseDto registerPatient(PatientRequestDto patientRequestDto);

    PatientResponseDto getPatientById(Long id);

    List<PatientResponseDto> getAllPatients();
}

