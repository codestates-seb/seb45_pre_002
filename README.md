## 🌱 Git
### branch
> `Pull Request` 와 `Code Review` 가 이루어진 후, 병합을 진행해야 한다.
- `main` : 배포 브랜치
- `dev` : `fe` / `be` 작업 테스트 병합 브랜치
- `fe` : Front-End 개발 브랜치
- `be` : Back-End 개발 브랜치
- `be-feat/기능 이름/#이슈번호` : BE 기능 개발 브랜치
- `fe-feat/기능 이름/#이슈번호` : FE 기능 개발 브랜치

### Git 저장소 이용법 
로컬에서 작업 후 개인 branch Push
1. `git checkout 브랜치`
2. `git add 파일/디렉토리 경로`
3. `git commit -m "message"`
4. `git push origin 브랜치"`

### Commit Message Convention

Tag | Title
-- | --
feat | 새로운 기능 추가
fix | 버그 수정
docs | 문서 수정
design | CSS 등 사용자 UI 디자인 변경
style | 코드 포맷 변경, 세미 콜록 누락, 코드 수정이 없는 경우
refactor | 코드 리팩토링
test | 테스트 추가, 테스트 리팩토링 추가 (프로덕션 코드 변경 x)
chore | 빌드 테스트 업데이트, 패키지 매니저를 설정 (프로덕션 코드 변경 x)
comment | 필요한 주석 추가 및 변경
rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우
remove | 파일을 삭제하는 작업만 수행한 경우
!BREAKING CHANGE | 커다란 API 변경
!HOTFIX | 급하게 치명적인 버그를 고쳐야하는 경우
