/**
 * Fetches data from the specified URL.
 *
 * @param {string} url - The URL to fetch data from
 * @return {Promise<any>} A promise that resolves with the fetched data
 */

export async function fetchData(url: string): Promise<any> {
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`Failed to fetch data: ${response.statusText}`);
  }
  return await response.json();
}
