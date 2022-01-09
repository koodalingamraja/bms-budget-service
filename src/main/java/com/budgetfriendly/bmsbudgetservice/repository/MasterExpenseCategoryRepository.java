package com.budgetfriendly.bmsbudgetservice.repository;

import com.budgetfriendly.bmsbudgetservice.entity.MasterExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterExpenseCategoryRepository extends JpaRepository<MasterExpenseCategory , Long> {
}
