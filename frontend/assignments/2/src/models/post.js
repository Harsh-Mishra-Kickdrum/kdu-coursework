// models/Post.js

const Post = {
  id: "",
  title: "",
  content: "",
  images: [], // Array for multiple images (optional)
  videos: [], // Array for multiple videos (optional)
  created_at: Date.now(), // Instantaneous timestamp
};

module.exports = Post;
