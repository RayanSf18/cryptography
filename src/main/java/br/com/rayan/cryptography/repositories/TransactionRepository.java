package br.com.rayan.cryptography.repositories;

import br.com.rayan.cryptography.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
