package br.edu.ifto.cursosfic.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EstudanteTest {
    private Estudante createEstudante(LocalDate dataNascimento) {
        return new Estudante(
            "Aluno 1",
            "01020304",
            dataNascimento,
            "Rua 1, 1",
            "mail@mail.com"
        );
    }

    @Test
    @DisplayName("deve exigir instância completa de estudante")
    public void deveExigirInstanciaCompleta() {
        assertThrows(UnsupportedOperationException.class, Estudante::new);
        assertDoesNotThrow(() -> createEstudante(LocalDate.of(1970, 1, 1)));
    }

    @Test
    @DisplayName("o estudante deve ter no mínimo 15 anos")
    public void estudanteDeveTerQuinzeAnos() {
        assertThrows(UnsupportedOperationException.class, () -> {
            createEstudante(LocalDate.of(LocalDate.now().getYear() - 14, 1, 1));
        });
    }
}
