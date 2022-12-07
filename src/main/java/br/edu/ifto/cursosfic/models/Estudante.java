package br.edu.ifto.cursosfic.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Estudante {
    private String nome;
    private String matricula;
    private LocalDate dataNascimento;
    private String endereco;
    private String emailInstitucional;
    private final List<Celular> celulares;

    public Estudante(String nome, String matricula, LocalDate dataNascimento, String endereco, String email) {
        if (Period.between(dataNascimento, LocalDate.now()).getYears() < 15) {
            throw new UnsupportedOperationException("estudante deve ter mais de 15 anos");
        }
        
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.emailInstitucional = email;
        this.celulares = new ArrayList<>();
    }

    public Estudante() {
        throw new UnsupportedOperationException("o construtor de estudante deve inicializar todos os atributos");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public List<Celular> getCelulares() {
        return celulares;
    }
}
