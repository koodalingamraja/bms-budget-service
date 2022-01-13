package com.budgetfriendly.bmsbudgetservice.controller;

import com.budgetfriendly.bmsbudgetservice.config.UserContextHolder;
import com.budgetfriendly.bmsbudgetservice.dto.ExpenseDTO;
import com.budgetfriendly.bmsbudgetservice.dto.UserDTO;
import com.budgetfriendly.bmsbudgetservice.response.BaseResponse;
import com.budgetfriendly.bmsbudgetservice.response.PageResponse;
import com.budgetfriendly.bmsbudgetservice.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bms/budget/")
public class BudgetController{

    @Autowired
    private BudgetService budgetService;

    @PostMapping("createExpense")
    public BaseResponse createExpense(@RequestBody ExpenseDTO expenseDTO){
//        UserDTO userDto = UserContextHolder.getUserDto();
//        String userName = userDto.getUserName();
//        System.out.println("user::"+userName);
        return budgetService.createExpense(expenseDTO);
    }

    @GetMapping("lastMonthExpense")
    public PageResponse lastMonthExpense(@RequestParam (value = "userId") Long userId,
                                         @RequestParam (value = "pageNo") int pageNo,
                                         @RequestParam (value = "pageSize") int pageSize){
        return budgetService.lastMonthExpense(userId,pageNo,pageSize);

    }


}
