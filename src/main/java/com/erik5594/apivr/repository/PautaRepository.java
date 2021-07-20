package com.erik5594.apivr.repository;

import com.erik5594.apivr.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 19:51
 */
public interface PautaRepository extends JpaRepository<Pauta, String> {
    Optional<Pauta> findPautaByIdAndInicioSessaoIsNull(String idPauta);
}
