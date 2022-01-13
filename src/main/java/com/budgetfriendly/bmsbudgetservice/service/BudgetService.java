package com.budgetfriendly.bmsbudgetservice.service;

import com.budgetfriendly.bmsbudgetservice.dto.ExpenseDTO;
import com.budgetfriendly.bmsbudgetservice.response.BaseResponse;
import com.budgetfriendly.bmsbudgetservice.response.PageResponse;

public interface BudgetService {

     BaseResponse createExpense(ExpenseDTO expenseDTO);

     PageResponse lastMonthExpense(Long userId,int pageNo,int pageSize);

}
