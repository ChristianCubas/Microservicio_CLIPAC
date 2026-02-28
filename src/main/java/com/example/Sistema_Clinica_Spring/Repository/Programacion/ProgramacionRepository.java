package com.example.Sistema_Clinica_Spring.Repository.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProgramacionRepository extends JpaRepository<Programacion,Long> {
    List<Programacion> findByFechaBetween(LocalDate inicio, LocalDate fin);

    @Query("""
        SELECT DISTINCT p
        FROM Programacion p
        LEFT JOIN p.trabajador t
        LEFT JOIN t.especialidades e
        WHERE p.fecha BETWEEN :inicio AND :fin
        AND (:trabajadorId IS NULL OR t.id_trabajador = :trabajadorId)
        AND (:consultorioId IS NULL OR p.consultorio.id_consultorio = :consultorioId)
        AND (:especialidadId IS NULL OR e.id_especialidad = :especialidadId)
    """)
    List<Programacion> filtrar(
            LocalDate inicio,
            LocalDate fin,
            Long trabajadorId,
            Long consultorioId,
            Long especialidadId
    );

    @Query("""
        SELECT p FROM Programacion p
        WHERE p.dia = :dia
        AND p.horario.id_horario = :horarioId
        AND (
            p.trabajador.id_trabajador = :trabajadorId
            OR
            p.consultorio.id_consultorio = :consultorioId
        )
    """)
    List<Programacion> validarCruce(
            @Param("dia") Integer dia,
            @Param("horarioId") Long horarioId,
            @Param("trabajadorId") Long trabajadorId,
            @Param("consultorioId") Long consultorioId
    );
}
