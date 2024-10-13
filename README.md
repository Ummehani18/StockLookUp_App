<h1 align="center">Stock Lookup App</h1>

## Overview
The Stock Lookup App is an Android application that allows users to search for stock information, including the company name, stock price, and percentage change, by entering a stock symbol. This app uses a ViewModel to manage the business logic and separates it from the UI.

## Features
- Search for stock information by symbol.
- Display company name, stock price, and percentage change.
- Error handling for invalid or unavailable stock symbols.

## Tech Stack
- *Kotlin* for programming.
- *Android Jetpack* (ViewModel, LiveData) for state management.
- *Jetpack Compose* for UI components.
- *Retrofit* (if integrated for fetching real data) for network requests.

## Prerequisites
Before running the app, ensure you have the following set up:

1. *Android Studio* installed on your machine. You can download it from [here](https://developer.android.com/studio).
2. *Java Development Kit (JDK)* installed. JDK 11+ is recommended.
3. *Gradle* (comes pre-installed with Android Studio).

## How to Set Up the Project

### 1. Clone the Repository

First, clone the GitHub repository to your local machine:<br>
```bash
git clone https://github.com/yourusername/stock-lookup-app.git <br>
cd stock-lookup-app
```

### 2. Open the Project in Android Studio

1. *Open Android Studio*:
   - Launch Android Studio from your system.

2. *Import the Project*:
   - Click on *File* > *Open*.
   - Navigate to the folder where you cloned the repository.
```bash
   cd /path/to/your/cloned/repository</i>
```
### 3. Run the App

- In Android Studio, click on the green *Run* button (or press Shift+F10).
- Select your connected device or emulator from the list.
- The app will build and launch on the selected device/emulator.

## Dependencies
Here are the main dependencies used in this project:
```bash
dependencies {
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    implementation "androidx.compose.ui:ui:1.4.0"
    implementation "androidx.activity:activity-compose:1.5.1"
    implementation "androidx.compose.material3:material3:1.0.0"
    // Add Retrofit and Gson if fetching real data
    // implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    // implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```
## Apk file 
click here to Download
(https://drive.google.com/file/d/10ik7YRCAszT2-4S_bq3rJ0r_do1dQlQt/view?usp=sharing)
