//Function to get all keys

function getAllKeys(obj) {
  let keys = [];
  for (let key in obj) {
    keys.push(key);
    if (
      //For the condition of nested objects
      typeof obj[key] === "object" &&
      !Array.isArray(obj[key]) &&
      obj[key] !== null
    ) {
      keys = keys.concat(getAllKeys(obj[key]));
    }
  }
  return keys;
}

//Function to get all values
function getAllValues(obj) {
  let values = [];
  for (let key in obj) {
    if (
      //For the condition of nested objects
      typeof obj[key] === "object" &&
      !Array.isArray(obj[key]) &&
      obj[key] !== null
    ) {
      values = values.concat(getAllValues(obj[key]));
    } else {
      values.push(obj[key]);
    }
  }
  return values;
}




const player = {
  firstName: "Leo",
  lastName: "Messi",
  address: {
    country: "Spain",
    city: "Barcelona",
  },
  careerInfo: {
    fcBarcelona: {
      appearances: 780,
      goals: {
        premierLeagueGoals: 590,
        championsLeagueGoals: 50,
      },
    },
  },
};

console.log("All Keys:", getAllKeys(player));
console.log("All Values:", getAllValues(player));

