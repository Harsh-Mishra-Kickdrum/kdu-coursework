import "./App.css";
import Navbar from "./components/Navbar/Navbar";
import Heading from "./components/Heading/Heading";
import { Button } from "./components/Button/Button.styles";
import { SetStateAction, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useSelector } from "react-redux";
import calculatePrice from "./utils/calculatePrice";
import {  SubmitAndPriceWithPrice } from "./components/SubmitAndPrice/SubmitAndPrice";

function App() {
  const roomType = useSelector((state) => state.roomType);
  const date = useSelector((state) => state.date);
  const addOns = useSelector((state) => state.addOns);

  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const price = calculatePrice(roomType, date, addOns);

  // Set Date state function
  const [setDate] = useState(() => {
    const storedDate = localStorage.getItem("date");
    return storedDate ? JSON.parse(storedDate) : new Date();
  });

  return (
    <div className="main">
      <div className="Heading">
        <Heading title="Hotel Booking"></Heading>
      </div>
      <div className="Select-Room-Type">
        <Navbar title="Select Room Type" />
        <div className="Select-Room-Type-content">
          <div>
            {" "}
            <Button title="Single Room"></Button>
          </div>
          <div>
            {" "}
            <Button title="Double Room"></Button>
          </div>
          <div>
            <Button title="Deluxe"></Button>
          </div>
          <div>
            <Button title="Presidental Suite"></Button>
          </div>
        </div>
      </div>

      <div className="Select-Date">
        <Navbar title="Select Date" />
        <div className="Select-Date-content">
          <Button>
            {" "}
            <div>
              <DatePicker
                selected={date}
                onChange={(date: SetStateAction<Date>) => {
                  setDate(date);
                  localStorage.setItem("date", JSON.stringify(date));
                }}
              />
            </div>
          </Button>
        </div>
      </div>

      <div className="Select-AddsOn">
        <Navbar title="Select additional add ons /preferences" />
        <div className="Select-AddOns-content">
          <div>
            {" "}
            <Button title="Breakfast"></Button>
          </div>
          <div>
            {" "}
            <Button title="Balcony Unit"></Button>
          </div>
          <div>
            <Button title="Deluxe"></Button>
          </div>
          <div>
            <Button title="See Facing"></Button>
          </div>
        </div>
      </div>
      <div>
        <div className="submitAndPrice">
          <SubmitAndPriceWithPrice price={price}></SubmitAndPriceWithPrice>
        </div>
      </div>
    </div>
  );
}

export default App;
