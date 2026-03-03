package com.example.Sistema_Clinica_Spring.Repository.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita,Long> {
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.programacion.id_programacion = :idProgramacion AND c.estado = 1")
    Long countByProgramacionIdAndEstado(@Param("idProgramacion") Long idProgramacion, @Param("estado") Integer estado);

    @Query(value = """
        SELECT
           COALESCE(te.id_especialidad, 0) as espId,
           COALESCE(e.nombre, 'SIN ESPECIALIDAD') as espNombre,
           t.id_trabajador,
           CONCAT(t.nombres, ' ', ISNULL(t.apellidos, '')) as medico,
           p.id_programacion,
           CONVERT(varchar, p.fecha, 103) as fecha,
           h.hora_inicio,
           h.hora_fin,
           p.estado as disponible
        FROM programacion p
        INNER JOIN trabajador t ON p.id_trabajador = t.id_trabajador
        LEFT JOIN trabajador_especialidad te ON t.id_trabajador = te.id_trabajador
        LEFT JOIN especialidad e ON te.id_especialidad = e.id_especialidad
        LEFT JOIN horario h ON p.id_horario = h.id_horario
        WHERE p.estado = 1
        ORDER BY p.fecha, h.hora_inicio
    """, nativeQuery = true)
    List<Object[]> obtenerHorariosDisponiblesPorEspecialidad();

    @Query("SELECT c FROM Cita c JOIN c.transaccion t WHERE t.idPaciente = :idPaciente")
    List<Cita> findByTransaccionIdPacienteOrderByCreatedAtDesc(@Param("idPaciente") Long idPaciente);
}
