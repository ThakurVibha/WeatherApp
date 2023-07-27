# Weather App

## IMPORTANT

1. Clone the repository:
   
2. Open the project in Android Studio.

3. Build and run the app on an Android emulator or physical device.

4.Add your api Key.
   
This Weather App is an Android Kotlin demo application that showcases the implementation of MVVM architecture, Room database, Retrofit, Databinding, Kotlin Coroutines, and REST API to create a simple yet effective weather application. 

## Features

- Display current weather information for a given location.
- Fetch weather data from a remote REST API using Retrofit.
- Store and cache weather data locally using Room database.
- Utilize Kotlin Coroutines for asynchronous operations.
- Implement MVVM architecture for a separation of concerns and testability.
- Utilize Data Binding for a seamless UI integration with data sources.
- Allow users to search for weather information.

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



