1. Project Overview
Description: An Android music streaming application built using Kotlin and Jetpack Compose that leverages the JioSaavn public API for real-time music data.
Core Features: Search functionality, paginated song results, full-screen and mini-player controls, and a persistent local queue.


2. Technical Stack 
Language: Kotlin 
UI: Jetpack Compose 
Architecture: MVVM (Model-View-ViewModel) 
State Management: StateFlow / MutableStateFlow 
Dependency Injection: Hilt 
Media Playback: Media3 ExoPlayer 
Networking: (e.g., Retrofit/OkHttp) to connect to the JioSaavn API.


3. Architecture Overview 
UI Layer: Composable functions observing StateFlow from ViewModels.
Domain/Data Layer: Repositories handling API calls and local queue persistence.
Media Service: How you implemented the Media3 Service to handle background playback when the app is minimized or the screen is locked.


4. Setup Instructions 
Clone the repository.
Open the project in Android Studio (Ladybug or newer recommended).
Sync Gradle and ensure all dependencies are downloaded.
Run the app on an emulator or physical device (API level 24+ recommended).


6. Assumptions & Trade-offs 
Audio Quality: "I chose the 160kbps/320kbps streams for the best balance between data usage and audio quality".
Error Handling: "Handled network failures gracefully by implementing retry mechanisms for the community-maintained API".
State Sync: How you ensured the Mini Player and Full Player stay perfectly in sync.


