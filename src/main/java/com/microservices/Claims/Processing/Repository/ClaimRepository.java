package com.microservices.Claims.Processing.Repository;

import com.microservices.Claims.Processing.models.Claim;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
//    @Value("${policymicroservice.baseUrl}")
//    String policymicroserviceBaseUrl;
//
//    @GetMapping("/policies/{policyNumber}")
//    PolicyDetails getPolicyByPolicyNumber(@PathVariable String policyNumber);
//
//    @Query("SELECT c FROM Claim c WHERE c.policyNumber = :policyNumber")
//    List<Claim> findByPolicyNumber(@Param("policyNumber") String policyNumber);
//
//    // add additional methods for custom queries as needed
//
//    default PolicyDetails getPolicyForClaim(Claim claim) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = policyManagementBaseUrl + "/policies/" + claim.getPolicyNumber();
//        ResponseEntity<PolicyDetails> response = restTemplate.getForEntity(url, PolicyDetails.class);
//        return response.getBody();
//    }
}
