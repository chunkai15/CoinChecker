<<<<<<< HEAD
# CoinChecker
=======

> CoinChecker APP

>>>>>>> (UPDATE V2.0)

API => https://www.coingecko.com/vi/api

- Author:
<p>Truong Xuan Khai</p>
<p>Vo Van Qua</p>
<p>Le Thi Thuy Tien</p>

## Screenshots

<p align="middle">
  <img width="200" src="https://imgur.com/a/eAb3kYg">
  <img width="200" src="https://imgur.com/a/7QWJZi6">
  <img width="200" src="https://imgur.com/a/tyxxRkm">
  <img width="200" src="https://imgur.com/a/Q23eaFz">
</p>

## Architecture
MVVM (Model - ViewModel - View) is the design pattern used for making this app. 
MVVM provides a clear separation of concern and has great support in Android SDK in the form of [Architecture Components][1].

## Libraries Used

* [Architecture][1] - A collection of libraries that help you design robust, testable, and
  maintainable apps.
  * [Data Binding][2] - Declaratively bind observable data to UI elements.
  * [LiveData][3] - Build data objects that notify views when the underlying database changes.
  * [Room][4] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][5] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
  * [Dagger][6] - For Dependeny Injection 
  * [Navigation][12] - Handle everything needed for in-app navigation.
* Third party
  * [Glide][7] for image loading
  * [Kotlin Coroutines][8] for managing background threads with simplified code and reducing needs for callbacks
  * [MPAndroidChart][9] to chart the financial data
  * [Retrofit][10] for making HTTP requests


[1]: https://developer.android.com/jetpack/arch/
[2]: https://developer.android.com/topic/libraries/data-binding/
[3]: https://developer.android.com/topic/libraries/architecture/livedata
[4]: https://developer.android.com/topic/libraries/architecture/room
[5]: https://developer.android.com/topic/libraries/architecture/viewmodel
[6]: https://developer.android.com/training/dependency-injection/dagger-android
[7]: https://bumptech.github.io/glide/
[8]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[9]: https://github.com/PhilJay/MPAndroidChart
[10]: https://github.com/square/retrofit
[11]: https://developer.android.com/training/dependency-injection/hilt-android
[12]: https://developer.android.com/topic/libraries/architecture/navigation/
