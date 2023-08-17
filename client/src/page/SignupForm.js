// SignupForm.js
import React, { useState } from "react";
import "./SignupForm.css";

function validate(data) {
  let errors = {};

  checkUsernameExists(data.username).then((isExists) => {
    if (isExists) {
      alert("이미 사용중인 유저네임입니다. 다른 유저네임을 선택해주세요.");
      return;
    }
  });

  if (!data.username) errors.username = alert("닉네임을 입력해주세요");
  else if (!data.email) errors.email = alert("이메일주소를 입력해주세요");
  else if (!data.password) errors.password = alert("비밀번호를 입력해주세요");
  // 다른 유효성 검사 조건도 추가 가능

  return errors;
}

function checkUsernameExists(username) {
  return fetch("/api/check-username?username=" + username)
    .then((response) => response.json())
    .then((data) => data.exists); // 예상 응답 포맷: { exists: true/false }
}

function submitToServer(data) {
  fetch("https://67b0-61-101-53-142.ngrok-free.app/members/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((res) => {
      if (res.status === 200) {
        console.log(res.json());
        alert("회원가입 성공!");

        window.location.href = "/question";
      } else {
        alert("오류 발생: " + res.statusText);
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}

function SignupForm() {
  const initialFormData = {
    username: "",
    email: "",
    password: "",
  };

  const [formData, setFormData] = useState(initialFormData);
  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validationErrors = validate(formData);
    if (Object.keys(validationErrors).length === 0) {
      // 서버에 요청하는 함수 호출
      submitToServer(formData);
    } else {
      setErrors(validationErrors);
    }
    setFormData(initialFormData);
  };

  return (
    <div className="container">
      <h1>회원가입</h1>
      <form onSubmit={handleSubmit}>
        <div className="username-container">
          <span>Username</span>
          <input
            type="text"
            name="username"
            placeholder="Username"
            value={formData.username}
            onChange={handleChange}
          />
          {errors.username && <p>{errors.username}</p>}
        </div>
        <div className="email-container">
          <span>Email</span>
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
          />
          {errors.email && <p>{errors.email}</p>}
        </div>
        <div className="password-container">
          <span className="password">Password</span>
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
          />
        </div>
        <div>
          {errors.password && <p>{errors.password}</p>}
          <button className="signup-button" type="submit">
            회원가입
          </button>
        </div>
      </form>
    </div>
  );
}

export default SignupForm;
