package com.marks.pos.backoffice.core.css.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CssStartOfDayTransactionDto {
    private BigDecimal openingFloatAmount;
    private String openingFloatComment;
}
