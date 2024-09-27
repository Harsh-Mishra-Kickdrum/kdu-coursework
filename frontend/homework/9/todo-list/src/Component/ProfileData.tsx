import "../Style/ProfileData.css";

export function ProfileData({ data }:any) {
  return (
    <div className="profile-data">
      <h1 className="name">{data.name}</h1>
      <h2 className="full-name">{data.fullName}</h2>
      <h3 className="qualification">{data.qualification}</h3>
    </div>
  );
}
