// Original array of days
const days = [
  "Sunday ",
  " Monday ",
  " Tuesday",
  "Wednesday ",
  " Thursday ",
  " Friday",
  "Saturday ",
];

// Convert to trimmed, abbreviated, uppercase array
const abbreviatedDays = days.map((day) =>
  day.trim().substring(0, 3).toUpperCase()
);

// Output the result
console.log(abbreviatedDays);
