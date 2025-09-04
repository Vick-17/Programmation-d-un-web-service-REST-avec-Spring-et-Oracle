package com.projectexam.exam.Security;

/**
 * UserDetailsService domaine: recherche des utilisateurs par identifiant
 * numérique (NSS patient ou matricule médecin) et construction des rôles.
 */

import com.projectexam.exam.Models.Medecin;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Repositories.MedecinRepository;
import com.projectexam.exam.Repositories.PatientRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    public DomainUserDetailsService(PatientRepository patientRepository, MedecinRepository medecinRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Accept either nSS (patient) or matricule (medecin). Both are Longs.
        Long key;
        try {
            key = Long.parseLong(username);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Identifiant invalide: " + username);
        }

        // Try patient by NSS first
        Patient patient = patientRepository.findBynSS(key).orElse(null);
        if (patient != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_PATIENT");
            return new User(String.valueOf(patient.getNSS()), patient.getPassword(), Collections.singleton(authority));
        }

        // Try medecin by matricule
        Medecin medecin = medecinRepository.findByMatricule(key).orElse(null);
        if (medecin != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_MEDECIN");
            return new User(String.valueOf(medecin.getMatricule()), medecin.getPassword(), Collections.singleton(authority));
        }

        throw new UsernameNotFoundException("Utilisateur introuvable pour identifiant: " + username);
    }
}
