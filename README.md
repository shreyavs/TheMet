# TheMet
An android application that communicates with third-party APIs and generates a quiz based on museum artifacts.

### Description
This app displays a list of departments with images from the Met Museum and asks the user to select one department to be quizzed on. Once the user has selected the department, the application loads the quiz questions. The user has 5 quiz questions to answer. After the quiz is complete the user can see their score and navigate back to the home page to select a different department and start a new quiz round. At any point in the quiz, the user can
navigate back to the home page by clicking the “Home” button.

# Application Workflow
Page 1: Splash Screen


Page 2: Home Page 
This scrollable page shows the different departments that the user can be quizzed on. The user needs to click on one of the departments to start the quiz.


Page 3: Loading Screen
Here the app is fetching data from the 3rd party API. Hence it takes a few seconds for the user to navigate to the quiz page.


Page 4: Quiz Page
The user can see the picture of the art piece. They have to select one of the options and then they will be able to navigate to the next question by clicking on the “Submit” button. The user can also use the “Home” Button to navigate back to the Home page and select another department.


Page 5: Score Board
This screen displays the user’s score as a Pie Chart. It also provides a button to the user to navigate back to the home page.

