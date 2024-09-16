function encodeString(str) {
  // trim the string to remove spaces from the start and end
  let trimmedStr = str.trim();

  // replace characters as per the given rules
  let codedStr = trimmedStr
    .replace(/a/g, "4")
    .replace(/e/g, "3")
    .replace(/i/g, "1")
    .replace(/o/g, "0")
    .replace(/s/g, "5");

  return codedStr;
}

// Test cases
console.log(encodeString("javascript is cool  ")); // "j4v45cr1pt 15 c00l"
console.log(encodeString("programming is fun")); // "pr0gr4mm1ng 15 fun"
console.log(encodeString("  become a coder")); // "b3c0m3 4 c0d3r"
