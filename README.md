# HuxyMovies

HuxyMovies is a mobile application which shows the list and the details of movies which are going to be displayed in Huxy-Cinema.

#### Functionalities of the Application. 
* Retrieving the list of movies.
* Viewing the details of each movie selected.

## Getting Started.

This is how you can setup your application on your local machine.

#### Installation.

 * Ensure that you are having Android studio, then clone the project with the following command :
 
```
https://github.com/huxaiphaer/HuxyMovies.git
```
* After cloning the repository , open in inside Android studio and wait for it to load. This might take a while but you have to ensure that all the builds finish.

* After that click on the play button away from the Right-top corner of Android Studio to run the application.

#### Running Test Reports

This project uses **Jacoco** to run both Instrumented and unit tests , below is the command :

```aidl
./gradlew jacocoTestReport

```

#### Running Tests With Coverage

```aidl
 ./gradlew createDebugCoverageReport

```

To be able to view the coverage report, navigate to `app/build/reports/coverage/index.html` , and open the file with the browser to view the test coverage report of the project.


### Languages/Libraries/Architecture Used.

* Kotlin.
* MVVM (Model View View-Model)
* Paging Library.
* Xml.

### Author.

* Lutaaya Idris.