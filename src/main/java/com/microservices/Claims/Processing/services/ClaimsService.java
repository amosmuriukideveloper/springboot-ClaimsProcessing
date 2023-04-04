package com.microservices.Claims.Processing.services;

import com.microservices.Claims.Processing.DTO.ClaimDTO;
import com.microservices.Claims.Processing.DTO.ClaimMapper;
import com.microservices.Claims.Processing.Exception.FraudulentClaimException;
import com.microservices.Claims.Processing.FraudDetection.FraudDetectionClient;
import com.microservices.Claims.Processing.Repository.ClaimRepository;
import com.microservices.Claims.Processing.models.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaimsService {
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private FraudDetectionClient fraudDetectionClient;

    @Autowired
    private ClaimMapper claimMapper;

    public ClaimDTO createClaim(ClaimDTO dto) throws FraudulentClaimException {
        Claim claim = claimMapper.toEntity(dto);
        boolean isFraudulent = fraudDetectionClient.detectFraud(dto);
        if (isFraudulent) {
            throw new FraudulentClaimException("Claim is fraudulent!");
        }
        claim = (Claim) claimRepository.save(claim);
        return claimMapper.toDto(claim);
    }

    public ClaimDTO getClaim(Long id) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            return claimMapper.toDto(optionalClaim.get());
        } else {
            return null;
        }
    }

    public ClaimDTO updateClaim(Long id, ClaimDTO dto) throws FraudulentClaimException {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claim.setPolicyNumber(dto.getPolicyNumber());
            claim.setSupportingDocuments(dto.getSupportingDocuments());
            claim.setIncidentDate(dto.getIncidentDate());
            claim.setAmount(dto.getAmount());
            boolean isFraudulent = fraudDetectionClient.detectFraud(dto);
            if (isFraudulent) {
                throw new FraudulentClaimException("Claim is fraudulent!");
            }
            claim = (Claim) claimRepository.save(claim);
            return claimMapper.toDto(claim);
        } else {
            return null;
        }
    }

    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }

    public List<ClaimDTO> getAllClaims() {
        List<Claim> claims = claimRepository.findAll();
        return claims.stream().map(claimMapper::toDto).collect(Collectors.toList());
    }

}
