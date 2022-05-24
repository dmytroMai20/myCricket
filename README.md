# myCricket

## How to run
- Download and unzip code
- Open folder in IDE
- (Using IntelliJ) To download dependencies, right click `pom.xml` -> Maven -> Download sources.
- Run `src/main/java/uk.ac.cam.group09/mycricket/CricketApp.java`

## Libraries used
- JavaFX (& SceneBuilder)

## Roles and tasks
### Backend: 
 - Weather handling (weather api, weather class, weather condition)
 - Map handling (google api) - mostly done. Using leaflet js rather than google api.
 - Match and location and match handler.
### Frontend:
 - homescreen/watchscreen containing all the matches
 - display data by creating new match and then accessing weatherconditions
 - favorites screen containing favorite locations
 - display all the data about locations
 - Pages for adding matches/locations. Leave a blank StackPane in javafx to 
   allow for the map and search bar to be added. Matches need an 
   input to enter time, locations just need the stackpane box. Both need to let the user enter a custom name for the match/location.

## Note:
 - the font can be downloaded [here](https://developer.apple.com/fonts/)
