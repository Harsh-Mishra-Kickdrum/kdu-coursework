const socket = io("http://localhost:8080", { transports: ["websocket"] });

const baseData = async () => {
  const response = await fetch("http://localhost:8080/stocks");
  const data = await response.json();
  return data;
};

let nameAndPrice = document.querySelector("#name_and_price");
let history = document.querySelector("#history");
let barGraph = document.querySelector("#bar_graph");

window.onload = function() {
  baseData().then((data) => {
    nameAndPrice.innerHTML = data.nameAndPrice;
    history.innerHTML = data.history;
    barGraph.innerHTML = data.barGraph;
  });
}

function addBarToXaxis(value) { 
  const bar = document.createElement("div");
  bar.classList.add("bar");
  bar.style.height = `${value}px`;
  barGraph.appendChild(bar);
}
socket.on('message', function(msg) {
  addBarToXaxis(msg);
})  

