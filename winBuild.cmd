:: [rights]  Copyright Hewlett-Packard Enterprise 2015 https://www.hpe.com
:: [license] Licensed under Apache 2.0 https://www.apache.org/licenses/LICENSE-2.0
:: [author]  Dan Bryant https://github.com/DanHP

@echo off
setlocal
(call mvn -v 1>NUL 2>NUL) || (
  echo ERR Apache Maven is required to build
  echo     see https://maven.apache.org/download.cgi
  exit /b 1
)
cd redhx-build-all
(call mvn package) || (
  echo ERR Maven build failed
  exit /b 2
)
(where robocopy 1>NUL 2>NUL) || (
  echo ERR Robocopy is required to build
  echo     This is usually in all windows installs
  exit /b 3
)
for /f %%p in ('dir /s/b %userprofile%\.m2\repository\*.jar *.jar') do @robocopy /njh /njs /ndl /fp "%%~dpp." "..\jars" "%%~nxp" /xf commons-lang3-3.1.jar
endlocal