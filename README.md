# 🎬 MovieFeed
MovieFeed is a movie discovery app for Android, built using Kotlin and Jetpack Compose. It allows users to browse movies across different categories, view detailed movie information including scores, genres, release dates, and billed cast members. This project helped me further explore Android development and Jetpack Compose's declarative UI patterns, and I hope it helps you in your journey too!

## 📽️ MovieFeed - A Modern Movie Discovery Application
MovieFeed utilizes The Movie Database (TMDb) API to provide users with up-to-date movie information. It features a clean, intuitive interface built with Jetpack Compose and showcases best practices such as MVVM architecture, Hilt for dependency injection, Retrofit for network operations, and Coroutines for handling background tasks.

## 🚀 Features
Browse Movie Categories: Explore various categories like "Popular", "Top Rated", "Now Playing", and more.
Movie Details: View comprehensive details about each movie, including:
Rating: Movie scores and reviews.
Genres: Genre tags associated with each movie.
Release Date: Know when the movie was released.
Duration: Check the runtime of the movie.
Cast Information: Get the list of billed cast members along with the characters they play.
Dynamic and Responsive UI: Built with Jetpack Compose to offer a modern and smooth user experience.
##🛠️ Technologies Used
Kotlin: The primary language for building Android applications, enabling concise and expressive code.
Jetpack Compose: Android's modern UI toolkit for building native interfaces.
MVVM Architecture: Separates business logic from UI, ensuring clean, maintainable code.
Hilt: Dependency injection framework used to streamline object creation and lifecycle management.
Retrofit: For making network requests to the TMDb API.
Coroutines & Flow: Manage background operations and state efficiently.
Coil: For fast and efficient image loading (used for movie posters and cast photos).
## 🎥 Screenshots
<table style="width:100%">
   <tr> 
      <th>homescreen</th> 
      <th>Movie List</th> 
      <th>Movie Details</th> 
      <th>Cast Information</th> 
      <th>search</th> 
   </tr> 
   <tr> 
      <td>
         <img src = "ScreenShots\Screenshot_20241008_192924_Movie-feed.jpg" width=240/>
      </td>
      <td>
         <img src = "ScreenShots\Screenshot_20241008_192935_Movie-feed.jpg" width=240/>
      </td> 
      <td>
         <img src = "ScreenShots\Screenshot_20241008_193008_Movie-feed.jpg" width=240/>
      </td> 
         <img src = "ScreenShots\Screenshot_20241008_193029_Movie-feed.jpg" width=240/>
      <td> 
         <img src = "ScreenShots\Screenshot_20241008_193046_Movie-feed.jpg" width=240/>
      </td> 
   </tr> 
</table>
📝 Getting Started
To get a local copy of the project up and running, follow these steps:

1. Clone the Repository
   bash
   Copy code
   git clone https://github.com/ar1381/movie-feed.git
2. Open the Project
   Open Android Studio.
   Sync the project to download dependencies.
3. API Key Setup
   Obtain an API key from The Movie Database (TMDb).
   Add a secrets.properties file in the root directory with your API key:
   properties
   Copy code
   TMDB_API_KEY=your_api_key_here
4. Run the Project
   Build and run the project on an Android emulator or a physical device.
## ⚡ Requirements
   Android Studio (Arctic Fox or newer)
   Minimum SDK: 21 (Android 5.0 Lollipop)
   TMDb API Key
## 🧩 Project Structure
   ui: Contains composables and other UI-related logic.
   viewmodel: Manages app data and business logic using Jetpack's ViewModel.
   data: Handles data models and network requests using Retrofit.
   repository: Manages the communication between the ViewModel and data sources.
## 💡 Key Learning Points
   This project helped me deepen my understanding of Jetpack Compose, API integration, and MVVM architecture in Android development. I hope this codebase serves as a helpful resource for others looking to learn these concepts.

## 👥 Contributing
Contributions, issues, and feature requests are welcome! Feel free to check the issues page and submit pull requests.

