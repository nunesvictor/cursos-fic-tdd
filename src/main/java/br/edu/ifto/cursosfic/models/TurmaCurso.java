package br.edu.ifto.cursosfic.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TurmaCurso {
    private final Curso curso;
    private final String local;
    private final int vagas;
    private int vagasDisponiveis;
    private LocalDate inicioAulas;
    private LocalDate fimAulas;
    private LocalDate inicioMatriculas;
    private LocalDate fimMatriculas;
    private final List<EstudantesMatriculados> estudantesMatriculados;
    
    public TurmaCurso(Curso curso, String local, int vagas, LocalDate inicioAulas, LocalDate fimAulas,
            LocalDate inicioMatriculas, LocalDate fimMatriculas) {
        
        this.curso = curso;
        this.local = local;
        this.vagas = vagas;
        this.vagasDisponiveis = vagas;
        this.inicioAulas = inicioAulas;
        this.fimAulas = fimAulas;
        this.inicioMatriculas = inicioMatriculas;
        this.fimMatriculas = fimMatriculas;
        this.estudantesMatriculados = new ArrayList<>();

        if (!periodosDeMatriculaECursoSaoValidos()) {
            throw new UnsupportedOperationException("períodos de matrícula ou de curso inválidos");
        }
    }

    private boolean periodosDeMatriculaECursoSaoValidos() {
        return inicioMatriculas.isBefore(fimMatriculas) &&
            fimMatriculas.isBefore(inicioAulas) &&
            inicioAulas.isBefore(fimAulas);
    }

    public Curso getCurso() {
        return curso;
    }

    public String getLocal() {
        return local;
    }

    public int getVagas() {
        return vagas;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public LocalDate getInicioAulas() {
        return inicioAulas;
    }

    public void setInicioAulas(LocalDate inicioAulas) {
        this.inicioAulas = inicioAulas;
    }

    public LocalDate getFimAulas() {
        return fimAulas;
    }

    public void setFimAulas(LocalDate fimAulas) {
        this.fimAulas = fimAulas;
    }

    public LocalDate getInicioMatriculas() {
        return inicioMatriculas;
    }

    public void setInicioMatriculas(LocalDate inicioMatriculas) {
        this.inicioMatriculas = inicioMatriculas;
    }

    public LocalDate getFimMatriculas() {
        return fimMatriculas;
    }

    public void setFimMatriculas(LocalDate fimMatriculas) {
        this.fimMatriculas = fimMatriculas;
    }

    public List<EstudantesMatriculados> getEstudantesMatriculados() {
        return this.estudantesMatriculados;
    }

    public List<Estudante> getEstudantes() {
        if (this.estudantesMatriculados.size() == 0) {
            throw new UnsupportedOperationException("nenhum estudante matriculado nessa turma");
        }

        return this.estudantesMatriculados
            .stream()
            .map(e -> e.getEstudante())
            .toList();
    }

    public List<LocalDate> getPeriodoMatriculas() {
        return List.of(inicioMatriculas, fimMatriculas);
    }
}
