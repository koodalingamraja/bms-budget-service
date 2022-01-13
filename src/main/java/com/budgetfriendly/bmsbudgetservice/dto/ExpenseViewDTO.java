package com.budgetfriendly.bmsbudgetservice.dto;

public class ExpenseViewDTO {

    private String expenseCategoryName;

    private String expenseType;

    private String expenseDate;

    private String description;

    private double expenseAmount;

    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }

    public void setExpenseCategoryName(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
