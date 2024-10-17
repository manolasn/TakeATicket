
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

The app leverages several modern Android libraries for a robust and scalable architecture:

- **AndroidX Libraries**:
  - `androidx.core:core-ktx:1.13.1`
  - `androidx.lifecycle:lifecycle-runtime-ktx:2.8.0`
  - `androidx.activity:activity-compose:1.9.0`
  - `androidx.compose:compose-bom:2024.05.00`
  - `androidx.compose.ui:ui`
  - `androidx.compose.material3:material3`
  - `androidx.appcompat:appcompat-resources:1.6.1`
  - `androidx.navigation:navigation-runtime-ktx:2.7.7`
  - `androidx.datastore:datastore-preferences:1.1.1`

- **Dependency Injection (Hilt)**:
  - `com.google.dagger:hilt-android:2.50`
  - `androidx.hilt:hilt-navigation-compose:1.2.0`
  - `androidx.hilt:hilt-compiler:1.2.0`

- **Networking**:
  - `com.squareup.retrofit2:retrofit:2.9.0`
  - `com.squareup.retrofit2:converter-gson:2.9.0`
  - `com.squareup.okhttp3:logging-interceptor:4.5.0`

- **Image Loading**:
  - `com.github.bumptech.glide:compose:1.0.0-alpha.3`
  - `io.coil-kt:coil-compose:2.6.0`

- **Database**:
  - `androidx.room:room-runtime:2.6.0`
  - `androidx.room:room-ktx:2.6.0`

- **UI Components**:
  - `com.airbnb.android:lottie-compose:6.3.0`
  - `com.google.android.material:material:1.12.0`
  - `com.google.accompanist:accompanist-systemuicontroller:0.32.0`
  
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

