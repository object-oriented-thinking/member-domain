# member-domain

## 미션 결과물 올리는 방법

### 브랜치 만들기

1. `checkout -b`를 이용해 새로운 branch를 만듦과 동시에 해당 브랜치로 이동합니다.
    ```shell
    git checkout -b {자신의 GIT ID}
    ```
2. 방금 만든 브랜치를 원격 저장소에 push 합니다.
    ```shell
    git push origin {자신의 GIT ID}
    ```
3. 실제로 미션을 수행할 브랜치를 만듭니다.(**`branch`가 `{자신의 GIT ID}`로 checkout 된 상태에서 생성해주세요!**)
    ```shell
    git checkout -b {자신의 GIT ID}_Done
    ```
4. 실제 작업용 브랜치를 push 합니다.
    ```shell
    git push origin {자신의 GIT ID}_Done
    ```
5. 작업용 브랜치에서 미션을 수행한 후, `commit`과 `push`를 합니다.
6. 미션 구현이 완료되었다면 Pull Request를 생성합니다.
    - Pull Request 시 제목은 `[n주차 미션] git 이름` 으로 작성해 주세요!
    - `base`는 `{자신의 GIT ID}`로 설정해주세요.(**base를 master로 하면 안됩니다.**)
    - `compare`는 `{자신의 GIT ID}_Done`입니다.

   ```
    base : this-is-spear ← compare : this-is-spear_Done
   ```

### Pull Request 생성하기
Pull Request에서의 글은 구현한 기능에 대해 설명과 어던 의도로 설계했는지 작성해주세요 ☺

> Pull Request 시 제목은 `git 이름` 으로 작성해 주세요!

### 다른 팀원들의 PR을 살피고 리뷰 남기기

미션의 결과물을 올렸다면, 나보다 먼저 PR을 남긴 스터디원들의 코드를 리뷰해주세요!
(칭찬도 좋습니다. ☺️)

