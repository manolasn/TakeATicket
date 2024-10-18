
# TAKE A TICKET

An Android application that showcases movies using data from [The Movie Database (TMDb)](https://www.themoviedb.org/), built with Jetpack Compose, Hilt for dependency injection, and Retrofit for API calls. This project was developed by **Nikiforos Manolas**.

## Features

- **Jetpack Compose** for modern UI development.
- **Hilt** for dependency injection.
- **Retrofit** for API calls to TMDb.
- **Room** for local data storage.
- **DataStore** for preferences management.
- **Glide & Coil** for image loading.
- **Lottie** animations.
- Navigation with **Navigation Compose**.
- Lifecycle-aware components.

## Libraries and Dependencies

The app uses various libraries and dependencies for different purposes:

- **AndroidX Libraries**:
  - `libs.androidx.core.ktx`
  - `libs.androidx.lifecycle.runtime.ktx.v280`
  - `libs.androidx.activity.compose.v190`
  - `libs.androidx.appcompat.resources`
  - `libs.androidx.navigation.runtime.ktx`
  - `libs.androidx.core.splashscreen`

- **Dependency Injection (Hilt)**:
  - `libs.hilt.android`
  - `libs.androidx.hilt.compiler`
  - `libs.hilt.android.compiler`
  - `libs.androidx.hilt.navigation.compose`

- **Networking**:
  - `libs.retrofit`
  - `libs.converter.gson`
  - `libs.logging.interceptor`

- **UI Components**:
  - `libs.compose`
  - `libs.ui`
  - `libs.ui.graphics`
  - `libs.ui.tooling.preview`
  - `libs.material3`
  - `libs.accompanist.systemuicontroller`
  - `libs.lottie.compose`

- **Lifecycle Components**:
  - `libs.androidx.lifecycle.runtime.compose`
  - `libs.androidx.lifecycle.viewmodel.compose`

- **Image Loading**:
  - `libs.coil.compose`

- **Database (Room)**:
  - `libs.androidx.room.runtime`
  - `libs.androidx.room.ktx`
  - `androidx.room:room-compiler:2.6.0`
  
## API

This app uses data from [The Movie Database API](https://www.themoviedb.org/documentation/api) to fetch movie details such as movie name, rating, release date, description, and more.

## Project Setup

1. Clone the repository:
   ```
   git clone git@github.com:manolasn/TakeATicket.git
   ```

2. Obtain an ACCESS_TOKEN from [TMDb](https://www.themoviedb.org/documentation/api).

3. Add your ACCESS_TOKEN in the `external.properties` file:
   ```
   access_token=your_access_token
   ```

4. Build and run the project in Android Studio.

