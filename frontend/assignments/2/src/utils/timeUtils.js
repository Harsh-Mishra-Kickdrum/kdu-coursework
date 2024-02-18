/**
 * Utility functions for handling and formatting time.
 * @module timeUtils
 */

const moment = require("moment");

/**
 * Formats a given time to a human-readable string, comparing it to the current time.
 * It supports showing just now, seconds ago, minutes ago, hours ago, days ago,
 * or a full date format for older dates.
 *
 * @param {Date|string|number} time - The time to format, can be a Date object, a string recognized by Moment.js, or a Unix timestamp.
 * @param {number} [threshold=10] - A threshold value not currently used but can be implemented for custom logic.
 * @param {string} [fullTimeFormat="MMM D, YYYY LT"] - The format string used for dates beyond the simple 'ago' format. Defaults to 'Month Day, Year Time'.
 * @returns {string} - A formatted string representing how long ago the time was, or a specific date if it's older.
 */
const formatTime = (
  time,
  threshold = 10, // Currently unused parameter, potential for future customization.
  fullTimeFormat = "MMM D, YYYY LT"
) => {
  const now = moment();
  const diff = moment.duration(now.diff(moment(time)));

  const seconds = diff.asSeconds(); // Calculate seconds for more granular control
  const minutes = diff.asMinutes();
  const hours = diff.asHours();
  const days = diff.asDays();

  if (seconds < 1) {
    return "Just now";
  } else if (seconds < 60) {
    return `${Math.floor(seconds)} sec ago`;
  } else if (minutes < 60) {
    return `${Math.floor(minutes)} min ago`;
  } else if (hours < 24) {
    return `${Math.floor(hours)} hr ago`;
  } else if (days < 7) {
    return `${Math.floor(days)} day${days > 1 ? "s" : ""} ago`;
  } else {
    return moment(time).format(fullTimeFormat);
  }
};

module.exports = formatTime;
