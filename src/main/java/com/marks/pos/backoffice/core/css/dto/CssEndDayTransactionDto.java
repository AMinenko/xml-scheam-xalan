package com.marks.pos.backoffice.core.css.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CssEndDayTransactionDto {
    private List<CssEndDayTransactionTenderDto> tenders;
}
