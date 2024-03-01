/**
 * Fetches data from the specified URL.
 *
 * @param {string} url - The URL to fetch data from
 * @return {Promise<any>} A promise that resolves with the fetched data
 */

import { RoomType } from "../types/roomTypes";

export async function fetchData(): Promise<RoomType> {
  const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json");
  if (!response.ok) {
    throw new Error(`Failed to fetch data: ${response.statusText}`);
  }
  return await response.json();
}
