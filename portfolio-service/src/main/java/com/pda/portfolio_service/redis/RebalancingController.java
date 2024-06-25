package com.pda.portfolio_service.redis;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/portfolios/rebalancing")
public class RebalancingController {

    private final RebalancingDataService rebalancingDataService;

    @PostMapping("")
    public void saveRebalancingReport(@RequestBody RebalancingData rebalancingData, @RequestParam("userId") int userId){
        rebalancingDataService.saveRebalancingReport(rebalancingData, userId);
    }

    @GetMapping("")
    public RebalancingData getRebalancingReport(@RequestParam("rebalancingId") String rebalancingId){
        return rebalancingDataService.getRebalancingReport(rebalancingId);

    }
}
