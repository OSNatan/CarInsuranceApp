package com.example.carins.task;

import com.example.carins.model.Car;
import com.example.carins.model.InsurancePolicy;
import com.example.carins.repo.InsurancePolicyRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PolicyExpiryTask {

    private static final Logger logger = LoggerFactory.getLogger(PolicyExpiryTask.class);

    private final InsurancePolicyRepository insurancePolicyRepository;

    public PolicyExpiryTask(InsurancePolicyRepository insurancePolicyRepository) {
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    @Scheduled(cron = "* 0,30 * * * *")
    @Transactional
    public void logExpiredPolicies() {
        LocalDate now = LocalDate.now();
        List<InsurancePolicy> policyList = insurancePolicyRepository.findByEndDateBeforeAndExpiredFalse(now);

        for(InsurancePolicy policy : policyList) {
            Car car = policy.getCar();

            logger.info("Policy for {} expired on {}", car.getMake()+" "+ car.getModel(), policy.getEndDate());
            policy.setExpired(true);
            insurancePolicyRepository.save(policy);
        }
    }
}
