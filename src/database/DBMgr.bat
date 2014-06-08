REM ----------------------------------------------------
REM   Copyright: Sporacid Studios 2013
REM   Author: Patrick Lavallee
REM   Initial Revision: 31/10/2013 Happy Halloween !!!
REM   Description: Postgre database deployment manager
REM ----------------------------------------------------

@ECHO OFF
SET host="127.0.0.1"
SET username="postgres"
SET password="bacon"
SET port=5432
SET pgShell="C:\Program Files\PostgreSQL\9.3\bin\psql.exe"

:DisplayMenu
REM  // Clear screen, print menu and read user input
CLS
ECHO -- Database Manager (Sporacid studios 2013) --
ECHO.
ECHO  1- Build Tables And dataload
ECHO  2- Drop Tables
ECHO  0- Quit
ECHO.
ECHO.
SET /p decision="Choose an option: "

REM	 // User input validation
IF %decision% geq 1 (
	IF %decision% leq 4 (
		SET /p dbname="Enter the database name (case sensitive): "
	)
)
ECHO. 
REM // decisions... decisions...
IF %decision%==1 GOTO :Build 
IF %decision%==2 GOTO :Destroy
IF %decision%==0 (
	GOTO :Finally
) ELSE (
	GOTO :DisplayMenu 
)

:Build
ECHO Building table structure of: %dbname%
%pgShell% -h %host% -U %username% -d %dbname% -p %port% -f ./deploy-database.sql
%pgShell% -h %host% -U %username% -d %dbname% -p %port% -f ./initial-dataload.sql
ECHO.
ECHO Database built!
PAUSE
GOTO :DisplayMenu

:Destroy
ECHO Dropping table structure of: %dbname%
%pgShell% -h %host% -U %username% -d %dbname% -p %port% -f ./drop-database.sql
ECHO.
ECHO Database destroyed!
PAUSE
GOTO :DisplayMenu

:Finally
PAUSE