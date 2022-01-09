package com.budgetfriendly.bmsbudgetservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "expense_date")
    private Date expenseDate;

    @ManyToOne
    @JoinColumn(name = "master_expense_category_id_fk")
    private MasterExpenseCategory masterExpenseCategory;

    @Column(name = "expense_type")
    private String expenseType;

    @Column(name = "expense_amount")
    private double expenseAmount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private Users user;

    @Column(name = "created_at")
    private Date createdAt;

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

    public MasterExpenseCategory getMasterExpenseCategory() {
        return masterExpenseCategory;
    }

    public void setMasterExpenseCategory(MasterExpenseCategory masterExpenseCategory) {
        this.masterExpenseCategory = masterExpenseCategory;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
