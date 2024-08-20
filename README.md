# Hit-it! Back-end 

포트폴리오 추천과 자산 관리, 노후 대비 기능을 제공하는 퇴직연금운용 노후 대비 자산관리 서비스 <b>Hit it!</b>의 Back-end 저장소입니다.

### 사용 기술
- Spring boot, Spring Security, Spring Data JPA 3.3.0
- Java 17.0
- MySql 8.0
- RabbitMQ 3.13.3
- Redis 
- AWS EC2 t3.large
- AWS RDS 
  
### 아키텍처

<img src="https://github.com/user-attachments/assets/44519970-5991-4926-8cc5-4da2b5e2da4b" width="70%">

### 패키지 구조

>마이크로서비스 아키텍처(MSA)를 기반으로 하는 멀티 모듈 프로젝트입니다. <br/>
각 모듈은 독립적으로 배포될 수 있으며, 서로 다른 서비스를 제공하여 전체적인 시스템의 기능을 구현합니다. <br/>
5가지 모듈로 구성되어 있습니다. <br/>
>> 1. mydata-service
>> 2. asset-service
>> 3. portfolio-service
>> 4. user-service
>> 5. utils  

#### 1. mydata-service


<details>
<summary> 구조도 </summary>
<div markdown="1">
  
```
mydata-service
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── pda
    │   │           └── mydata_service
    │   │               ├── MydataServiceApplication.java
    │   │               ├── controller
    │   │               │   └── MydataController.java
    │   │               ├── dto
    │   │               │   ├── BankAccountDto.java
    │   │               │   ├── CardDto.java
    │   │               │   ├── LoanDto.java
    │   │               │   ├── PensionDto.java
    │   │               │   ├── RetirementAccountDto.java
    │   │               │   ├── SecurityAccountDto.java
    │   │               │   ├── SecurityStockDto.java
    │   │               │   └── SecurityTransactionDto.java
    │   │               ├── jpa
    │   │               │   ├── BankAccount.java
    │   │               │   ├── BankAccountRepository.java
    │   │               │   ├── Card.java
    │   │               │   ├── CardRepository.java
    │   │               │   ├── Loan.java
    │   │               │   ├── LoanRepository.java
    │   │               │   ├── MydataUser.java
    │   │               │   ├── MydataUserRepository.java
    │   │               │   ├── Pension.java
    │   │               │   ├── PensionRepository.java
    │   │               │   ├── SecurityAccount.java
    │   │               │   ├── SecurityAccountRepository.java
    │   │               │   ├── SecurityStock.java
    │   │               │   ├── SecurityStockRepository.java
    │   │               │   ├── SecurityTransaction.java
    │   │               │   └── SecurityTransactionRepository.java
    │   │               └── service
    │   │                   ├── BankAccountService.java
    │   │                   ├── BankAccountServiceImpl.java
    │   │                   ├── MydataService.java
    │   │                   └── MydataServiceImpl.java
    │   └── resources
    │       ├── application-db.properties
    │       ├── application.properties
    │       └── env.properties
    └── test
        └── java
            └── com
                └── pda
                    └── mydata_service
                        └── MydataServiceApplicationTests.java

```
</details>

#### 2. asset-service

<details>
<summary> 구조도 </summary>
<div markdown="1">

