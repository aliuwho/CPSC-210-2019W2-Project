# 1-2-3, play with me!

This project was created and developed individually for CPSC 210 at UBC. Below are specifications for different phases of the project.
The focus of this project was to become familiar with Java AWT (most significantly, creating and utilizing a variety UI elements). 
I also implement local data persistence for different users using JSON files.

Any and all feedback is welcome and appreciated!

*Games targeting child development!*

Featuring:
- a shopping memory game (younger children)
- a creative writing game (older children)
- building (all ages!)
- and more!

#### Basic Overview

> What will the application do? 
>
This application will contain many different simple & more complex games for a user to play.

> Who will use it?

My intended audience is children in elementary school, but anybody can play.

>   Why is this project of interest to you?

I always enjoyed the idea of making games, but I wanted to create something with more utility than the average game.
This project was inspired by many of the children I've seen playing on phones or tablets; I know technology can have a
huge impact on people, and I want to improve its benefits on a developing brain.
The initial games/activities I've selected are accessible in a console program, and they also help 
introduce creativity and responsibility in children.
At the current stage, the project allows users to write & save stories, as well as care for a pet.


#### User Stories
1. As a user, I want to be remembered by name.
2. As a user, I want to be receive a writing prompt.
3. As a user, I want to be able to care for/interact with pets.
5. As a user, I want to be able to add a story to my library.
4. As a user, I want to be able to view my stories.
6. As a user, I want to be able to save my stories to file.
7. As a user, I want to be able to resume my profile where I left off.
8. As a user, I want to be able to earn points and levels.
9. As a user, I want to be able to play Connect 4.

Instructions for Grader (Phase 3)

    You can generate the first required event by entering any username and pressing submit.
    The username you choose should appear in the window.
    
    You can generate the second required event by selecting the Writing Desk.
    Add one or two stories to your library (or if you're feeling lazy, use "amy" as your username).
    You should be able to select from the stories and choose to delete or view the story you select.
    
    You can locate my visual component by heading to the Connect 4 game. As you play, chips will update their colors,
    and the arrow should move to indicate your position on the screen.
    
    You can save the state of my application by quitting and re-entering with the same username.
    You may also note that as you enter and exit the Writing Desk and Pet Room windows, your stories and 
    pets/pet states will be stoed throughout the process. 
    Also, there's a "save" button when you write a new story.
    
    You can reload the state of my application by entering a username with no existing data. (anything except "amy"
    or whatever username you have been using up till now)
    
    
Note: all images used are open source from the internet!

**PHASE 4**

Task 2:

"Test and design a class that is robust.  You must have at least one method that throws a checked exception.
  You must have one test for the case where the exception is expected and another where the exception is not expected."

My Story.java class is robust; getStoryText() and write() both throw an IO exception.
Both of these methods are thoroughly tested in StoryTest.java

Task 3:

Issue 1 - Too much coupling with the Saveable class. Refactored WritingDeskWindow,
WriteStoryWindow, ViewStoryWindow, and ConnectWindow by removing association with Saveable.
(Changes shown in attached UML for clarity)

Issue 2 - Pet and Pet subclasses were not cohesive; the subclasses lacked a purpose in the module since
all the functionality could have been refactored into a single Pet class. Now, there is a single Pet class instead of a
Pet parent class and 5 subclasses.
