import "../Style/Hobbies.css";

export function Hobbies({ hobbies }:any) {
  return (
    <div className="hobbies-container">
      <h3 className="HobbiesHeader">Hobbies</h3>
      <ul>
        {hobbies.map((hobby:any) => (
          <li key={hobby.id}>{hobby.hobby}</li>
        ))}
      </ul>
    </div>
  );
}
