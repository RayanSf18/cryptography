package br.com.rayan.cryptography.controllers;

import br.com.rayan.cryptography.domain.transaction.CreateTransactionRequest;
import br.com.rayan.cryptography.domain.transaction.TransactionResponse;
import br.com.rayan.cryptography.domain.transaction.UpdateTransactionRequest;
import br.com.rayan.cryptography.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateTransactionRequest request) {
        this.transactionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateTransactionRequest request) {
        this.transactionService.update(id,request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TransactionResponse>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "pageSze", defaultValue = "10") Integer pageSize) {
        var body = this.transactionService.listAll(page, pageSize);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id) {
        var body = this.transactionService.findById(id);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
