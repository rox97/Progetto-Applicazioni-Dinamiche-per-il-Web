package it.univr.webapp.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomer {
    private String custCode;
    private String custName;
    private String custCity;
    private String workingArea;
    private String custCountry;
}
