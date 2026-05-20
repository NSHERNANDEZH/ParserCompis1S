@echo off
title AvengerScript IDE

set MVN="%~dp0mvnw.cmd"
set ROOT=%~dp0

echo.
echo  ============================================
echo   AvengerScript IDE  ^|  http://localhost:8080
echo  ============================================
echo.
echo  Ctrl+C para detener.
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
