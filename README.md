# TheMet
An android application that communicates with third-party APIs and generates a quiz based on museum artifacts.

### Description
This app displays a list of departments with images from the Met Museum and asks the user to select one department to be quizzed on. Once the user has selected the department, the application loads the quiz questions. The user has 5 quiz questions to answer. After the quiz is complete the user can see their score and navigate back to the home page to select a different department and start a new quiz round. At any point in the quiz, the user can
navigate back to the home page by clicking the “Home” button.

# Application Workflow
Page 1: Splash Screen <br>
<img width="92" alt="1" src="https://user-images.githubusercontent.com/41322681/152621051-0dab4a0c-45eb-4f6f-a9fa-55695a582169.png">

Page 2: Home Page <br>
This scrollable page shows the different departments that the user can be quizzed on. The user needs to click on one of the departments to start the quiz. <br>
<img width="115" alt="1" src="https://user-images.githubusercontent.com/41322681/152621121-743d86d9-60fc-478a-a6bf-ce8a8e9c6e59.png">

Page 3: Loading Screen <br>
Here the app is fetching data from the 3rd party API. Hence it takes a few seconds for the user to navigate to the quiz page. <br>
<img width="102" alt="1" src="https://user-images.githubusercontent.com/41322681/152621272-5c583fcb-d735-473c-add1-50f7653adfaf.png">

Page 4: Quiz Page <br>
The user can see the picture of the art piece. They have to select one of the options and then they will be able to navigate to the next question by clicking on the “Submit” button. The user can also use the “Home” Button to navigate back to the Home page and select another department. <br>
<img width="102" alt="1" src="https://user-images.githubusercontent.com/41322681/152621324-fc85b6bf-b1ee-4b45-8e89-b1aa3a848cf7.png">

Page 5: Score Board <br>
This screen displays the user’s score as a Pie Chart. It also provides a button to the user to navigate back to the home page. <br>
<img width="93" alt="1" src="https://user-images.githubusercontent.com/41322681/152621366-9ca128d0-e0a6-402e-b5a4-65adeccd8ddd.png">
