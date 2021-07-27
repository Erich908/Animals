# Animals
Java program that takes image of a cheetah as input and counts the dots on the cheetah.

//To run the program up until the grayscale stage:
    java Animal 0 <filename_of_image>

e.g. java Animal 0 cheetah.png

//To run the program up until the noise reduction stage:
    java Animal 1 <filename_of_image>
    
e.g. java Animal 1 cheetah.png 

//To run the program up until the edge detection stage:
    java Animal 2 <filename> <epsilon>
Where epsilon(0-100) determines how sensitive the edge detection is

e.g. java Animal 2 cheetah.png 50

//To run the full program:
    java Animal 3 <filename> <epsilon> <lower_limit> <upper_limit>
Where lower_limit and upper_limit are the minimum and maximum radius of the dots the program should include in the counting

e.g. java Animal 3 cheetah.png 50 4 7
