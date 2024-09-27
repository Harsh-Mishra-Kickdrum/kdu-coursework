import React from 'react';
import logo from './logo.svg';
import '../Style/App.css';
import { ProfileData } from '../Component/ProfileData';
import { Skills } from '../Component/Skills';
import { Hobbies } from '../Component/Hobbies';



function App() {
  const apiData = {
    name: "Amey",
    fullName: "Amey Aditya",
    qualification: "SSE",
    skills: [
      { id: 1, skill: "Python" },
      { id: 2, skill: "React" },
    ],
    hobbies: [{ id: 1, hobby: "Cricket" }],
  };

  return (
    <div className="profile-container">
      <ProfileData data={apiData} />
      <div className="skills-and-hobbies">
        <Skills skills={apiData.skills} />
        <Hobbies hobbies={apiData.hobbies} />
      </div>
    </div>
  );
}

export default App;

