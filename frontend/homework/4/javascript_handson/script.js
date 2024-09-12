//get the add btn
let addBtn = document.getElementById("add-button");

//attach a click listener addTodo
addBtn.addEventListener("click", addTodo);

window.addEventListener("keydown", () => {});

//define addTodo
function addTodo() {
  //get the input tag
  let inputTag = document.getElementById("todo-input");

  //get the data from the input tag
  const data = inputTag.value;

  //create the new list element
  const todoItem = document.createElement("li");

  //set the content to the data received from the input
  todoItem.textContent = data;

  //create delete button
  let deleteButton = document.createElement("button");
  deleteButton.textContent = "DELETE";
  deleteButton.style.margin = "auto 10px"; 
  deleteButton.style.color = "red"; 

  // attach a click event listener to the delete button
  deleteButton.addEventListener("click", () => {
    // remove the todo item from the list
    todoList.removeChild(todoItem);
  });

  todoItem.appendChild(deleteButton);

  //get the ul object reference
  let todoList = document.getElementById("todo-list");

  //append the li item to the ul object
  todoList.appendChild(todoItem);
}
