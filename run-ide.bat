@echo off
title AvengerScript IDE

set MVN="%~dp0mvnw.cmd"
set ROOT=%~dp0

echo.
echo  ============================================
echo   AvengerScript IDE - Iniciando servidor...
echo  ============================================
echo.

echo [1/2] Compilando proyecto...
%MVN% -f "%ROOT%pom.xml" clean install --no-transfer-progress -q
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo  ERROR: Fallo la compilacion. Revisa los errores arriba.
    pause
    exit /b 1
)

echo [2/2] Arrancando servidor en http://localhost:8080
echo.
echo  Presiona Ctrl+C para detener el servidor.
echo.
%MVN% -f "%ROOT%pom.xml" spring-boot:run -pl ide --no-transfer-progress

:cleanup
echo.
echo  Deteniendo proceso en puerto 8080...
for /f "tokens=5" %%a in ('netstat -aon 2^>nul ^| findstr ":8080 "') do (
    taskkill /F /PID %%a 2>nul
)
echo  Puerto 8080 liberado.
pause
