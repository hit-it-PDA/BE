package com.pda.portfolio_service.feign;

import com.pda.portfolio_service.dto.UserAgeTestScoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service", url = "${service.url.user}")
public interface UserServiceClient {
    @PostMapping("/api/users/openfeign/{user_id}")
    UserAgeTestScoreDto getUserAgeTestScore(@PathVariable("user_id") int userId);

}