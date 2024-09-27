const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const User = require("./models/user"); 
const Post = require("./models/post"); 
const authController = require("./controllers/authController");
const postController = require("./controllers/postController");

const io = require("./utils/messaging");
const timeUtils = require("./utils/timeUtils");

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware to enable CORS
app.use(cors());

/**
 * Middleware to parse requests with x-www-form-urlencoded content type.
 */
app.use(bodyParser.urlencoded({ extended: true }));

/**
 * Middleware to parse requests with JSON content type.
 */
app.use(bodyParser.json());

/**
 * Route for handling user login.
 * @route POST /api/user/login
 * @param {object} req - Express request object, containing user credentials.
 * @param {object} res - Express response object, used to return the response.
 */
app.post("/api/user/login", authController.login);

/**
 * Route to check the login status of a user.
 * @route GET /api/user/login/status
 * @param {object} req - Express request object.
 * @param {object} res - Express response object, used to return the login status.
 */
app.get("/api/user/login/status", authController.loginStatus);

/**
 * Route for creating a new post.
 * @route POST /api/post
 * @param {object} req - Express request object, containing post data.
 * @param {object} res - Express response object, used to return the created post.
 */
app.post("/api/post", postController.createPost);

/**
 * Route for retrieving all posts.
 * @route GET /api/post
 * @param {object} req - Express request object.
 * @param {object} res - Express response object, used to return a list of posts.
 */
app.get("/api/post", postController.getPosts);

/**
 * Route for retrieving a single post by its ID.
 * @route GET /api/post/:id
 * @param {object} req - Express request object, containing the ID parameter.
 * @param {object} res - Express response object, used to return the requested post.
 */
app.get("/api/post/:id", postController.getPostById);

// Assuming you have a messaging/chat functionality set up with Socket.io.
/**
 * Setup for Socket.io messaging functionality, listens on a separate port.
 */
io.listen(3001); // Change port if needed based on your application's requirements.

/**
 * Starts the Express server.
 * 
 * @listens {number} PORT - The port number on which the server will listen.
 */
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
