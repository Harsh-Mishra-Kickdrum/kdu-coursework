import { RoomType } from "../types/roomTypes";

export interface PriceCalculatorProps {
  roomType: RoomType;
  date: Date;
  addOns: AddOn[];
}

/**
 * Fetches data from the specified URL.
 */
export async function fetchData(): Promise<RoomType> {
  const response = await fetch(
    "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json"
  );
  if (!response.ok) {
    throw new Error(`Failed to fetch data: ${response.statusText}`);
  }
  return await response.json();
}

/**
 * Calculates the price based on the room type, date, and add-ons.
 */
export function calculatePrice({
  roomType,
  date,
  addOns,
}: PriceCalculatorProps): number {
  const basePrice = parseInt(roomType.costPerNight, 10);
  const addOnCosts = addOns.reduce(
    (total, addOn) => total + parseInt(addOn.cost, 10),
    0
  );

  const extraCharge = Math.floor((date.getTime() % 10000) / 10); // Adjust the range of the extra charge as needed

  return basePrice + addOnCosts + extraCharge;
}

export default calculatePrice;
