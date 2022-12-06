package br.edu.ifto.cursosfic.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TurmaCursoTest {
    private Estudante getEstudante() {
        return new Estudante(
            "Aluno 1",
            "01020304",
            LocalDate.of(1970, 1,1),
            "Rua 1, 1",
            "mail@mail.com"
        );
    }

    private Curso getCurso() {
        return new Curso(
            "Sistemas para Internet", 
            "3.000", 
            "Lorem ipsum dolor"
        );
    }

    private TurmaCurso getTurmaCurso(List<LocalDate> matriculasPeriod, List<LocalDate> aulasPeriod) {
        return new TurmaCurso(
            getCurso(),
            "IFTO", 
            40,
            aulasPeriod.get(0),
            aulasPeriod.get(1),
            matriculasPeriod.get(0),
            matriculasPeriod.get(1)
        );
    }
    
    private TurmaCurso getTurmaCursoComPeriodoInvalido() {
        final List<LocalDate> matriculasPeriod = List.of(
            LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 31));
        final List<LocalDate> aulasPeriod = List.of(
            LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 30));

        return getTurmaCurso(matriculasPeriod, aulasPeriod);
    }

    private TurmaCurso getTurmaCursoComPeriodoValido() {
        final List<LocalDate> matriculasPeriod = List.of(
            LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 30));
            final List<LocalDate> aulasPeriod = List.of(
            LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 31));

        return getTurmaCurso(matriculasPeriod, aulasPeriod);
    }

    @Test
    @DisplayName("período de matrícula deve ser anterior ao do curso")
    public void periodoDeMatriculaDeveSerAnteriorAoCurso() {
        assertThrows(UnsupportedOperationException.class,
            this::getTurmaCursoComPeriodoInvalido);
        assertDoesNotThrow(this::getTurmaCursoComPeriodoValido);
    }

    @Test
    @DisplayName("deve exibir erro ao listar turma sem alunos matriculados")
    public void deveExibirErroAoListarAlunosDeTurmaVazia() {
        assertThrows(UnsupportedOperationException.class,
            getTurmaCursoComPeriodoValido()::getEstudantes);
    }

    @Test
    @DisplayName("não deve exibir erro ao listar turma com alunos")
    public void naoDeveExibirErroAoListarTurmaNaoVazia() {
        var estudante = getEstudante();
        var dataMatricula = LocalDate.of(2022, 6, 10);
        var turmaCurso = getTurmaCursoComPeriodoValido();

        new EstudantesMatriculados(
            estudante, turmaCurso, dataMatricula);
        
        assertDoesNotThrow(turmaCurso::getEstudantes);
        assertEquals(1, turmaCurso.getEstudantes().size());
    }
}
