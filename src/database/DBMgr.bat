REM ----------------------------------------------------
REM   Copyright: Sporacid Studios 2013
REM   Author: Patrick Lavallee
REM   Initial Revision: 31/10/2013 Happy Halloween !!!
REM   Description: Postgre database deployment manager
REM ----------------------------------------------------

@ECHO OFF
SET host="127.0.0.1"
SET username="postgres"
SET password="qazwsxedc"
SET port=5432
SET pgShell="E:\Program Files (x86)\PostGre\bin\psql.exe"

:DisplayMenu
REM  // Clear screen, print menu and read user input
CLS
ECHO -- Database Manager (Sporacid studios 2013) --
ECHO.
ECHO  1- Build Tables
ECHO  2- Drop Tables
ECHO  3- Cleanup Tables
ECHO  4- Dataload Database
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
IF %decision%==3 GOTO :Cleanup
IF %decision%==4 GOTO :Dataload
IF %decision%==0 (
	GOTO :Finally
) ELSE (
	GOTO :DisplayMenu 
)

:Build
ECHO Building table structure of: %dbname%
%pgShell% -h %host% -U %username% -d %dbname% -p %port% -f ./DDL_create_all.sql
ECHO.
ECHO Database built!
PAUSE
GOTO :DisplayMenu

:Destroy
ECHO Dropping table structure of: %dbname%
%pgShell% -h %host% -U %username% -d %dbname% -p %port% -f ./DDL_destroy_all.sql
ECHO.
ECHO Database destroyed!
PAUSE
GOTO :DisplayMenu

:Cleanup
ECHO Deleting %dbname%'s tables
%pgShell% -h %host% -U %username% -d %dbname% -p %port% -f ./DML_delete_all.sql
ECHO.
ECHO Database tables cleaned!
PAUSE
GOTO :DisplayMenu

:Dataload
ECHO Dataloading %dbname%'s tables
%pgShell% -h %host% -U %username% -d %dbname% -p %port% -f ./DML_insert_all.sql
ECHO.
ECHO Database tables dataloaded!
PAUSE
GOTO :DisplayMenu

:Finally
PAUSE