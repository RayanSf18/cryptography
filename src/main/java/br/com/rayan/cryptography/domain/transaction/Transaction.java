package br.com.rayan.cryptography.domain.transaction;

import br.com.rayan.cryptography.infra.cryptography.CryptographyService;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_document")
    private String encryptedUserDocument;

    @Column(name = "credit_card_token")
    private String encryptedCreditCardToken;

    private Long transactionValue;

    @Transient
    private String rawUserDocument;

    @Transient
    private String rawCreditCardToken;

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncryptedUserDocument() {
        return encryptedUserDocument;
    }

    public void setEncryptedUserDocument(String encryptedUserDocument) {
        this.encryptedUserDocument = encryptedUserDocument;
    }

    public String getEncryptedCreditCardToken() {
        return encryptedCreditCardToken;
    }

    public void setEncryptedCreditCardToken(String encryptedCreditCardToken) {
        this.encryptedCreditCardToken = encryptedCreditCardToken;
    }

    public Long getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Long transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getRawUserDocument() {
        return rawUserDocument;
    }

    public void setRawUserDocument(String rawUserDocument) {
        this.rawUserDocument = rawUserDocument;
    }

    public String getRawCreditCardToken() {
        return rawCreditCardToken;
    }

    public void setRawCreditCardToken(String rawCreditCardToken) {
        this.rawCreditCardToken = rawCreditCardToken;
    }

    @PrePersist
    public void prePersist() {
        this.encryptedUserDocument = CryptographyService.encrypt(rawUserDocument);
        this.encryptedCreditCardToken = CryptographyService.encrypt(rawCreditCardToken);
    }

    @PostLoad
    public void postLoad() {
        this.rawUserDocument = CryptographyService.decrypt(encryptedUserDocument);
        this.rawCreditCardToken = CryptographyService.decrypt(encryptedCreditCardToken);
    }
}
