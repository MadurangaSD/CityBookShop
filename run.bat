@echo off
echo ========================================
echo   CITY BOOKSHOP MANAGEMENT SYSTEM
echo ========================================
echo.

cd src

echo Checking Java installation...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java JDK 8 or higher
    echo Download from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b
)

echo.
echo Compiling application...
javac Main.java model\*.java util\*.java gui\*.java

if errorlevel 1 (
    echo.
    echo ERROR: Compilation failed!
    echo Please check the error messages above.
    pause
    exit /b
)

echo.
echo Compilation successful!
echo.
echo Starting City Bookshop System...
echo.
echo Default Login Credentials:
echo Manager: admin / admin123
echo Cashier: cashier / cash123
echo.

java Main

if errorlevel 1 (
    echo.
    echo ERROR: Application failed to start!
    pause
)

cd ..
