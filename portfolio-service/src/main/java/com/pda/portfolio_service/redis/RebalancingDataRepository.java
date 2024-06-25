package com.pda.portfolio_service.redis;

// redis에 리밸런싱 id : 리밸런싱 보고서 저장
// id : YYMMDD + userId

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pda.utils.json.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RebalancingDataRepository {

//    private final StringRedisTemplate redisTemplate;

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;


    // redis 저장
    public void saveRebalancingReport(RebalancingData rebalancingData, String rebalancingId){

        redisTemplate.opsForValue().set(rebalancingId, rebalancingData);

        log.info("RebalancingData 저장 = {}", rebalancingId, rebalancingData);
    }

    // redis 조회
    public RebalancingData getRebalancingReport(String rebalancingId) {
        log.info(" :: rebalancing report = {}", rebalancingId);
        return (RebalancingData) redisTemplate.opsForValue().get(rebalancingId);
    }

}
