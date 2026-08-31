package de.thm.reportify.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NutzerRepository extends JpaRepository<Nutzer, Long> {

    Optional<Nutzer> findByBenutzernameIgnoreCaseAndAktivTrue(
            String benutzername);
}