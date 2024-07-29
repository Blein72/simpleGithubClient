# simpleGithubClient

## Description
Adnroid app that allow to get github users list and view detail info on each user.

## Arhitecture
Simple architecture with MVVM pattern without domain layer(project is too simple at this moment).

##Libraries
Retrofit
Coil
Hilt
Compose

Testing
Mockk
JUnit4

## Future possible improvements
1) Introduce domain layer(use cases)
2) Add a object conversion from API classes to project specific classes(At the moment API data classes are used)
3) Add pagination for user list
4) Add getting user list with search parameter
5) Improve error handling with a predivined errors

Other idea to explore: is to be able to login with your personal accoun and add a limted functonality to add your repositorys or pull request.
Viewing and commenting to your repos and issues and PRs can be a good start for a new feature.
