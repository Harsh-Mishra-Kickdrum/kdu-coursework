import { APIResponse } from "./quotes.type";


/**
 * Fetches a random quote from the quotable.io API.
 *
 * @return {Promise<APIResponse>} The fetched random quote.
 */

export async function fetchRandomQuote(): Promise<APIResponse> {
  try {
    const response = await fetch("https://api.quotable.io/quotes/random");
    const data = await response.json();
    return data[0] as APIResponse;
  } catch (error) {
    console.error(new Error("Failed to fetch quote"));
    throw new Error("Failed to fetch quote");
  }
}

/**
 * Asynchronously fetches quotes from an external API.
 *
 * @param {number} limit - The maximum number of quotes to fetch
 * @return {Promise<APIResponse[]>} A promise that resolves to an array of APIResponse objects
 */

export async function fetchQuotes(limit = 3): Promise<APIResponse[]> {
  try {
    const response = await fetch(
      `https://api.quotable.io/quotes/random?limit=${limit}`
    );
    const data = await response.json();
    return data as APIResponse[];
  } catch (error) {
    console.error(new Error("Failed to fetch quotes"));
    throw new Error("Failed to fetch quotes");
  }
}
