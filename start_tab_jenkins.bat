@echo off
echo Starting emulator...

:: Start the emulator without opening a new interactive shell
start /b emulator -avd Samsung_Galaxy_Tab_Active_3
timeout /t 30

echo Starting Appium server...

:: Start Appium in the background without requiring interactive shell
start /b appium --port 4723
timeout /t 20

echo Emulator and Appium server started successfully.
