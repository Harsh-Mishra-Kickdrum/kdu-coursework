//converting the object to a string with email in uppercase
const inputString =
  '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';
const obj = JSON.parse(inputString);

Object.keys(obj).forEach((key) => {
  if (key !== "email" && typeof obj[key] === "string") {
    obj[key] = obj[key].toUpperCase();
  }
});

console.log("Modified Object with Uppercase Values:", obj);


//converting the object to a string without email

delete obj.email; 
const outputString = JSON.stringify(obj); 

console.log("Output String without Email:", outputString);
