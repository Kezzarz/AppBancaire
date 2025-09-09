package com.skylar.banking.repositories;

import com.skylar.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // select * from _user Where firstname = 'kevin'
    List<User> findAllByFirstname(String firstname);

    // select * from _user Where firstname like '%kevin%'
    List<User> findAllByFirstnameContaining(String firstname);

    // select * from _user Where firstname ilike 'kevin'
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    // select * from _user u inner join account a on u.id = a.id_user and a.iban = 'DE12345678'
    List<User> findAllByAccountIban(String iban);

    // Select * from _user where firstname = '%kevin%' and = 'kcannizzaro@aubay.com'
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    @Query("from User where firstname = :fn")
    List<User> searchByFirstname(@Param("fn") String firstname);

    @Query("from User where firstname = '%firstname%'")
    List<User> searchByFirstnameContaining(String firstname);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(String iban);

    @Query(value = "select * from _user u inner join account a on u.id = a.id_user and a.iban = :iban", nativeQuery = true)
    List<User> searchByIbanNative(String iban);

}



