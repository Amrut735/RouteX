@echo off
setlocal

set "APP_HOME=%~dp0"
set "CLASSPATH=%APP_HOME%gradle\wrapper\gradle-wrapper.jar"

if not exist "%CLASSPATH%" (
  echo Gradle wrapper JAR missing: %CLASSPATH%
  echo Run this once to bootstrap the wrapper JAR, or re-sync in Android Studio.
  exit /b 1
)

java -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
endlocal

