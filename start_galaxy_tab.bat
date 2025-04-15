@echo off
echo Starting emulators...

start "" cmd /k "emulator -avd Samsung_Galaxy_Tab_Active_3"
timeout /t 30

echo Starting Appium servers...

start "" cmd /k "appium --port 4723"
timeout /t 20

echo Tab emulator and Appium servers started.