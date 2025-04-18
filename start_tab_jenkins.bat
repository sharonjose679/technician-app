@echo off
echo Starting emulator...

start /B "" emulator -avd Samsung_Galaxy_Tab_Active_3 -no-snapshot-save -no-boot-anim -no-audio
timeout /t 30

echo Starting Appium server...

start /B "" appium --port 4723 --log appium_log.txt
timeout /t 20

echo Emulator and Appium server started successfully.

