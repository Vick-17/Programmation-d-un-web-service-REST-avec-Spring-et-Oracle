package com.projectexam.exam.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Mappers.PatientMapper;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Repositories.PatientRepository;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    private PatientServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PatientServiceImpl(patientRepository, patientMapper);
    }

    @Test
    void createPatient_success() {
        // Arrange
        PatientCreateDto req = new PatientCreateDto();
        req.setNSS(1234567890L);
        req.setPassword("Secret#123");
        req.setNomPAT("Dupont");

        when(patientRepository.findBynSS(1234567890L)).thenReturn(Optional.empty());

        ArgumentCaptor<Patient> entityCaptor = ArgumentCaptor.forClass(Patient.class);
        Patient saved = new Patient().setNSS(1234567890L).setNomPAT("Dupont");
        when(patientRepository.saveAndFlush(entityCaptor.capture())).thenReturn(saved);

        PatientDto expected = new PatientDto(1234567890L, "Dupont", null);
        when(patientMapper.toDto(saved)).thenReturn(expected);

        // Act
        PatientDto result = service.createPatient(req);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);

        Patient persisted = entityCaptor.getValue();
        assertEquals(1234567890L, persisted.getNSS());
        assertEquals("Dupont", persisted.getNomPAT());
        assertNotNull(persisted.getPassword(), "Password should be encoded and set");
        assertNotEquals("Secret#123", persisted.getPassword(), "Password must be encoded, not plain text");
    }

    @Test
    void createPatient_duplicateNss_throws() {
        // Arrange
        PatientCreateDto req = new PatientCreateDto();
        req.setNSS(1L);
        req.setPassword("pwd");
        req.setNomPAT("Nom");

        when(patientRepository.findBynSS(1L)).thenReturn(Optional.of(new Patient()));

        // Act + Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.createPatient(req));
        assertTrue(ex.getMessage().toLowerCase().contains("déjà"));
    }

    @Test
    void createPatient_missingPassword_throws() {
        PatientCreateDto req = new PatientCreateDto();
        req.setNSS(2L);
        req.setNomPAT("Nom");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.createPatient(req));
        assertTrue(ex.getMessage().toLowerCase().contains("mot de passe"));
    }

    @Test
    void createPatient_missingNom_throws() {
        PatientCreateDto req = new PatientCreateDto();
        req.setNSS(3L);
        req.setPassword("pwd");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.createPatient(req));
        assertTrue(ex.getMessage().toLowerCase().contains("nom"));
    }
}

