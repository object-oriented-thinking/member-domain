### 사용자

- 사용자는 이메일, 닉네임, 비밀번호로 구성된다.
    - 이메일은 이메일 양식에 맞아야 한다.
    - 이메일은 인증 가능한 이메일이여야 한다.
    - 중복 이메일 회원가입은 허용되지 않는다.
    - 닉네임은 4글자 이상 6글자 이하여야 한다.
    - 비밀번호는 4글자 이상 20글자 이하여야 한다.

### 사용자 도메인 서비스

- 이메일, 닉네임, 비밀번호를 받아 회원가입을 요청한다.
    - 입력받은 데이터는 사용자 정보의 양식에 맞아야 한다.
    - 이메일 인증 요청한다.
    - 전송된 이메일 인증은 발송된 후 30분 이내로 유효하다.
    - 이메일 인증이 완료되기 전에는 회원가입이 완료되지 않는다.
- 회원가입을 완료한다.
    - 신규 사용자 인증 여부는 false여야 한다.
    - 사용자 인증 여부를 true로 변경한다.
- 회원의 이메일은 변경할 수 없다.
- 사용자의 닉네임을 가져와 정보를 수정한다.
    - 가져온 닉네임은 4글자 이상 6글자 이하여야 한다.
    - 현재 비밀번호를 입력해야 수정할 수 있다.
- 사용자의 비밀번호를 가져와 변경한다.
    - 가져온 비밀번호는 4글자 이상 20글자 이하여야 한다.
    - 현재 비밀번호를 입력해야 변경할 수 있다.
- 사용자의 비밀번호를 가져와 회원탈퇴를 요청한다.
    - 가져온 비밀번호와 현재 비밀번호가 같아야 한다.
    - 회원탈퇴 정책에 맞아야 한다.