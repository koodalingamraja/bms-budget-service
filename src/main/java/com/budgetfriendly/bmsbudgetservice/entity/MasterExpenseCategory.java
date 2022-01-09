package com.budgetfriendly.bmsbudgetservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "master_expense_category")
public class MasterExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "expense_category_name")
    private String expenseCategoryName;

    @Column(name = "expense_category_code")
    private String expenseCategoryCode;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_at")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }

    public void setExpenseCategoryName(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
    }

    public String getExpenseCategoryCode() {
        return expenseCategoryCode;
    }

    public void setExpenseCategoryCode(String expenseCategoryCode) {
        this.expenseCategoryCode = expenseCategoryCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
