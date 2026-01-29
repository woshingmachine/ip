@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder (includes all subdirectories)
javac -Xlint:none -d ..\bin ..\src\main\java\twinbot\*.java ..\src\main\java\twinbot\task\*.java ..\src\main\java\twinbot\storage\*.java ..\src\main\java\twinbot\ui\*.java ..\src\main\java\twinbot\parser\*.java ..\src\main\java\twinbot\command\*.java ..\src\main\java\twinbot\exception\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin twinbot.TwinBot < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
