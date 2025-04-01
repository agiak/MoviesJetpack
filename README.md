# MoviesJetpack

A modern Android application showcasing movies using Jetpack Compose and following clean
architecture principles.

## Screenshots

<table>
  <tr>
    <td><img src="screenshots/home_screen.png" alt="Home Screen" width="200"/></td>
    <td><img src="screenshots/search_screen.png" alt="Search Screen" width="200"/></td>
  </tr>
</table>

## Features

- **Home Screen**: Displays popular movies and now playing movies with ratings and release dates
- **Search**: Real-time movie search functionality with a clean and responsive UI
- **Favorites**: Ability to save and manage favorite movies locally
- **Movie Details**: Comprehensive movie information including:
    - Cast information
    - Similar movies recommendations
    - Movie reviews
    - Rating and release date
- **Smooth Animations**:
    - Screen transitions
    - Bottom navigation bar animations
    - List item animations

## Architecture & Technical Details

### Project Structure

```
app/
├── core/
│   ├── data/
│   ├── domain/
│   └── presentation/
├── movies/
│   ├── data/
│   │   ├── remote/
│   │   ├── local/
│   │   └── repository/
│   ├── domain/
│   │   ├── model/
│   │   └── repository/
│   └── presentation/
│       ├── details/
│       ├── list/
│       └── search/
├── favourites/
│   ├── data/
│   ├── domain/
│   └── presentation/
└── search/
    ├── data/
    ├── domain/
    └── presentation/
```

### Technologies & Libraries

- **UI**:
    - Jetpack Compose
    - Material Design 3
    - Compose Navigation
    - Animations API

- **Architecture**:
    - Clean Architecture
    - MVVM Pattern
    - Repository Pattern
    - Use Cases
    - Dependency Injection (Hilt)

- **Concurrency**:
    - Kotlin Coroutines
    - Kotlin Flow
    - StateFlow for UI state management

- **Data**:
    - Room Database (for favorites)
    - Retrofit (for API calls)
    - Ktor Client
    - Kotlinx Serialization

- **Image Loading**:
    - Coil for Compose

### Architecture Components

1. **Presentation Layer**:
    - Composable UI components
    - ViewModels
    - UI States
    - Screen navigation

2. **Domain Layer**:
    - Use Cases
    - Domain Models
    - Repository Interfaces

3. **Data Layer**:
    - Repository Implementations
    - Remote Data Source
    - Local Data Source
    - Data Models

### Key Features Implementation

- **Navigation**: Using Compose Navigation with type-safe route handling
- **State Management**: Using StateFlow and collectAsStateWithLifecycle
- **UI Components**: Custom composables following Material Design guidelines
- **Animations**: Custom transitions and animations for smooth user experience
- **Error Handling**: Comprehensive error handling and user feedback
- **Data Caching**: Local storage for favorites and offline support

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- JDK 11 or newer
- Android SDK 21 or higher

### Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Run the app on an emulator or physical device

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

- Material Design for the UI/UX guidelines
- TMDB for the movie data API 