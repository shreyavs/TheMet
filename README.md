# QuizMe Met
An android application that communicates with third-party APIs and generates a quiz based on museum artifacts.

### Description
**Overview:**
QuizMe Met is an engaging and educational Android quiz application designed for art enthusiasts and museum-goers alike. Leveraging the rich collection of the Metropolitan Museum of Art (Met Museum), this app provides users with an interactive way to test and expand their knowledge about various art departments.

**Key Features:**
- **Department Selection:**
    - Upon launching the app, users are presented with a list of departments from the Met Museum, each accompanied by a representative image.
    - Users select a department to be quizzed on, diving into specific areas such as Ancient Art, European Paintings, Modern Art, and more.
- **Quiz Gameplay:**
    - Each quiz consists of 5 carefully curated questions related to the selected department.
    - Questions range from identifying artworks and artists to historical facts and stylistic details.
    - An interactive multiple-choice format ensures an engaging user experience.
- **Scoring and Feedback:**
    - Upon completing the quiz, users receive their score along with feedback on their performance.
    - Detailed explanations for each answer are provided to enhance learning and retention.
- **User Navigation:**
    - Users can easily navigate back to the home page at any point during the quiz by clicking the “Home” button.
    - From the home page, users can select a different department and start a new quiz round, allowing for endless learning opportunities.

**Educational Value:**
QuizMe Met serves as an excellent tool for both casual learners and serious art students, providing a fun and interactive way to explore and understand the vast collection of the Met Museum.

**Usability:**
The app's intuitive design ensures a smooth user experience, with easy navigation and clear instructions, making it accessible for users of all ages.

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
