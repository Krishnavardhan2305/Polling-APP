import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: ''
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); // 🚫 prevent reload

    try {
      await axios.post(
        "http://localhost:8080/api/users/register",
        formData
      );

      alert("Registered successfully");
      navigate("/login");

    } catch (err) {
      alert("Error registering");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Register</h2>

      <form onSubmit={handleSubmit}>
        <input
          className="form-control my-2"
          name="username"
          placeholder="Username"
          onChange={handleChange}
        />

        <input
          className="form-control my-2"
          name="email"
          placeholder="Email"
          onChange={handleChange}
        />

        <input
          className="form-control my-2"
          type="password"
          name="password"
          placeholder="Password"
          onChange={handleChange}
        />

        <button className="btn btn-success" type="submit">
          Register
        </button>
      </form>
    </div>
  );
};

export default Register;