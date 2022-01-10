package com.budgetfriendly.bmsbudgetservice.service;

import com.budgetfriendly.bmsbudgetservice.dto.ExpenseDTO;
import com.budgetfriendly.bmsbudgetservice.response.BaseResponse;

public interface BudgetService {

     BaseResponse createExpense(ExpenseDTO expenseDTO);

}
