# Stackoverflow Clone Project
<div align="center">
  <img src="https://user-images.githubusercontent.com/97720335/234835985-4f9ad932-0653-4ebd-a937-b552dc54bdf9.png" width="70%" />
</div>

<br>

- `팀 명` : 200🆗
- `프로젝트명` : Stackoverflow Clone Project
- `프로젝트 기간` : 2023.08.04 - 2023.08.23
- `배포 링크` : [StackOverFlow](미정)

개발자들의 최대 커뮤니티, 스택 오버 플로우의 클론 페이지를 만들어보았습니다 

<br>

## 🐼 Team Member
|이재우<br>✨(BE 팀장)</br>|임다영<br>(BE)</br>|장근호<br>(BE)</br>|김민수<br>✨(FE 팀장)</br>|김종회<br>(FE)</br>|백아름<br>(FE)</br>|
|:---:|:---:|:---:|:---:|:---:|:---:|
|[@leesaewoo](https://github.com/leesaewoo)|[@Dayong-Im](https://github.com/Dayong-Im)|[@SEBBE45JGH](https://github.com/SEBBE45JGH)|[@minpppal](https://github.com/minpppal)|[@Dr-eong](https://github.com/Dr-eong)|[@bkhrm](https://github.com/bkhrm)|
|<div style="display: flex; align-items: flex-start;"><img src="https://avatars.githubusercontent.com/u/39489835?s=400&v=4" alt="icon" width="80" height="80" /></div>|<div style="display: flex; align-items: flex-start;"><img src="https://avatars.githubusercontent.com/u/83412506?v=4" alt="icon" width="80" height="80" /></div>|<div style="display: flex; align-items: flex-start;"><img src="https://avatars.githubusercontent.com/u/130126943?v=4" alt="icon" width="80" height="80" /></div>|<div style="display: flex; align-items: flex-start;"><img src="https://avatars.githubusercontent.com/u/123739304?v=4" alt="icon" width="80" height="80" /></div>|<div style="display: flex; align-items: flex-start;"><img src="https://avatars.githubusercontent.com/u/96914194?v=4" alt="icon" width="80" height="80" /></div>|<div style="display: flex; align-items: flex-start;"><img src="https://avatars.githubusercontent.com/u/126218999?v=4" alt="icon" width="80" height="80" /></div>|

<br>


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


### 커밋 컨벤션

"타입": "제목"
\n
"본문"
\n
"꼬리말"


타입 
	- fix : 오류 수정했을 때
	- feat : 새로운 기능을 추가했을 때
	- refactor : 기능의 변화가 아닌 코드 리팩터링 ex) 변수 이름 변경
	- test : 테스트 코드 추가/수정
	- chore : 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore
	- docs : 문서(주석) 수정
	- style : 스타일 수정



-------------------------------------------------------

제목
	- 제목 줄을 50자 이내로 작성한다.
	- 제목 첫 글자를 대문자로 작성한다.
	- 제목 줄을 마침표로 끝내지 않는다.
	- 제목은 명령조로 작성 ("~ 했음"❌ | "~ 수정" 🆗)

-------------------------------------------------------

본문
	- 본문과 주제를 공백 라인으로 구분.
	- 본문을 72자마다 줄 바꿈.
	- 본문은 ‘어떻게’ 보다 ‘무엇을’, ‘왜’에 맞춰 작성. 

-------------------------------------------------------

꼬리말[선택적 작성]
	- "꼬리말 타입: #이슈번호" 로 작성
	- 이슈 번호가 여러개면 쉼표로 구분한다.

	꼬리말 타입
		- Fixes : 이슈 수정중( = 아직 해결되지 않은 이슈)
		- Resolve : 이슈 해결
		- Ref : 참고할 이슈가 있을때만 작성
		- Related to : 해당 커밋과 관련된 이슈번호(아직 해결되지 않은 경우)

꼬리말 예시
	Fixes: #10  
	Related to: #8, #9
	(#10번 이슈 수정중이고 해당 이슈는 #8, #9번과 관련있는 코드이다)