```
asset-service
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── pda
    │   │           └── asset_service
    │   │               ├── AssetServiceApplication.java
    │   │               ├── controller
    │   │               │   ├── AccountController.java
    │   │               │   └── AssetController.java
    │   │               ├── dto
    │   │               │   ├── AccountCreateDto.java
    │   │               │   ├── AccountDto.java
    │   │               │   ├── BankAccountDto.java
    │   │               │   ├── BankAccountResponseDto.java
    │   │               │   ├── CardDto.java
    │   │               │   ├── CardResponseDto.java
    │   │               │   ├── LoanDto.java
    │   │               │   ├── LoanResponseDto.java
    │   │               │   ├── MydataInfoDto.java
    │   │               │   ├── PensionDto.java
    │   │               │   ├── PensionResponseDto.java
    │   │               │   ├── RetirementAccountDto.java
    │   │               │   ├── RetirementAccountResponseDto.java
    │   │               │   ├── SecurityAccountDto.java
    │   │               │   ├── SecurityAccountResponseDto.java
    │   │               │   ├── SecurityAccountStocksDto.java
    │   │               │   ├── SecurityAccountTransactionsDto.java
    │   │               │   ├── SecurityStockDto.java
    │   │               │   ├── SecurityStockResponseDto.java
    │   │               │   ├── SecurityTransactionDto.java
    │   │               │   ├── SecurityTransactionResponseDto.java
    │   │               │   └── UserAccountInfoDto.java
    │   │               ├── feign
    │   │               │   └── MydataServiceClient.java
    │   │               ├── jpa
    │   │               │   ├── Account.java
    │   │               │   ├── AccountRepository.java
    │   │               │   ├── AssetUser.java
    │   │               │   ├── AssetUserRepository.java
    │   │               │   ├── BankAccount.java
    │   │               │   ├── BankAccountRepository.java
    │   │               │   ├── Card.java
    │   │               │   ├── CardRepository.java
    │   │               │   ├── Loan.java
    │   │               │   ├── LoanRepository.java
    │   │               │   ├── MydataInfo.java
    │   │               │   ├── MydataInfoRepository.java
    │   │               │   ├── Pension.java
    │   │               │   ├── PensionId.java
    │   │               │   ├── PensionRepository.java
    │   │               │   ├── SecurityAccount.java
    │   │               │   ├── SecurityAccountRepository.java
    │   │               │   ├── SecurityStock.java
    │   │               │   ├── SecurityStockRepository.java
    │   │               │   ├── SecurityTransaction.java
    │   │               │   └── SecurityTransactionRepository.java
    │   │               ├── redis
    │   │               │   └── RedisConfig.java
    │   │               ├── service
    │   │               │   ├── AccountService.java
    │   │               │   ├── AccountServiceImpl.java
    │   │               │   ├── AssetService.java
    │   │               │   ├── AssetServiceImpl.java
    │   │               │   ├── BankAccountService.java
    │   │               │   ├── BankAccountServiceImpl.java
    │   │               │   ├── CardService.java
    │   │               │   ├── CardServiceImpl.java
    │   │               │   ├── LoanService.java
    │   │               │   ├── LoanServiceImpl.java
    │   │               │   ├── MydataInfoService.java
    │   │               │   ├── MydataServiceImpl.java
    │   │               │   ├── PensionService.java
    │   │               │   ├── PensionServiceImpl.java
    │   │               │   ├── SecurityAccountService.java
    │   │               │   ├── SecurityAccountServiceImpl.java
    │   │               │   ├── SecurityStockService.java
    │   │               │   ├── SecurityStockServiceImpl.java
    │   │               │   ├── SecurityTransactionService.java
    │   │               │   └── SecurityTransactionServiceImpl.java
    │   │               └── sms
    │   │                   ├── SMSCertificationRepository.java
    │   │                   ├── SMSCertificationService.java
    │   │                   ├── SmsCertificationUtil.java
    │   │                   └── UserDto.java
    │   └── resources
    │       ├── application-db.properties
    │       ├── application-sms.properties
    │       ├── application.properties
    │       └── env.properties
    └── test
        └── java
            └── com
                └── pda
                    └── asset_service
                        └── AssetServiceApplicationTests.java
```
</details>

#### 3. portfolio-service

포트폴리오 관련 기능을 제공하는 서비스로, 포트폴리오 추천 포트폴리오 선택, 리밸런싱 알림 등 다양한 포트폴리오 관련 기능을 포함합니다.

