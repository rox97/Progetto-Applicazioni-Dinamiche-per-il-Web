package it.univr.webapp.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomer {
    private String custCode;
    private String custName;
    private String custCity;
    private String workingArea;
    private String custCountry;
    private BigInteger grade;
    private BigDecimal openingAmt;
    private BigDecimal receiveAmt;
    private BigDecimal paymentAmt;
    private BigDecimal outstandingAmt;
    private String phoneNo;
}
