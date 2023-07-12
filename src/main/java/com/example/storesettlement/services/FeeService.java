package com.example.storesettlement.services;

import com.example.storesettlement.model.Fee;
import com.example.storesettlement.repositories.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final FeeRepository feeRepository;

    public Fee getFee() {
        if(feeRepository.findById(1).isEmpty()) {
            Fee fee = Fee.builder()
                    .fee(0.1)
                    .build();
            return feeRepository.save(fee);
        }
        return feeRepository.findById(1).orElseThrow();
    }

    public Fee setFee(double f) {
        Fee fee = Fee.builder()
                .id(1)
                .fee(f)
                .build();
        return feeRepository.save(fee);
    }
}
