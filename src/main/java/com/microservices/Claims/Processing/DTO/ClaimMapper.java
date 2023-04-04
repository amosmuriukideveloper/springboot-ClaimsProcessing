package com.microservices.Claims.Processing.DTO;

import com.microservices.Claims.Processing.models.Claim;
import org.springframework.stereotype.Component;

@Component
public class ClaimMapper {
    public ClaimDTO toDto(Claim claim) {
        ClaimDTO dto = new ClaimDTO();
        dto.setId(claim.getId());
        dto.setPolicyNumber(claim.getPolicyNumber());
        dto.setSupportingDocuments(claim.getSupportingDocuments());
        dto.setIncidentDate(claim.getIncidentDate());
        dto.setAmount(claim.getAmount());
        dto.setFraudulent(claim.getFraudulent());
        dto.setReimbursed(claim.getReimbursed());
        return dto;
    }

    public Claim toEntity(ClaimDTO dto) {
        Claim claim = new Claim();
        claim.setId(dto.getId());
        claim.setPolicyNumber(dto.getPolicyNumber());
        claim.setSupportingDocuments(dto.getSupportingDocuments());
        claim.setIncidentDate(dto.getIncidentDate());
        claim.setAmount(dto.getAmount());
        claim.setFraudulent(dto.getFraudulent());
        claim.setReimbursed(dto.getReimbursed());
        return claim;
    }
}
