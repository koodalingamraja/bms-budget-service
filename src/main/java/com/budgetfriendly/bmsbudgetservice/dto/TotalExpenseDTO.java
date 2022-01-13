package com.budgetfriendly.bmsbudgetservice.dto;

import java.util.List;

public class TotalExpenseDTO {

    private TotalAmountDTO totalAmountDTO;

    private List<ExpenseViewDTO> expenseViewDTOs;

    public TotalAmountDTO getTotalAmountDTO() {
        return totalAmountDTO;
    }

    public void setTotalAmountDTO(TotalAmountDTO totalAmountDTO) {
        this.totalAmountDTO = totalAmountDTO;
    }

    public List<ExpenseViewDTO> getExpenseViewDTOs() {
        return expenseViewDTOs;
    }

    public void setExpenseViewDTOs(List<ExpenseViewDTO> expenseViewDTOs) {
        this.expenseViewDTOs = expenseViewDTOs;
    }
}
