package com.budgetfriendly.bmsbudgetservice.repository;

import com.budgetfriendly.bmsbudgetservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


}
