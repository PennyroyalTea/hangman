# hangman engine for google app engine
This is a hangman realization for google app engine which does all the nasty job whereas you can set it up and personalize via HTML & CSS.

## How to install?
1. Download [Google App Engine](https://cloud.google.com/) (GAE), setup and create project. Make sure your server works properly.
2. Create package in your project. 
3. Download all the files from src folder. Put them into this package and change in every file the package name (the first line of code).
4. Update __web.xml__ in __war/WEB-INF__ to add Controller.java as a servlet to your server (following GAE rules).
5. To make the servlet work, you should have 3 .HTML files in __war__ : **hangman-newGame-servlet.html**, **hangman-continue-servlet.html** and **hangman-end-servlet.html**. You can create them on your own or just take from __war__ folder in the repository.
6. Now it is ready to work, but unconfigured. 

## How-To-Use aka basic configuration
The servlet allows you to deploy your own hangman web application without any programming, using only HTML, but nevertheless make it  personalized and stylish. So, you need to edit only 3 HTML files from the previous part of the instruction. The servlet will show this files in different stages of game. But also, to make this pages interactive, servlet will find some special words there (like %pattern) and change them by computer-generated information. Here is the list of probabilities (**REQUIRED** means that without this code the game can't be properly played) :

1) **hangman-newGame-servlet.html** will be shown when the player starts the game. It should welcome the guest and have one text-field with name="id" and one submit button so that the user can choose the id of a game he wants to join.
...pattern:
..* %seed -- will be replaced by random number. Can be useful if you want to generate random id.

2) **hangman-continue-servlet.html** will be shown when the player plays the game. It should show to user the part of the word he guessed and also give them a list of buttons to make a move.

...pattern:
..* %word [**REQUIRED**] -- will be replaced by the visible part of the word.
..* %mistakes -- will be replaced by the number corresponding to the number wrong tries.
..* %unusedButtons [**REQUIRED**] -- will be replaced by the list of submit buttons, each of them correspond to one unused letter. Every ...button has class="letter" so you can flexibly personalize them via CSS. 

3) **hangman-end-servlet.html** will be shown when the player ends the game. It should congratulate them and may provide them some information about their statistics.

...pattern:
..* %word -- will be replaced by the word player has guessed.
..* %mistakes -- will be replaced by final number of mistakes.
..* %comment  -- will be replaced by short comment, which shows the phase depending on the result. Experimental and better not to be used.

Don't forget that you can still use CSS and javascript on this pages.

## advanced configuration
You can also configure the game much deeper. Do it at your own risk. 
1) All the settings are available in __Settings.java__ file.
2) To change source of the words just recode __WordBase.java__ class. It still has to have __public static String getRandomWord()__ function which will return random word.
3) To change the page generation, work on __PageGenerator.java__
