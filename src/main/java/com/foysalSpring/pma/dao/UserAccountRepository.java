package com.foysalSpring.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.foysalSpring.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

}