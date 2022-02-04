package id.belajar.spring.boot.transaction.persistence.repository;

import id.belajar.spring.boot.transaction.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT * FROM ACCOUNTS a WHERE a.email = ?1", nativeQuery = true)
    Optional<Account> findByEmail(String email);

}
