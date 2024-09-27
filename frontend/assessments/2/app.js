// <!-- In this assessment, your objective is to create a real-time stock market application using -->
// <!-- HTML/SCSS frontend and integrate it with a Node.js backend server using sockets for live -->
// <!-- updates. -->

//Node Server which will handle socket io connections

const express = require("express");
const cors = require("cors");
const jsonServer = require("json-server");

const app = express();
app.use(cors());

const server = app.listen(8080, () => {
  console.log("Server listening on port 8080");
});

// Serve static files from public client folder
app.use(express.static("client"));

const io = require("socket.io")(server);
let stocks = {};

//Handle incoming connection from client
io.on("connection", (socket) => {
  console.log("Client connected");
  socket.on("disconnect", () => {
    console.log("Client disconnected");
  });
});

//mock api for sending random data for the stock as random price  and base price to the client and other details

const servers = jsonServer.create();
const middlewares = jsonServer.defaults();
const port = process.env.PORT || 8080;
servers.use(jsonServer.bodyParser);
servers.use(middlewares);
servers.listen(port, () => {
  console.log("JSON Server is running");
});


server.get("/stocks", (request, response) => {
  if (request.method === "GET") {
    const stocks = require("./stocks/stocks");
    response.status(200).jsonp(stocks());
  }
});

//send the stocks value to the frontend with interval of 5 seconds
setInterval(() => {
  io.emit("stocks", require("./stocks/stocks"));
}, 5000);



