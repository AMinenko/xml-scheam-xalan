package com.marks.pos.backoffice.core.css.dto;

import com.marks.pos.backoffice.core.css.dto.imagewear.CssImagewearTransactionDto;
import com.marks.pos.backoffice.core.css.dto.ped.CssPedTransactionRecordDto;
import com.marks.pos.backoffice.core.css.dto.tax.CssRetailTransactionTaxDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude={"productLineItems","tenders","taxes"})
public class CssRetailTransactionDto {
    private static final int AMOUNT_SCALE = 2;

    private List<CssRetailTransactionItemDto> productLineItems;
    private List<CssRetailTransactionTaxDto> taxes;
    private List<CssRetailTransactionTenderDto> tenders = new ArrayList<>();
    private List<CssPedTransactionRecordDto> allPedTransactions;
    private BigDecimal transactionGrossAmount = BigDecimal.ZERO.setScale(AMOUNT_SCALE, BigDecimal.ROUND_HALF_UP);
    private BigDecimal transactionSubtotal = BigDecimal.ZERO.setScale(AMOUNT_SCALE, BigDecimal.ROUND_HALF_UP);
    private BigDecimal transactionDiscountTotal = BigDecimal.ZERO.setScale(AMOUNT_SCALE, BigDecimal.ROUND_HALF_UP);
    private String originalTransactionId;
    private CssCustomerDto customerInformation;
    private String associateId;
    private String sourceSystem;
    private CssImagewearTransactionDto imagewearTransaction;
    private Boolean transactionDiscounted;
}