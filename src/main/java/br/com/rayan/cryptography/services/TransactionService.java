package br.com.rayan.cryptography.services;

import br.com.rayan.cryptography.domain.transaction.CreateTransactionRequest;
import br.com.rayan.cryptography.domain.transaction.Transaction;
import br.com.rayan.cryptography.domain.transaction.TransactionResponse;
import br.com.rayan.cryptography.domain.transaction.UpdateTransactionRequest;
import br.com.rayan.cryptography.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void create(CreateTransactionRequest request) {
        var transaction = new Transaction();
        transaction.setRawUserDocument(request.userDocument());
        transaction.setRawCreditCardToken(request.creditCardToken());
        transaction.setTransactionValue(request.value());

        this.transactionRepository.save(transaction);
    }

    public Page<TransactionResponse> listAll(int page, int pageSize) {
        var content = transactionRepository.findAll(PageRequest.of(page, pageSize));
        return content.map(transaction -> TransactionResponse.fromEntity(transaction));
    }

    public TransactionResponse findById(Long id) {
        var content = transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TransactionResponse.fromEntity(content);
    }

    @Transactional
    public void update(Long id, UpdateTransactionRequest request) {
        var content = transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        content.setTransactionValue(request.value());
        this.transactionRepository.save(content);
    }

    @Transactional
    public void deleteById(Long id) {
        var content = transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.transactionRepository.deleteById(content.getId());
    }
}
