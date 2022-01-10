package com.budgetfriendly.bmsbudgetservice.service.impl;

import com.budgetfriendly.bmsbudgetservice.common.CommonLogics;
import com.budgetfriendly.bmsbudgetservice.dto.ExpenseDTO;
import com.budgetfriendly.bmsbudgetservice.entity.Expense;
import com.budgetfriendly.bmsbudgetservice.entity.MasterExpenseCategory;
import com.budgetfriendly.bmsbudgetservice.entity.Users;
import com.budgetfriendly.bmsbudgetservice.repository.ExpenseRepository;
import com.budgetfriendly.bmsbudgetservice.repository.MasterExpenseCategoryRepository;
import com.budgetfriendly.bmsbudgetservice.repository.UsersRepository;
import com.budgetfriendly.bmsbudgetservice.response.BaseResponse;
import com.budgetfriendly.bmsbudgetservice.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MasterExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CommonLogics commonLogics;

    @Override
    public BaseResponse createExpense(ExpenseDTO expenseDTO) {
        BaseResponse response = new BaseResponse();
        try{

            Expense expense = new Expense();
            expense.setExpenseDate(expenseDTO.getExpenseDate());

            Optional<MasterExpenseCategory> dbExpenseCate = null;

            if(expenseDTO.getMasterExpenseCategoryDTO().getId() != null && expenseDTO.getMasterExpenseCategoryDTO().getId() != 0){
                dbExpenseCate  = expenseCategoryRepository.findById(expenseDTO.getMasterExpenseCategoryDTO().getId());
            }

            if(dbExpenseCate.isPresent()){
                expense.setMasterExpenseCategory(dbExpenseCate.get());
            }

            Optional<Users> dbUser = null;

            if(expenseDTO.getUserDTO().getId() != null && expenseDTO.getUserDTO().getId() != 0) {
                dbUser  = usersRepository.findById(expenseDTO.getUserDTO().getId());
            }

            if(dbUser.isPresent()){
                expense.setUser(dbUser.get());
            }

            expense.setExpenseType(expenseDTO.getExpenseType());
            expense.setExpenseAmount(expenseDTO.getExpenseAmount());
            expense.setDescription(expenseDTO.getDescription());
            expense.setCreatedAt(new Date());

            int month = commonLogics.getMonth(expenseDTO.getExpenseDate());
            expense.setExpenseMonth(month);

            int year = commonLogics.getYear(expenseDTO.getExpenseDate());
            expense.setExpenseYear(year);

            Expense dbExpense = expenseRepository.save(expense);
            response.setStatus("success");
            response.setMessage("new expense create successfully");
            response.setData(dbExpense);

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