- `HititPortfolio` : 자체 서비스 포트폴리오를 추천 받는 클래스들이 포함되어있습니다.
- `MyDataPortfolio` : 마이데이터로 포트폴리오를 추천 받는 클래스들이 포함되어있습니다.
- `Optimize` : 사용자의 포트폴리오를 리밸런싱 후, 알림을 보내는 클래스들이 포함되어있습니다.

<details>
<summary> 구조도 </summary>
<div markdown="1">

```
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── pda
    │   │           └── portfolio_service
    │   │               ├── PortfolioServiceApplication.java
    │   │               ├── controller
    │   │               │   ├── DataController.java
    │   │               │   ├── PortfolioController.java
    │   │               │   └── PortfolioMessageController.java
    │   │               ├── dto
    │   │               │   ├── FundProductDto.java
    │   │               │   ├── HititPortfoliosFundsResponseDto.java
    │   │               │   ├── HititPortfoliosFundsStocksAndBondsResponseDto.java
    │   │               │   ├── HititPortfoliosResponseDto.java
    │   │               │   ├── MyDataFlaskLevelTest.java
    │   │               │   ├── MyDataPortfolioDto.java
    │   │               │   ├── OptimizeDto.java
    │   │               │   ├── OptimizeResponseCamelCaseDto.java
    │   │               │   ├── OptimizeResponseMapper.java
    │   │               │   ├── PortfolioFundAssetResponseDto.java
    │   │               │   ├── StockIncomeRevResponseDto.java
    │   │               │   ├── StockRevIncomeRequestDto.java
    │   │               │   ├── UserAgeTestScoreDto.java
    │   │               │   └── UserPortfolioFundRequestDto.java
    │   │               ├── dto_test
    │   │               │   ├── Bond.java
    │   │               │   ├── Fund.java
    │   │               │   ├── MyDataTestDto.java
    │   │               │   ├── Portfolio.java
    │   │               │   ├── PortfolioResponse.java
    │   │               │   └── Stock.java
    │   │               ├── feign
    │   │               │   ├── FlaskTestServiceClient.java
    │   │               │   ├── MyDataFlaskLevelTestResponseDto.java
    │   │               │   ├── MyDataFlaskResponseDto.java
    │   │               │   ├── MyDataFundData.java
    │   │               │   ├── MyDataPortfolioServiceClient.java
    │   │               │   ├── MyDataTransaction.java
    │   │               │   ├── MydataAssetClient.java
    │   │               │   ├── MydataDto.java
    │   │               │   ├── MydataUserClient.java
    │   │               │   ├── OptimizeResponseDto.java
    │   │               │   ├── OptimizeServiceClient.java
    │   │               │   ├── PensionResponseDto.java
    │   │               │   ├── SecurityAccountStocksDto.java
    │   │               │   ├── SecurityAccountTransactionsDto.java
    │   │               │   ├── SecurityTransactionDto.java
    │   │               │   └── UserServiceClient.java
    │   │               ├── jpa
    │   │               │   ├── FundBonds.java
    │   │               │   ├── FundBondsRepository.java
    │   │               │   ├── FundPrices.java
    │   │               │   ├── FundPricesId.java
    │   │               │   ├── FundPricesRepository.java
    │   │               │   ├── FundProducts.java
    │   │               │   ├── FundProductsRepository.java
    │   │               │   ├── FundStocks.java
    │   │               │   ├── FundStocksRepository.java
    │   │               │   ├── Portfolio.java
    │   │               │   ├── PortfolioFund.java
    │   │               │   ├── PortfolioFundAsset.java
    │   │               │   ├── PortfolioFundAssetRepository.java
    │   │               │   ├── PortfolioFundBond.java
    │   │               │   ├── PortfolioFundBondId.java
    │   │               │   ├── PortfolioFundBondRepository.java
    │   │               │   ├── PortfolioFundId.java
    │   │               │   ├── PortfolioFundRepository.java
    │   │               │   ├── PortfolioFundStock.java
    │   │               │   ├── PortfolioFundStockId.java
    │   │               │   ├── PortfolioFundStockRepository.java
    │   │               │   ├── PortfolioRepository.java
    │   │               │   ├── UserPortfolio.java
    │   │               │   ├── UserPortfolioRepository.java
    │   │               │   ├── UserPortfolios.java
    │   │               │   ├── UserPortfoliosFundProducts.java
    │   │               │   ├── UserPortfoliosFundProductsRepository.java
    │   │               │   └── UserPortfoliosRepository.java
    │   │               ├── redis
    │   │               │   └── RebalancingData.java
    │   │               └── service
    │   │                   ├── DataService.java
    │   │                   └── PortfolioService.java
    │   └── resources
    │       ├── application-db.properties
    │       ├── application.properties
    │       └── env.properties
    └── test
        └── java
            └── com
                └── pda
                    └── portfolio_service
                        └── PortfolioServiceApplicationTests.java
```
</details>

