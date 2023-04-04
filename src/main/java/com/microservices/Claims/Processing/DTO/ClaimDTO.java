package com.microservices.Claims.Processing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDTO {
    private Long id;

    private String policyNumber;

    private String supportingDocuments;

    private Date incidentDate;

    private Double amount;

    private Boolean fraudulent;

    private Boolean reimbursed;
}
