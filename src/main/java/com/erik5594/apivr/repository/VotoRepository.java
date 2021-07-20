package com.erik5594.apivr.repository;

import com.erik5594.apivr.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 22:23
 */
public interface VotoRepository extends JpaRepository<Voto, String> {
    Optional<Voto> findVotoByAssociado_CpfAndPauta_Id(String cpfAssociado, String idPauta);
    Optional<List<Voto>> findAllByPauta_Id(String idPauta);
}
