package br.com.rayan.cryptography.domain.transaction;

public record TransactionResponse(Long id, String userDocument, String creditCardToken, Long value) {

    public static TransactionResponse fromEntity(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getRawUserDocument(),
                transaction.getRawCreditCardToken(),
                transaction.getTransactionValue()
        );
    }
}
