import React, {useState, useEffect, useRef} from 'react';
import { useNavigate } from 'react-router-dom';
import './Myinfo.css';

function MyInfo() {

    const [username, setUsername] = useState('username');
    const [isEditing, setIsEditing] = useState(false);
    const inputRef = useRef(null); // Ref를 생성합니다.
    const navigate = useNavigate();

    useEffect(()=> {
        fetchUsername();
    }, []);

    const fetchUsername = () => {
        fetch("https://33d0-61-101-53-142.ngrok-free.app/members/1", {
				method: "GET", // GET, POST 등 HTTP 메서드 선택
                headers: {
                    'Content-Type': 'application/json',
                    'ngrok-skip-browser-warning': '69420',
                  },
        })
        .then((response)=> response.json())
        .then((data) => {
            setUsername(data.username);
        })
        .catch((error) => {
            console.error('Error fetching username:', error);
        });
    };

    const handleEditClick = () => {
        setIsEditing(true);
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    }


    const handleSaveClick = () => {
        fetch('https://33d0-61-101-53-142.ngrok-free.app/members/1', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'ngrok-skip-browser-warning': '69420',
            },
            body: JSON.stringify({username}),
        })
        .then((response)=>response.json())
        .then((data)=>{
            console.log('Username updated:', data);
            setIsEditing(false);
        })
        .catch((error)=> {
            console.error('Error updating username:', error);
        });
    };


    const handleEnterKey = (event) => {
        if (event.key === 'Enter') {
            handleSaveClick(); // Enter 키를 누르면 확인 버튼 클릭과 동일한 기능 실행
        }
    }

    useEffect(() => {
        if (isEditing) {
          inputRef.current.focus(); // 수정 모드일 때, 입력 필드에 포커스 설정
          inputRef.current.select(); // 수정 모드일 때, 입력 필드 내용 블록 지정
        }
      }, [isEditing]);


  return (
    <div className="myinfo-container">
        <div className='profile-container'>
      <img src="https://source.unsplash.com/1600x900/" alt="Profile" className="profile-image"/>
      </div>
      <div className="userinfo-container">
      <div className='info-container'>
          {isEditing ? ( // 수정 모드일 때
            <>
              <input className='username-change' ref={inputRef} 
                type="text"
                value={username}
                onChange={handleUsernameChange}
                onKeyDown={handleEnterKey}
              />
              <button className='profile-change' onClick={handleSaveClick}>확인</button>
            </>
          ) : (
            <>
              <div className='info-username'>{username}</div>
              <button className='profile-change' onClick={handleEditClick}>수정</button>
            </>
          )}
        </div>
        <div>소개입니다</div>
      </div>
      <div className='changepassword-container'>
      <button className='password-change' onClick={()=>{navigate('/changepw')}}>비밀번호 수정</button>
      </div>
    </div>
  );
}

export default MyInfo