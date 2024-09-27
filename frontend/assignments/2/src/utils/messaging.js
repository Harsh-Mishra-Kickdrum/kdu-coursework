const socketIo = require("socket.io");
const io = socketIo();

// Object to keep track of connected users
const users = {};

/**
 * Sets up socket.io connection event listener.
 *
 * @listens connection - Listens for new socket connections to the server.
 */
io.on("connection", (socket) => {
  console.log("New user connected", socket.id);

  /**
   * Listens for "newUser" events when a user joins.
   *
   * @listens newUser - Event indicating a new user has joined.
   * @param {string} userName - The name of the user joining.
   */
  socket.on("newUser", (userName) => {
    users[socket.id] = userName;
    // Emit updated user list to all connected clients
    io.emit("userList", Object.values(users));
  });

  /**
   * Listens for "sendChatMessage" events to receive messages from clients.
   *
   * @listens sendChatMessage - Event indicating a chat message is sent by a user.
   * @param {string} message - The chat message sent by the user.
   */
  socket.on("sendChatMessage", (message) => {
    // Emit the chat message along with the user's name to all connected clients
    io.emit("chatMessage", { user: users[socket.id], message });
  });

  /**
   * Handles socket disconnection.
   *
   * @listens disconnect - Event indicating a user has disconnected.
   */
  socket.on("disconnect", () => {
    if (users[socket.id]) {
      console.log("User disconnected", users[socket.id]);
      // Remove the disconnected user from the users object
      delete users[socket.id];
      // Emit updated user list to all connected clients
      io.emit("userList", Object.values(users));
    }
  });
});

module.exports = io;
