# Member Domain

## 회원

### 속성

- 사용자 식별자, 사용자 이름, 이메일, 비밀번호, 그리고 회원 유형이 들어간다.
    - 사용자 이름은 공백과 욕설이 들어가면 안되고, 1글자 이상, 20글자 이하여야 한다.
    - 비밀번호에 특수문자가 들어가야 하고, 8글자 이상 30글자 이하여야 한다.
    - 이메일은 이메일 형식에 맞아야 하고, 도메인은 `gmail`, `naver`, `daum`만 가능하다.
    - 회원 유형은 인증 유무에 따라 변하며, 지원자(`APPLICANT`), 회원(`MEMBER`) 중 하나의 정보를 가진다.

### 행위

- 회원가입을 하기 위해 이메일과 지원자 이름, 그리고 비밀번호를 가지고 회원가입을 요청한다.
    - 회원(`MEMBER`)과 이메일 중복이 되어서는 안된다.
    - 지원자(`APPLICANT`)가 재지원하게 된다면, 이전 정보를 삭제하고 현재 지원자의 정보를 저장한다.
    - 지원자(`APPLICANT`)의 정보를 저장한다.
    - 인증을 요청한다.
- 지원자 이메일 정보를 가져와 지원자의 회원 가입을 허락한다.
    - 지원자(`APPLICANT`)만 회원 가입을 허락할 수 있다.
    - 회원(`MEMBER`)으로 변경한다.
- 회원의 이름 정보를 가져와 회원 정보 수정한다.
    - 사용자 이름 정보를 변경하고, 사용자 이름 정책에 맞아야 한다.
- 회원은 현재 비밀번호의 정보와 새 비밀번호 정보를 가져와 비밀번호 변경한다.
    - 회원만 비밀번호를 수정할 수 있다.
    - 현재 비밀번호가 맞아야 한다.
    - 새 비밀번호는 비밀번호의 정책에 맞아야 한다.
- 회원은 본인 인증이 완료되면 회원 탈퇴를 진행한다.
    - 회원만 회원 탈퇴를 진행할 수 있다.
    - 사용자 회원 탈퇴 정책인 본인 인증 완료가 되어야 한다.

## 인증

### 속성

- 인증은 인증코드 식별자, 인증하려는 지원자의 식별자, 인증 토큰의 정보를 가진다.
  - 인증 안에 정보가 저장되어 있으면 지원자이거나 회원일 경우이다.

### 행위

- 지원자의 식별자를 가져와 인증을 요청한다.
    - 인증 안에 정보가 저장된 사용자 중, 지원자인 경우 갱신하고, 그 외 사용자인 경우 `NotAllowedException` 예외가 발생한다.
    - 인증 안에 정보가 저장되어 있지 않다면 새로 생성한다.
    - 알림 정책을 통해 인증 코드 알림을 보낸다.
- 지원자의 인증 코드를 받아 인증한다.
    - 인증 토큰를 식별해 이메일을 반환받는다.
    - 해당 이메일을 사용하는 지원자의 회원 가입을 허락한다.

## 인증 토큰

### 속성

- 인증 토큰은 인증 토큰을 가진다. 
  - 인증 토큰을 통해해당 지원자의 이메일 정보와 유효 시각 정보를 가진다.

### 행위

- 지원자의 이메일 정보를 가져와 인증 토큰을 생성한다.
  - 지원자의 이메일 정보와 현재 시각에서 30분이 지난 시각을 가지고 인증 토큰을 생성한다.
- 인증 코드를 가져와 이메일 식별한다.
  - 인증 코드를 받아 이메일을 식별한다.
- 인증 코드가 유효한지 식별한다.
  - 유효 시각을 지났다면 `InvalidTokenException` 예외가 발생한다.

## 알림

### 행위

- 인증 토큰을 받아 이메일을 보낸다.
    - 성공적으로 보내지 못하면 `EmailTransferException` 예외가 발생한다.
