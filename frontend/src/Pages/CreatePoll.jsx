import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const CreatePoll = () => {
  const [question, setQuestion] = useState('');
  const [options, setOptions] = useState([
    { text: '' },
    { text: '' }
  ]);

  const navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  const handleOptionChange = (index, value) => {
    const updatedOptions = [...options];
    updatedOptions[index].text = value;
    setOptions(updatedOptions);
  };

  const addOption = () => {
    setOptions([...options, { text: '' }]);
  };

  const removeOption = (index) => {
    const updatedOptions = options.filter((_, i) => i !== index);
    setOptions(updatedOptions);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!userId) {
      alert("Please login first");
      return;
    }

    if (!question.trim()) {
      alert("Poll question is required");
      return;
    }

    for (let option of options) {
      if (!option.text.trim()) {
        alert("All options are required");
        return;
      }
    }

    try {
      const response = await axios.post(
        `http://localhost:8080/api/polls/create/${userId}`,
        {
          question,
          options
        }
      );

      console.log("Poll created:", response.data);
      alert("Poll created successfully");
      navigate("/");
    } catch (err) {
      console.log("Create poll error:", err);
      console.log("Backend response:", err.response?.data);
      alert("Error creating poll");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Create Poll</h2>

      <form onSubmit={handleSubmit}>
        <input
          className="form-control my-2"
          placeholder="Enter poll question"
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
        />

        {options.map((option, index) => (
          <div key={index} className="d-flex my-2">
            <input
              className="form-control"
              placeholder={`Option ${index + 1}`}
              value={option.text}
              onChange={(e) => handleOptionChange(index, e.target.value)}
            />

            {options.length > 2 && (
              <button
                type="button"
                className="btn btn-danger ms-2"
                onClick={() => removeOption(index)}
              >
                X
              </button>
            )}
          </div>
        ))}

        <button
          type="button"
          className="btn btn-secondary my-2"
          onClick={addOption}
        >
          Add Option
        </button>

        <br />

        <button className="btn btn-success mt-3" type="submit">
          Create Poll
        </button>
      </form>
    </div>
  );
};

export default CreatePoll;