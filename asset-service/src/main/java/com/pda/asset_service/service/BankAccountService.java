package com.pda.asset_service.service;

import com.pda.asset_service.dto.BankAccountDto;
import com.pda.asset_service.dto.BankAccountResponseDto;
import com.pda.asset_service.dto.MydataInfoDto;
import com.pda.asset_service.jpa.BankAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount convertToEntity(BankAccountResponseDto bankAccountDto);

    BankAccountDto convertToDto(BankAccount bankAccount);

    List<MydataInfoDto> mydataLink(int userId, List<String> bankAccounts);
}
