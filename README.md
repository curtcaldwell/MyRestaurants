# ZipBites


### Overview
ZipBites is an Android application to help users find nearby restaurants using a zip code through the OpenTable API.


ZipBites includes these features:

  - Displays selected restaurant on Google Maps
  - Provides driving directions through Google Maps
  - Gives the user the availablity to make reservations
  - Displays phone number, address, price range and menu options

### Version
2.2.0

### Tech

ZipBites uses a number of open source projects to work properly:

* [Retrofit 2.0] - A type-safe HTTP client for Android and Java
* [OKHttp] - Request/response API


And of course ZipBites itself is open source with a [public repository](https://github.com/curtcaldwell/ZipBites)
 on GitHub.

### Installation

```sh
$ git clone [git-repo-url] ZipBites
```
Open in Android studio

### Development

Want to contribute? Great!

Add your Google Maps api key and OpenTable api key to the gradle.properties file. You need the following key:

{ProjectDirectory}/gradle.properties
CLIENT_ID="{your key}"

### Todos

 - Write Tests
 - Add a favorites list
 - Add a search bar
 
 
  
### Privacy Policy

  - Any information provided by a user, collected about a user, and collected about a user’s use of the app or device is not stored or kept for any purpose, and is only aquired to provide a personalized experience within the app (ie. displaying your name and e-mail address)


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [OkHttp]: http://square.github.io/okhttp/
   [Retrofit 2.0]: http://square.github.io/retrofit/
   [Dagger2]: https://github.com/google/dagger
   [Butter Knife]: http://jakewharton.github.io/butterknife/
