Write-Host "Starting emulator..."
Start-Process -FilePath "C:\Users\Lenovo\AppData\Local\Android\Sdk\emulator\emulator.exe" -ArgumentList "-avd Samsung_Galaxy_Tab_Active_3 -no-snapshot-load -no-window -no-audio"

# Give initial time for emulator process to start
Start-Sleep -Seconds 10

# Wait for emulator to be detected by ADB
adb wait-for-device

# Wait for system to fully boot
Write-Host "Waiting for emulator to finish booting..."
$bootComplete = ""
while ($bootComplete -ne "1")
{
    Start-Sleep -Seconds 5
    $bootComplete = adb shell getprop sys.boot_completed | ForEach-Object { $_.Trim() }
    Write-Host "Boot complete status: $bootComplete"
}

Write-Host "Emulator booted successfully!"

Write-Host "Starting Appium server..."
Start-Process -FilePath "C:\Users\Lenovo\AppData\Roaming\npm\appium.cmd"

Write-Host "Emulator and Appium server started successfully."
