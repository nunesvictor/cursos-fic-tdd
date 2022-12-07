package br.edu.ifto.cursosfic.models;

import java.time.LocalDate;

public class EstudanteTurma {
    private final Estudante estudante;
    private final Turma turmaCurso;
    private final LocalDate dataMatricula;

    public EstudanteTurma(Estudante estudante, Turma turmaCurso, LocalDate dataMatricula) {
        this.estudante = estudante;
        this.turmaCurso = turmaCurso;
        this.dataMatricula = dataMatricula;

        if (!podeMatricular()) {
            throw new UnsupportedOperationException("sem vagas disponíveis ou matrícula fora do prazo");
        }

        matricular();
    }

    private boolean haVagaDisponivel() {
        return turmaCurso.getVagasDisponiveis() > 0;
    }

    private boolean estaEmPeriodoDeMatricula() {
        var inicio = turmaCurso.getPeriodoMatriculas().get(0);
        var fim = turmaCurso.getPeriodoMatriculas().get(1);

        return inicio.compareTo(dataMatricula) * dataMatricula.compareTo(fim) >= 0;
    }

    private boolean podeMatricular() {
        return haVagaDisponivel() && estaEmPeriodoDeMatricula();
    }

    private void matricular() {
        int vagasDisponiveis = turmaCurso.getVagasDisponiveis();
        turmaCurso.setVagasDisponiveis(vagasDisponiveis - 1);
        turmaCurso.getEstudantesMatriculados().add(this);
    }
    
    public Estudante getEstudante() {
        return estudante;
    }
    
    public Turma getTurmaCurso() {
        return turmaCurso;
    }
    
    public LocalDate getDataMatricula() {
        return dataMatricula;
    }
}
