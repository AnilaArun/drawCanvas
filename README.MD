<h1><div align="center">TEXT BASED DRAWING PROGRAM</div></h1>

This is an implementation of a simple text(ASCII) based drawing program(a simplified version of paint). Thi will allow ussers to 
   1. Create a new canvas
   2. Draw on the Canvas using text based commands
   3. Quit the program

**COMMANDS**
<table>
<tr><th>Command</th><th>Description</th></tr>
<tr><td>C w h</td><td>Create a Canvas with width w and height h</td></tr>
<tr><td>L x1 y1 x2 y2</td><td>Draw a new line from coordinates (x1, y1) to (x2, y2) horizontally or vertically. Lines are made of the x characters</td></tr>
<tr><td>R x1 y1 x2 y2</td><td>Draw a new rectangle, with upper left corner at coordinate (x1, y1) and lower right coordinate at (x2, y2). Lines are
made up of the x character</td></tr>
<tr><td>Q</td><td>Quit the program</td></tr>
</table>

NOTE: The program looks for the Commands with first character as C, L, R or Q. It expects space separated characters.
Please press enter key after entering space separated characters.

***Compile, Build and Run***

Gradle is used to compile and build the program, 
generated jar file will be created in ``build/libs folder`` and can use ``java -jar drawCanvas-1.0-SNAPSHOT.jar `` command to run the program

