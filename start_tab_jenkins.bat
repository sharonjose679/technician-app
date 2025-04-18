@echo off
echo Starting emulator...
start "" "C:\Users\Lenovo\AppData\Local\Android\Sdk\emulator\emulator.exe" -avd Samsung_Galaxy_Tab_Active_3
timeout /t 20

echo Starting Appium server...
start "" "C:\Users\Lenovo\AppData\Roaming\npm\appium.cmd"

echo Emulator and Appium server started successfully.
