# Moon Phase App
The purpose of this app is to demonstrate my knowledge for a clean and concise app following MVVM. 
Clean MVVM Architecture I like to follow for apps I build
## Technologies & Libraries Used:
  - Koin for DI (Koin is more concise and straightforward than the other DI frameworks. It is perfect for encapsulated feature/library modules.)
  - okhttp3 for making network calls
  - Retrofit (retrieve and upload JSON (or other structured data) via a REST api call)
  - Moshi (for parsing JSON into Java and Kotlin classes)
## What the app does
- Utilising the https://www.farmsense.net/api/astro-widgets/ api to get current information about the moon
- The app displays a picture of the current moon's phase (New Moon, First Quarter, Full Moon etc)
- Also displays some basic info such as distance to the moon age etc
