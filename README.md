# MVVM Clean Code Weather App

![Weather App](weather_app_banner.jpg)

Weather App is an Android Kotlin demo application that showcases the implementation of MVVM architecture, Room database, Retrofit, Databinding, Kotlin Coroutines, and REST API to create a simple yet effective weather application. The project is designed to serve as a reference for developers who want to build clean and maintainable Android applications using modern architectural patterns and libraries.

## Features

- Display current weather information for a given location.
- Fetch weather data from a remote REST API using Retrofit.
- Store and cache weather data locally using Room database for offline access.
- Utilize Kotlin Coroutines for asynchronous operations.
- Implement MVVM architecture for a separation of concerns and testability.
- Utilize Data Binding for a seamless UI integration with data sources.
- Display relevant weather icons based on the weather conditions.
- Show a list of predefined cities and allow users to search for weather information.
- Handle errors and provide appropriate feedback to the user.

## Screenshots

![Screenshot 1](screenshots/screenshot_1.png)
![Screenshot 2](screenshots/screenshot_2.png)
![Screenshot 3](screenshots/screenshot_3.png)

## Architecture

The app follows the MVVM (Model-View-ViewModel) architectural pattern, which promotes separation of concerns and makes the codebase more organized and maintainable. Here's an overview of the architecture:

- **Model**: Represents the data and business logic of the application. It includes data classes for weather information, the repository pattern to interact with the data sources, and network communication using Retrofit.
- **View**: This layer is responsible for the UI components of the app. It includes XML layout files that are data-bound with ViewModel.
- **ViewModel**: Acts as an intermediary between the Model and View layers. It fetches data from the repository and exposes it to the View via LiveData. The ViewModel also handles user interactions and triggers appropriate actions in the Model.

## Libraries Used

- Retrofit: For making API calls and network communication.
- Room: For local data caching and persistence.
- Databinding: To bind UI components with ViewModel data.
- Kotlin Coroutines: For managing asynchronous tasks.
- ViewModel and LiveData: To implement MVVM architecture.
- Gson: For parsing JSON responses from the API.

## Getting Started

1. Clone the repository:
   
2. Open the project in Android Studio.

3. Build and run the app on an Android emulator or physical device.


