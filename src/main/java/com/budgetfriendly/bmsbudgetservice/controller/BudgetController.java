package com.budgetfriendly.bmsbudgetservice.controller;

import com.budgetfriendly.bmsbudgetservice.dto.ExpenseDTO;
import com.budgetfriendly.bmsbudgetservice.response.BaseResponse;
import com.budgetfriendly.bmsbudgetservice.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bms/budget/")
public class BudgetController{

    @Autowired
    private BudgetService budgetService;

    @PostMapping("createExpense")
    public BaseResponse createExpense(@RequestBody ExpenseDTO expenseDTO){
        return budgetService.createExpense(expenseDTO);
    }


}
