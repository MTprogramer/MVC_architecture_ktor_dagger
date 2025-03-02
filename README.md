# MVC Architecture with Jetpack Compose, Ktor, and MVI-Inspired ViewModel


## MVC Work Flow
![Work flow image](images/image_1.png)

This Android project demonstrates a scalable **MVC (Model-View-Controller)** architecture with an **MVI (Model-View-Intent)**-inspired approach using **ViewModel**, **Jetpack Compose**, **Ktor**, and **Kotlin Coroutines** with **StateFlow**. It fetches posts from `https://jsonplaceholder.typicode.com/posts` and navigates to a post detail view.

## MVC Explained

### M - Model
- **Definition**: The Model represents the data, business logic, and rules, serving as the app’s data backbone.
- **Purpose**: 
  - To manage data operations (fetching, storing) independently of the UI.
  - To provide reusable data access and enable testing.
- **In This Project**:
  - **Code Files**:
    - **`data/model/Post.kt`**: Defines the `Post` data class.
    - **`data/network/ApiResult.kt`**: Sealed class for API states.
    - **`data/network/ApiService.kt`**: Fetches posts via Ktor, emitting `ApiResult` Flows.
    - **`data/repository/PostRepository.kt`**: Abstracts data access with Flows.
    - **`data/di/AppModule.kt`**: Hilt module for dependencies.
  - **Role**: Supplies raw data Flows to the Controller (ViewModel) for state processing.

### V - View
- **Definition**: The View is the UI layer, displaying data and capturing user interactions.
- **Purpose**: 
  - To render data visually for user interaction.
  - To send user inputs to the Controller without altering data directly.
- **In This Project**:
  - **Code Files**:
    - **`ui/screens/posts/PostsScreen.kt`**: Renders posts list, observes `PostsState`, sends `FetchPosts` intent.
    - **`ui/screens/details/PostDetailScreen.kt`**: Displays post details, observes `PostDetailState`.
    - **`ui/navigation/AppNavigation.kt`**: Manages navigation routes.
    - **`ui/theme/Theme.kt`**: Provides styling.
  - **Role**: Reacts to ViewModel state updates and triggers intents (e.g., navigation, retries).

### C - Controller
- **Definition**: The Controller processes user inputs (intents), updates the Model, and coordinates View updates.
- **Purpose**: 
  - To handle user actions and translate them into data operations or state changes.
  - To maintain UI state and ensure the View reflects the Model’s data.
- **In This Project**:
  - **Code Files**:
    - **`controller/PostsViewModel.kt`**: Manages `PostsState` with `StateFlow`, processes `FetchPosts` intent.
    - **`controller/PostDetailViewModel.kt`**: Manages `PostDetailState`, processes `FetchPost` intent.
  - **Role**: Uses ViewModel to process intents, fetch Model data, and emit state updates to the View via `StateFlow`.
  - **Note**: In traditional MVC, the Model updates the View directly, but here, the Controller (ViewModel) updates the View by processing Model data and emitting it through `StateFlow`, adapting to Compose’s reactive nature and ViewModel’s lifecycle awareness.


## How It Works
1. **MainActivity**: Renders `AppNavigation`.
2. **AppNavigation**: Defines routes with `NavHost`.
3. **PostsScreen**: Observes `PostsState`, sends intents to `PostsViewModel`, navigates on click.
4. **PostDetailScreen**: Observes `PostDetailState` from `PostDetailViewModel`.
5. **ViewModels**: Process intents, fetch Model data, update `StateFlow` for the View.

## Dependencies
- **Jetpack Compose**: UI with `collectAsState()`.
- **Ktor**: Networking.
- **Kotlin Coroutines**: `StateFlow`, `viewModelScope`.
- **Hilt**: DI with ViewModel support.
- **Jetpack Navigation**: Screen routing.

## Running the Project
1. **Add Internet Permission**:
   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
