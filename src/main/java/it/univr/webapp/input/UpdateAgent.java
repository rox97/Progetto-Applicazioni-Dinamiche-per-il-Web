package it.univr.webapp.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAgent {
    private String agentCode;
    private String agentName;
    private String workingArea;
    private BigDecimal commission;
    private String phoneNo;
    private String country;
}
