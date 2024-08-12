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

<img src="https://github.com/user-attachments/assets/44519970-5991-4926-8cc5-4da2b5e2da4b">

### 패키지 구조

마이크로서비스 아키텍처(MSA)를 기반으로 하는 멀티 모듈 프로젝트입니다. <br/>
각 모듈은 독립적으로 배포될 수 있으며, 서로 다른 서비스를 제공하여 전체적인 시스템의 기능을 구현합니다. <br/>

- mydata-service
- asset-service
- user-service
- portfolio-service
- utils

의 5가지 모듈로 구성되어 있습니다.  <br/>

#### <user-service>

- `investment_test` : 투자 성향 테스트 클래스들이 포함되어있습니다.
- `notification` : 사용자에게 보낼 알림 기능 클래스들이 포함되어있습니다.
- `retirements` : 노후 준비 종합 진단 테스트 클래스들이 포함되어있습니다.
- `user_service` : 로그인 및 회원가입 등의 사용자 관리 기능 클래스들이 포함되어있습니다.

<details>
<summary> user-service </summary>
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

#### <utils>

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
![image](https://github.com/user-attachments/assets/9489b808-ff81-49fa-bc32-a39bd9619b25)


- 마이데이터
![image](https://github.com/user-attachments/assets/f4bb43ab-cab4-41ae-8b6f-d47c36734cd6)

