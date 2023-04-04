package com.microservices.Claims.Processing.Controller;

import com.microservices.Claims.Processing.DTO.ClaimDTO;
import com.microservices.Claims.Processing.Exception.FraudulentClaimException;
import com.microservices.Claims.Processing.services.ClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimController {
    @Autowired
    private ClaimsService claimsService;

    @PostMapping("/save")
    public ResponseEntity<ClaimDTO> createClaim(@RequestBody ClaimDTO claimDTO) {
        try {
            ClaimDTO createdClaimDTO = claimsService.createClaim(claimDTO);
            return ResponseEntity.ok(createdClaimDTO);
        } catch (FraudulentClaimException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ClaimDTO> getClaim(@PathVariable("id") Long id) {
        ClaimDTO claimDTO = claimsService.getClaim(id);
        if (claimDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(claimDTO);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ClaimDTO> updateClaim(@PathVariable("id") Long id, @RequestBody ClaimDTO claimDTO) {
        try {
            ClaimDTO updatedClaimDTO = claimsService.updateClaim(id, claimDTO);
            if (updatedClaimDTO == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(updatedClaimDTO);
            }
        } catch (FraudulentClaimException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable("id") Long id) {
        claimsService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ClaimDTO>> getAllClaims() {
        List<ClaimDTO> claimDTOs = claimsService.getAllClaims();
        return ResponseEntity.ok(claimDTOs);
    }
}
