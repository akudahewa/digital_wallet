package com.exam.digitalwallet.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.exam.digitalwallet.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account,String>  {

}
