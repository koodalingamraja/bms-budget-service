package com.budgetfriendly.bmsbudgetservice.repository;

import com.budgetfriendly.bmsbudgetservice.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense , Long> {

    @Query("select expense from Expense as expense where expense.user.id = :userId AND expense.expenseMonth = :lastMonth")
    Page<Expense> findByLastMonth(@Param("userId") Long userId,@Param("lastMonth") int lastMonth, Pageable pageable);
}
