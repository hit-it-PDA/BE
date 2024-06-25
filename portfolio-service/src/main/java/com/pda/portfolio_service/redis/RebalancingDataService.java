package com.pda.portfolio_service.redis;

import com.mysql.cj.exceptions.StreamingNotifiable;
import com.pda.utils.exception.sms.SmsCertificationException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
//@RequiredArgsConstructor
@AllArgsConstructor
public class RebalancingDataService {
    private final RebalancingDataRepository rebalancingDataRepository;



    public String createRebalancingId(int userId){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = currentDate.format(formatter);

        return formattedDate + userId;
    }

    public void saveRebalancingReport(RebalancingData rebalancingData, int userId){
        String rebalancingId = createRebalancingId(userId);
        rebalancingDataRepository.saveRebalancingReport(rebalancingData, rebalancingId);
    }

    public RebalancingData getRebalancingReport(String rebalancingId){
        return rebalancingDataRepository.getRebalancingReport(rebalancingId);
    }
}
