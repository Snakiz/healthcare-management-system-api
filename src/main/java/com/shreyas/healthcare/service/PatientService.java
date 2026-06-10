package com.shreyas.healthcare.service;

import com.shreyas.healthcare.dto.request.PatientRequestDto;
import com.shreyas.healthcare.dto.response.PatientResponseDto;

public interface PatientService {

    PatientResponseDto registerPatient(PatientRequestDto patientRequestDto);
    PatientResponseDto getPatientById(Long id);
}

