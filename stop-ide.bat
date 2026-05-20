@echo off
title Detener AvengerScript IDE

echo.
echo  Buscando proceso en puerto 8080...
echo.

set MVN="%~dp0mvnw.cmd"
set FOUND=0
for /f "tokens=5" %%a in ('netstat -aon 2^>nul ^| findstr ":8080 "') do (
    if %%a NEQ 0 (
        echo  Matando PID %%a ...
        taskkill /F /PID %%a 2>nul
        set FOUND=1
    )
)

if %FOUND%==0 (
    echo  No habia ningun proceso en el puerto 8080.
) else (
    echo  Puerto 8080 liberado correctamente.
)
echo.
pause
