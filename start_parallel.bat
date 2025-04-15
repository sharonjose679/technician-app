@echo off
echo Starting emulators...

start "" cmd /k "emulator -avd Samsung_Galaxy_Tab_Active_3"
timeout /t 30

start "" cmd /k "emulator -avd Galaxy_S23_Android_14"
timeout /t 30

echo Starting Appium servers...

start "" cmd /k "appium --port 4723"
timeout /t 20

start "" cmd /k "appium --port 4725"
timeout /t 20

echo All emulators and Appium servers started.