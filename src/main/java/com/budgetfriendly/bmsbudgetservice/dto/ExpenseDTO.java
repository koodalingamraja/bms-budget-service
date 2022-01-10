package com.budgetfriendly.bmsbudgetservice.dto;


import java.util.Date;

public class ExpenseDTO {

    private Long id;

    private Date expenseDate;

    private MasterExpenseCategoryDTO masterExpenseCategoryDTO;

    private String expenseType;

    private double expenseAmount;

    private String description;

    private UserDTO userDTO;

    private Date createdAt;

    private int expenseMonth;

    private int expenseYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public MasterExpenseCategoryDTO getMasterExpenseCategoryDTO() {
        return masterExpenseCategoryDTO;
    }

    public void setMasterExpenseCategoryDTO(MasterExpenseCategoryDTO masterExpenseCategoryDTO) {
        this.masterExpenseCategoryDTO = masterExpenseCategoryDTO;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getExpenseMonth() {
        return expenseMonth;
    }

    public void setExpenseMonth(int expenseMonth) {
        this.expenseMonth = expenseMonth;
    }

    public int getExpenseYear() {
        return expenseYear;
    }

    public void setExpenseYear(int expenseYear) {
        this.expenseYear = expenseYear;
    }
}
