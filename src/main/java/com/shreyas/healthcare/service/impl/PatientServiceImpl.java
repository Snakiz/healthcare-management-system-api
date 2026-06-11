package com.shreyas.healthcare.service.impl;

import com.shreyas.healthcare.dto.request.PatientRequestDto;
import com.shreyas.healthcare.dto.response.PatientResponseDto;
import com.shreyas.healthcare.entity.Patient;
import com.shreyas.healthcare.exception.ResourceAlreadyExistsException;
import com.shreyas.healthcare.exception.ResourceNotFoundException;
import com.shreyas.healthcare.repository.PatientRepository;
import com.shreyas.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientResponseDto registerPatient(PatientRequestDto patientRequestDto) {

        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new ResourceAlreadyExistsException("Patient with email " + patientRequestDto.getEmail() + " already exists");
        }

        Patient patient = mapToEntity(patientRequestDto);

        Patient savePatient = patientRepository.save(patient);


        return mapToResponseDto(savePatient);
    }

    @Override
    public PatientResponseDto getPatientById(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient with Id " + id + " does not exist"));

        return mapToResponseDto(patient);
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {

        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDto updatePatient(
            Long id,
            PatientRequestDto patientRequestDto) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: " + id));

        patient.setFirstName(
                patientRequestDto.getFirstName());

        patient.setLastName(
                patientRequestDto.getLastName());

        patient.setEmail(
                patientRequestDto.getEmail());

        patient.setPhoneNumber(
                patientRequestDto.getPhoneNumber());

        patient.setAge(
                patientRequestDto.getAge());

        patient.setGender(
                patientRequestDto.getGender());

        Patient updatedPatient =
                patientRepository.save(patient);

        return mapToResponseDto(updatedPatient);
    }

    @Override
    public PatientResponseDto deletePatient(Long id) {

        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient with id " + id + " does not exist"));

        patientRepository.deleteById(id);
        return mapToResponseDto(patient);
    }

    @Override
    public void deleteAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        if (patients.isEmpty()) {
            throw new ResourceNotFoundException("No patients found to delete");
        }

        patientRepository.deleteAll();
    }

    private Patient mapToEntity(PatientRequestDto patientRequestDto) {

        Patient patient = new Patient();

        patient.setFirstName(patientRequestDto.getFirstName());
        patient.setLastName(patientRequestDto.getLastName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
        patient.setAge(patientRequestDto.getAge());
        patient.setGender(patientRequestDto.getGender());

        return patient;
    }

    private PatientResponseDto mapToResponseDto(Patient patient) {

        PatientResponseDto responseDto = new PatientResponseDto();

        responseDto.setId(patient.getId());
        responseDto.setFirstName(patient.getFirstName());
        responseDto.setLastName(patient.getLastName());
        responseDto.setEmail(patient.getEmail());
        responseDto.setPhoneNumber(patient.getPhoneNumber());
        responseDto.setAge(patient.getAge());
        responseDto.setGender(patient.getGender());

        return responseDto;
    }

}
