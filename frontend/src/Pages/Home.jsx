import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Home = () => {
  const [polls, setPolls] = useState([]);

  const userId = localStorage.getItem("userId");

  // ================= FETCH POLLS =================
  const fetchPolls = () => {
    axios.get("http://localhost:8080/api/polls/getallpolls")
      .then(res => setPolls(res.data))
      .catch(err => console.log(err));
  };

  useEffect(() => {
    fetchPolls();
  }, []);

  // ================= VOTE FUNCTION =================
  const handleVote = async (optionId) => {

    const userId = localStorage.getItem("userId");

    if (!userId) {
      alert("Please login first");
      return;
    }

    try {
      await axios.post(
        `http://localhost:8080/api/polls/vote/${optionId}/${userId}`
      );

      fetchPolls();

    } catch (err) {
      alert(err.response?.data || "Already voted!");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Available Polls</h2>

      {polls.length === 0 ? (
        <p>No polls available</p>
      ) : (
        polls.map((poll) => (
          <div key={poll.id} className="card p-3 mt-3">

            {/* QUESTION */}
            <h5>{poll.question}</h5>

            {/* OPTIONS */}
            <div className="mt-2">
              {poll.options.map((option) => (
                <button
                  key={option.id}
                  className="btn btn-outline-primary d-block my-1"
                  onClick={() => handleVote(option.id)}
                >
                  {option.text} ({option.votes} votes)
                </button>
              ))}
            </div>

          </div>
        ))
      )}
    </div>
  );
};

export default Home;