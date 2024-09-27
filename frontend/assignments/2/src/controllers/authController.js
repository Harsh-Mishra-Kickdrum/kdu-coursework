// controllers/authController.js

const User = require("../models/user");

const login = async (req, res) => {
  const { email, password } = req.body; // Extract credentials from request body

  const user = await User.findOne({ user_email_id: email });

  if (!user) {
    return res.status(401).json({ message: "Invalid credentials" });
  }

  if (user.password !== password) {
    return res.status(401).json({ message: "Invalid credentials" });
  }

  // Successful login
  res.json({ message: "Login successful", user: user }); // Return user data
};

// Simple hardcoded users (replace with your actual users)
const defaultUsers = [
  {
    id: 1,
    user_name: "Nitish Gupta",
    user_email_id: "nitish@kdu.com",
    password: "123",
    profile_url: "",
  },
  {
    id: 1,
    user_name: "Harsh Mishra",
    user_email_id: "harsh@kdu.com",
    password: "456",
    profile_url: "",
  },
  {
    id: 1,
    user_name: "Narendra Modi",
    user_email_id: "modi@gov.com",
    password: "modi",
    profile_url: "",
  },
  {
    id: 1,
    user_name: "Mr.Bean",
    user_email_id: "bean@pogo.com",
    password: "teddy",
    profile_url: "",
  },
];

// Define a simple findOne method for hardcoded users
const findOne = async (query) => {
  return defaultUsers.find((user) => {
    // Customize matching logic for your user model
    return user.user_email_id === query.user_email_id;
  });
};

// Assign the findOne method to the User model
User.findOne = findOne;

const loginStatus = (req, res) => {
  const isLoggedIn = req.user.isLoggedIn();
  res.json({ loggedIn: isLoggedIn });
};

module.exports = { login, loginStatus };
