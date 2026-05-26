@echo off
echo.
echo  ============================================
echo   AvengerScript -- Instalador de Extension
echo  ============================================
echo.

cd /d "%~dp0"

REM ── Verificar Node / npm ─────────────────────────────────────────────────────
where npm >nul 2>&1
if errorlevel 1 (
    echo [ERROR] npm no encontrado. Instala Node.js: https://nodejs.org
    pause & exit /b 1
)

REM ── Verificar VS Code CLI ────────────────────────────────────────────────────
where code >nul 2>&1
if errorlevel 1 (
    echo [AVISO] El comando 'code' no esta en el PATH.
    echo         Abri VS Code, presiona Ctrl+Shift+P
    echo         luego: Shell Command: Install 'code' command in PATH
    echo         y volvé a correr este script.
    pause & exit /b 1
)

REM ── 1. Instalar dependencias ─────────────────────────────────────────────────
echo [1/4] Instalando dependencias npm...
call npm install --silent
if errorlevel 1 ( echo [ERROR] npm install fallo. & pause & exit /b 1 )
echo       OK

REM ── 2. Compilar TypeScript ───────────────────────────────────────────────────
echo [2/4] Compilando TypeScript...
call npm run compile
if errorlevel 1 ( echo [ERROR] Compilacion TypeScript fallo. & pause & exit /b 1 )
echo       OK

REM ── 3. Empaquetar .vsix ──────────────────────────────────────────────────────
echo [3/4] Empaquetando extension (.vsix)...
call npm run package
if errorlevel 1 ( echo [ERROR] vsce package fallo. & pause & exit /b 1 )
echo       OK

REM ── 4. Instalar en VS Code ───────────────────────────────────────────────────
echo [4/4] Instalando en VS Code...
for %%f in (*.vsix) do (
    call code --install-extension "%%f" --force
    echo       Instalado: %%f
)

echo.
echo  ============================================
echo   Extension instalada correctamente!
echo   Recarga VS Code: Ctrl+Shift+P, Reload Window
echo  ============================================
echo.
pause
