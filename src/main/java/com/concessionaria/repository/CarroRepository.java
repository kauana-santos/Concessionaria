package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByChassi(String chassi);
    boolean existsByPlaca(String placa);

    @Query("""
        SELECT c FROM Carro c
        WHERE (:cor IS NULL OR c.cor = :cor)
        AND (:ano IS NULL OR c.anoFabricacao = :ano)
    """)
    List<Carro> findByFiltros(
            @Param("cor") String cor,
            @Param("ano") Integer ano
    );

}
