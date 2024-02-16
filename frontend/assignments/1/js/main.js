const FAVORITE_ICON_SVG = `
<svg
  class="favorite-icon"
  xmlns="http://www.w3.org/2000/svg"
  viewBox="0 0 24 24"
  aria-hidden="true"
>
  <path fill="#808080"></path>
</svg>
`;

const HEART_SVG = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" aria-hidden="true"><path fill="#ff657e" d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></svg>`;

const userProfileIcon = document.querySelector(".user-profile-icon");
const newTweetInput = document.querySelector("#new-tweet-input");
const tweetStream = document.querySelector("#tweet-stream");

window.addEventListener("resize", adjustNavigationVisibility);

const tweetSubmitButton = document.querySelector("#tweet-submit");

tweetSubmitButton.addEventListener("click", handleTweetPost);

userProfileIcon.addEventListener("click", toggleNavigationVisibility);

newTweetInput.addEventListener("input", updateSubmitButtonState);

function adjustNavigationVisibility() {
  const navSection = document.querySelector(".nav-section");
  navSection.style.display = window.innerWidth > 570 ? "block" : "none";
}

function handleTweetPost() {
  const tweetContent = newTweetInput.value;
  if (tweetContent) {
    insertNewTweet(tweetContent);
  }
}

function toggleNavigationVisibility() {
  const navSection = document.querySelector(".nav-section");
  navSection.style.display =
    navSection.style.display === "none" ? "block" : "none";
}

function updateSubmitButtonState() {
  const content = newTweetInput.value.trim();
  tweetSubmitButton.disabled = content.length === 0;
  tweetSubmitButton.style.backgroundColor = content ? "#1DA1F2" : "#AAB8C2";
}

function insertNewTweet(content) {
  const tweetElement = document.createElement("div");
  tweetElement.classList.add("tweet");

  const userAvatar = document.createElement("img");
  userAvatar.classList.add("tweet-avatar");
  userAvatar.src = "./assets/avatar-placeholder.png";
  userAvatar.alt = "Avatar";

  const tweetContent = document.createElement("div");
  tweetContent.classList.add("tweet-content");

  const tweetText = document.createElement("p");
  tweetText.textContent = content;

  // Construct the tweet layout
  tweetContent.appendChild(tweetText);
  tweetElement.appendChild(userAvatar);
  tweetElement.appendChild(tweetContent);

  // Prepend the new tweet to make it appear at the top
  tweetStream.insertBefore(tweetElement, tweetStream.firstChild);

  // Reset input area
  newTweetInput.value = "";
  updateSubmitButtonState();
}
