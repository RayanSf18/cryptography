package br.com.rayan.cryptography.domain.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CreateTransactionRequest(@NotBlank String userDocument, @NotBlank String creditCardToken, @NotNull Long value) {
}