#### 4. user-service

사용자 관련 기능을 제공하는 서비스로, 로그인 및 회원가입, 테스트, 알림 등 다양한 사용자 관련 기능을 포함합니다.

- `investment_test` : 투자 성향 테스트 클래스들이 포함되어있습니다.
- `notification` : 사용자에게 보낼 알림 기능 클래스들이 포함되어있습니다.
- `retirements` : 노후 준비 종합 진단 테스트 클래스들이 포함되어있습니다.
- `user_service` : 로그인 및 회원가입 등의 사용자 관리 기능 클래스들이 포함되어있습니다.

<details>
<summary> 구조도 </summary>
<div markdown="1">

```
user_service
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── pda
    │   │           ├── UserServiceApplication.java
    │   │           ├── investment_test
    │   │           │   ├── controller
    │   │           │   │   └── InvestmentTestController.java
    │   │           │   ├── dto
    │   │           │   │   ├── QuestionDto.java
    │   │           │   │   └── ResultDto.java
    │   │           │   ├── jpa
    │   │           │   │   ├── InvestmentType.java
    │   │           │   │   ├── answer
    │   │           │   │   │   ├── Answer.java
    │   │           │   │   │   └── AnswerRepository.java
    │   │           │   │   ├── question
    │   │           │   │   │   ├── Question.java
    │   │           │   │   │   └── QuestionRepository.java
    │   │           │   │   └── user_answer
    │   │           │   │       ├── UserAnswer.java
    │   │           │   │       └── UserAnswerRepository.java
    │   │           │   └── service
    │   │           │       └── InvestmentTestService.java
    │   │           ├── notification
    │   │           │   ├── controller
    │   │           │   │   └── NotificationController.java
    │   │           │   ├── dto
    │   │           │   ├── jpa
    │   │           │   │   ├── Notification.java
    │   │           │   │   └── NotificationRepository.java
    │   │           │   └── service
    │   │           │       └── NotificationService.java
    │   │           ├── retirements
    │   │           │   ├── controller
    │   │           │   │   └── RetirementController.java
    │   │           │   ├── dto
    │   │           │   │   ├── RetirementTestRequestDto.java
    │   │           │   │   └── RetirementTestResponseDto.java
    │   │           │   ├── jpa
    │   │           │   │   ├── Gender.java
    │   │           │   │   ├── RetirementTestResult.java
    │   │           │   │   ├── RetirementTestResultRepository.java
    │   │           │   │   └── RetirementType.java
    │   │           │   └── service
    │   │           │       └── RetirementService.java
    │   │           └── user_service
    │   │               ├── controller
    │   │               │   ├── UserController.java
    │   │               │   └── UserOpenFeignController.java
    │   │               ├── dto
    │   │               │   ├── KaKaoTokenDto.java
    │   │               │   ├── KakaoUserDto.java
    │   │               │   ├── LoginDto.java
    │   │               │   ├── LoginResponseDto.java
    │   │               │   ├── SignupUserDto.java
    │   │               │   ├── UserAgeTestScoreDto.java
    │   │               │   ├── UserInfoDto.java
    │   │               │   └── UserUpdateRequestDto.java
    │   │               ├── jpa
    │   │               │   ├── User.java
    │   │               │   └── UserRepository.java
    │   │               └── service
    │   │                   ├── UserMessageService.java
    │   │                   └── UserService.java
    │   └── resources
    │       ├── application-db.properties
    │       ├── application-mq.properties
    │       ├── application.properties
    │       └── env.properties
    └── test
        └── java
            └── com
                └── pda
                    └── user_service
                        └── UserServiceApplicationTests.java

```

