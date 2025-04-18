Write-Host "Starting emulator..."
Start-Process -FilePath "C:\Users\Lenovo\AppData\Local\Android\Sdk\emulator\emulator.exe" -ArgumentList "-avd Samsung_Galaxy_Tab_Active_3 -no-snapshot-load -no-window -no-audio"
Start-Sleep -Seconds 20

Write-Host "Starting Appium server..."
Start-Process -FilePath "C:\Users\Lenovo\AppData\Roaming\npm\appium.cmd"

Write-Host "Emulator and Appium server started successfully."
