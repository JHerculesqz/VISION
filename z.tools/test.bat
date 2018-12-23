echo off

echo STEP1.xcopy test
xcopy %~dp0test C:\test\ai\ /s /y

echo STEP2.xcopy opencv
xcopy %~dp0opencv-344 %SystemRoot%\opencv-344\ /s /y

pause>null