</details>

#### 5. utils

공통적으로 사용될 수 있는 유틸리티 함수들과 예외 처리, 보안, 메시징 등의 기능을 제공합니다.

- `api_utils` : 공통 리스폰스, 문자열 변환 등의 API 호출에 사용되는 유틸리티 클래스들이 포함되어 있습니다.
- `exception` : 예외 처리 관련 클래스들이 포함되어 있습니다.
- `rabbitmq`: RabbitMQ 메시징을 위한 설정 및 서비스 클래스들이 포함되어 있습니다.
- `security` : JWT 인증 및 Security 설정 클래스가 포함되어 있습니다.

<details>
<summary> 구조도 </summary>
<div markdown="1">
  
```
utils
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── pda
    │   │           └── utils
    │   │               ├── UtilsApplication.java
    │   │               ├── api_utils
    │   │               │   ├── ApiUtils.java
    │   │               │   ├── CustomNumberUtils.java
    │   │               │   ├── CustomStringUtils.java
    │   │               │   └── StringListConverter.java
    │   │               ├── exception
    │   │               │   ├── DuplicatedEmailException.java
    │   │               │   ├── GlobalExceptionHandler.java
    │   │               │   ├── InvalidParameterException.java
    │   │               │   ├── investment_tests
    │   │               │   │   ├── AnswerNotFoundException.java
    │   │               │   │   ├── QuestionNotFoundException.java
    │   │               │   │   └── UserAnswerNotFoundException.java
    │   │               │   ├── login
    │   │               │   │   ├── NotCorrectPasswordException.java
    │   │               │   │   └── NotFoundUserException.java
    │   │               │   └── sms
    │   │               │       └── SmsCertificationException.java
    │   │               ├── rabbitmq
    │   │               │   ├── config
    │   │               │   │   └── RabbitMQConfig.java
    │   │               │   ├── dto
    │   │               │   │   └── NotificationDto.java
    │   │               │   └── service
    │   │               │       └── MessageService.java
    │   │               └── security
    │   │                   ├── JwtAuthenticationFilter.java
    │   │                   ├── JwtTokenProvider.java
    │   │                   ├── WebSecurityConfig.java
    │   │                   ├── dto
    │   │                   │   └── UserDetailsDto.java
    │   │                   ├── openfeign
    │   │                   │   └── AuthClient.java
    │   │                   └── service
    │   │                       └── CustomUserDetailsService.java
    │   └── resources
    │       ├── application-mq.properties
    │       ├── application.properties
    │       └── env.properties
    └── test
        └── java
            └── com
                └── pda
                    └── utils
                        ├── UtilsApplicationTests.java
                        └── api_utils
                            ├── CustomNumberUtilsTest.java
                            └── CustomStringUtilsTest.java
```

</details>
 
### ERD

- 서비스
<img src="https://github.com/user-attachments/assets/9489b808-ff81-49fa-bc32-a39bd9619b25" width="70%">

- 마이데이터
<img src="https://github.com/user-attachments/assets/f4bb43ab-cab4-41ae-8b6f-d47c36734cd6" width="70%">

