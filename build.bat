@echo off
setlocal EnableDelayedExpansion

REM Navigate to the script's directory
pushd "%~dp0"

echo Building the maven project...

call mvnw.cmd clean install
if %ERRORLEVEL% NEQ 0 (
    echo Maven build failed. Exiting.
    popd
    exit /b 1
)

for /f "delims=" %%i in ('mvnw.cmd help:evaluate -Dexpression=imageTag -q -DforceStdout') do set IMAGE_TAG=%%i

echo Building Docker image with tag %IMAGE_TAG%...

docker build -t "%IMAGE_TAG%" .
if %ERRORLEVEL% NEQ 0 (
    echo Docker build failed. Exiting.
    popd
    exit /b 1
)

echo Docker image built successfully with tag %IMAGE_TAG%.

REM Return to the original directory
popd
