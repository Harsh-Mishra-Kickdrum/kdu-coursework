// controllers/postController.js

const Post = require("../models/post");
const paginate = require("../utils/paginations");

// Hardcoded posts
const posts = [
  {
    id: 1,
    title: "My First Post",
    content: "This is the content of my first post.",
    images: [],
    videos: [],
    created_at: Date.now(),
  },
  // Add more posts here
];

const createPost = async (req, res) => {
  const newPost = new Post(req.body); // Create a new post instance
  posts.push(newPost); // Add to the hardcoded posts array
  res.status(201).json(newPost); // Return the created post
};

const getPosts = async (req, res) => {
  const pageNumber = parseInt(req.query.pageNumber || 1);
  const paginatedPosts = paginate(pageNumber, posts);
  res.json(paginatedPosts);
};

const getPostById = async (req, res) => {
  const post = posts.find((post) => post.id === parseInt(req.params.id));
  if (!post) {
    return res.status(404).json({ message: "Post not found" });
  }
  res.json(post);
};

module.exports = { createPost, getPosts, getPostById };
