import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Home = () => {
  const [polls, setPolls] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/polls")
      .then(res => setPolls(res.data))
      .catch(err => console.log(err));
  }, []);

  return (
    <div className="container mt-4">
      <h2>Available Polls</h2>

      {polls.length === 0 ? (
        <p>No polls available</p>
      ) : (
        polls.map((poll) => (
          <div key={poll.id} className="card p-3 mt-3">
            <h5>{poll.question}</h5>
          </div>
        ))
      )}
    </div>
  );
};

export default Home;