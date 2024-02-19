import "../Style/Skills.css";
import "../Style/Header.css";


export function Skills({ skills }:any) {
  return (
    <div className="skills-container">
      <h3 className= "skills-header"> Skills</h3>
      <ul>
        {skills.map((skill:any) => (
          <li key={skill.id}>{skill.skill}</li>
        ))}
      </ul>
    </div>
  );
}
