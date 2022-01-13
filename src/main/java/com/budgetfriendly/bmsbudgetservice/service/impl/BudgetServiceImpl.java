package com.budgetfriendly.bmsbudgetservice.service.impl;

import com.budgetfriendly.bmsbudgetservice.common.CommonLogics;
import com.budgetfriendly.bmsbudgetservice.dto.ExpenseDTO;
import com.budgetfriendly.bmsbudgetservice.dto.ExpenseViewDTO;
import com.budgetfriendly.bmsbudgetservice.dto.TotalAmountDTO;
import com.budgetfriendly.bmsbudgetservice.dto.TotalExpenseDTO;
import com.budgetfriendly.bmsbudgetservice.entity.Expense;
import com.budgetfriendly.bmsbudgetservice.entity.MasterExpenseCategory;
import com.budgetfriendly.bmsbudgetservice.entity.Users;
import com.budgetfriendly.bmsbudgetservice.repository.ExpenseRepository;
import com.budgetfriendly.bmsbudgetservice.repository.MasterExpenseCategoryRepository;
import com.budgetfriendly.bmsbudgetservice.repository.UsersRepository;
import com.budgetfriendly.bmsbudgetservice.response.BaseResponse;
import com.budgetfriendly.bmsbudgetservice.response.PageResponse;
import com.budgetfriendly.bmsbudgetservice.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

            if(expenseDTO.getUserId() != null && expenseDTO.getUserId() != 0) {
                dbUser  = usersRepository.findById(expenseDTO.getUserId());
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

    @Override
    public PageResponse lastMonthExpense(Long userId,int pageNo,int pageSize) {
        PageResponse pageResponse = new PageResponse();
        TotalExpenseDTO totalExpenseDTO = new TotalExpenseDTO();
        try{
            List<ExpenseViewDTO> expenseViewDTOList = new ArrayList<>();
            TotalAmountDTO totalAmountDTO = new TotalAmountDTO();

            Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));

            int currentMonth = commonLogics.getMonth(new Date());
            int lastMonth = 0;
            if(currentMonth == 1){
                lastMonth = 12;
            }else{
                lastMonth = currentMonth - 1;
            }

            Page<Expense> lastMonthExpenseList = expenseRepository.findByLastMonth(userId,lastMonth,pageable);

            List<Double> total = new ArrayList<>();
            double totalCost = 0;

            if(lastMonthExpenseList != null && lastMonthExpenseList.getSize() != 0) {
                lastMonthExpenseList.stream().forEach(expense -> {
                    ExpenseViewDTO expenseViewDTO = new ExpenseViewDTO();
                    expenseViewDTO.setExpenseCategoryName(expense.getMasterExpenseCategory().getExpenseCategoryName());
                    expenseViewDTO.setExpenseType(expense.getExpenseType());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String date = format.format(expense.getExpenseDate());
                    expenseViewDTO.setExpenseDate(date);
                    expenseViewDTO.setDescription(expense.getDescription());
                    expenseViewDTO.setExpenseAmount(expense.getExpenseAmount());
                    total.add(expense.getExpenseAmount());
                    expenseViewDTOList.add(expenseViewDTO);
                });
            }

            for(int i = 0; i < total.size(); i++){
                totalCost += total.get(i);
            }
            totalAmountDTO.setTotalAmount(totalCost);

            totalExpenseDTO.setTotalAmountDTO(totalAmountDTO);
            totalExpenseDTO.setExpenseViewDTOs(expenseViewDTOList);

            pageResponse.setTotalRecordCount(lastMonthExpenseList.getTotalElements());
            pageResponse.setHasNext(lastMonthExpenseList.hasNext());
            pageResponse.setHasPrevious(lastMonthExpenseList.hasPrevious());
            pageResponse.setData(totalExpenseDTO);

        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResponse;
    }
}
