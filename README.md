# TalkAI

TalkAI is a simple sample Android application built with Kotlin, using MVVM architecture along with AI integrations like Gemini and ChatGPT. The app may later include English learning functionality.

## Demo

[TalkAI - Demo](https://www.youtube.com/)

## Technologies Used

- **Kotlin**: The programming language used for building the Android application.
- **MVVM Architecture**: A design pattern for separating concerns, making the app easier to maintain and test.
- **LiveData**: A lifecycle-aware data holder for observing changes in the UI.
- **ViewModel**: Stores UI-related data and survives configuration changes.
- **Retrofit 2**: A type-safe HTTP client for Android and Java for making API calls.
- **OkHttp 4**: A networking library for handling HTTP requests and responses.
- **Repository Pattern**: A design pattern that acts as an intermediary between the data source and the application. It abstracts the data access logic and provides a clean API for data operations, promoting separation of concerns and enhancing code maintainability.
- **Factory Pattern**: A creational design pattern used to create objects without specifying the exact class of object that will be created. It promotes loose coupling and enhances code maintainability.
  
## Features

- Uses the Gemini library for AI-related tasks.
- Integrates ChatGPT by making API calls through Retrofit 2 to process user input and generate responses.
