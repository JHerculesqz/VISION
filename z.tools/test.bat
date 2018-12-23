echo off

echo STEP1.xcopy test
xcopy %~dp0test C:\test\ai\ /s /y

pause>null