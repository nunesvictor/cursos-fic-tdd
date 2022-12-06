package br.edu.ifto.cursosfic.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EstudantesMatriculadosTest {
    private Estudante getEstudante() {
        return new Estudante(
            "Aluno 1",
            "01020304",
            LocalDate.of(1970, 1,1),
            "Rua 1, 1",
            "mail@mail.com"
        );
    }

    private TurmaCurso getTurmaCurso(int vagas) {
        final List<LocalDate> matriculasPeriod = List.of(
            LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 30));
        final List<LocalDate> aulasPeriod = List.of(
            LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 31));

        final var curso = new Curso(
            "Sistemas para Internet", 
            "3.000", 
            "Lorem ipsum dolor"
        );

        return new TurmaCurso(
            curso,
            "IFTO", 
            vagas,
            aulasPeriod.get(0),
            aulasPeriod.get(1),
            matriculasPeriod.get(0),
            matriculasPeriod.get(1)
        );
    }

    @Test
    @DisplayName("não pode matricular sem vaga disponível")
    public void naoPodeMatricularSemVagaDisponivel() {
        var dataMatricula = LocalDate.of(2022, 6, 10);
        assertThrows(UnsupportedOperationException.class, () -> {
            new EstudantesMatriculados(
                getEstudante(), getTurmaCurso(0), dataMatricula);
        });

        assertDoesNotThrow(() -> {
            new EstudantesMatriculados(
                getEstudante(), getTurmaCurso(1), dataMatricula);
        });
    }

    @Test
    @DisplayName("matricular estudante diminui as vagas da turma")
    public void matricularDiminuiVagasDisponiveis() {
        var dataMatricula = LocalDate.of(2022, 6, 10);
        var matricula = new EstudantesMatriculados(
            getEstudante(), getTurmaCurso(1), dataMatricula);
        
        assertEquals(matricula.getTurmaCurso().getVagasDisponiveis(), 0);
    }

    @Test
    @DisplayName("não permite inscrições fora do período de matrícula")
    public void naoPermiteInscricaoForaDoPeriodoDeMatricula() {
        var antesPeriodo = LocalDate.of(2022, 5, 31);
        var aposPeriodo = LocalDate.of(2022, 7, 1);
        var dentroPeriodo = LocalDate.of(2022, 6, 10);
        
        assertThrows(UnsupportedOperationException.class, () -> {
            new EstudantesMatriculados(
                getEstudante(), getTurmaCurso(1), antesPeriodo);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            new EstudantesMatriculados(
                getEstudante(), getTurmaCurso(1), aposPeriodo);
        });
        assertDoesNotThrow(() -> {
            new EstudantesMatriculados(
                getEstudante(), getTurmaCurso(1), dentroPeriodo);
        });
    }
}
