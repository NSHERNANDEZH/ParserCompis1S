@echo off
title AvengerScript IDE
set MVN="%~dp0mvnw.cmd"
set ROOT=%~dp0
set POM=%ROOT%proyecto1\ide\pom.xml
set JAR=%ROOT%proyecto1\ide\target\avenger-ide-1.0-SNAPSHOT.jar

echo.
echo  ============================================
echo   AvengerScript IDE
echo  ============================================
echo.

REM Verificar Java
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo  ERROR: Java no encontrado. Instala Java 11 o mayor.
    echo  Descarga: https://adoptium.net
    pause
    exit /b 1
)

REM Liberar puerto 8080 si hay una instancia anterior corriendo
for /f "tokens=5" %%a in ('netstat -aon 2^>nul ^| findstr ":8080 "') do (
    taskkill /F /PID %%a >nul 2>&1
)

echo [1/2] Compilando...
call %MVN% -f "%POM%" clean package -DskipTests --no-transfer-progress
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo  ERROR: Fallo la compilacion. Revisa los errores arriba.
    pause
    exit /b 1
)

echo.
echo [2/2] Arrancando servidor...
echo  ============================================
echo  El navegador se abrira automaticamente.
echo  Cierra esta ventana para apagar el servidor.
echo  ============================================
echo.
java -jar "%JAR%"

echo.
echo  Servidor detenido.
pause
