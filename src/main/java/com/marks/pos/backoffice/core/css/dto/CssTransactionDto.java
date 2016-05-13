package com.marks.pos.backoffice.core.css.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.marks.pos.backoffice.core.utils.JsonDateDeserializer;
import com.marks.pos.backoffice.core.utils.JsonDateSerializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude={"retailTransaction"})
public class CssTransactionDto extends CssDto{
    private static final Integer DEFAULT_MAJOR_VERSION = 4;
    private String transactionId;
    private String transactionTypeCode;
    private Integer majorVersion = DEFAULT_MAJOR_VERSION;
    private String retailStoreId;
    private String workstationId;
    private String sequenceNumber;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date beginDateTime;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date endDateTime;

    private String operatorId;
    private String operatorFirstName;
    private String operatorLastName;
    private Boolean voided;
    private String statusReasonCode;
    private CssRetailTransactionDto retailTransaction;
    private CssEndDayTransactionDto endOfDayTransaction;
    private CssStartOfDayTransactionDto startOfDayTransaction;
    private List<CssReceiptDto> receipts;
    private String sourceSystem;
}
