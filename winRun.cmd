:: [rights]  Copyright Hewlett-Packard Enterprise 2015 https://www.hpe.com
:: [license] Licensed under Apache 2.0 https://www.apache.org/licenses/LICENSE-2.0
:: [author]  Dan Bryant https://github.com/DanHP
@echo off
setlocal

::### Change these lines to reflect your config ###::
set server=%myserver%
set port=443
set username=bob
set password=secret

set cert=server.crt
set keystore=cacerts.jks
if not exist %keystore% (
  (openssl version 1>NUL 2>NUL) || (
    echo ERR OpenSSL is required to build the Java Keystore
    echo     see Cygwin for a collection of linux binaries including OpenSSL
    echo     link https://cygwin.com/
    exit /b 1
  )
  echo ### CREATING KEYSTORE FOR SSL VALIDATION ###
  (echo QUIT | openssl s_client -connect %server%:%port% 2>NUL | openssl x509 -out %cert%) || (
    echo ERR Failed to download the server cert
    exit /b 2
  )
  (echo yes | keytool -import -v -trustcacerts -alias server-alias -file %cert% -keystore %keystore% -keypass %password% -storepass %password%) || (
    echo ERR Failed to create the keystore
    exit /b 3
  )
)
cd redhx-build-all
java ^
 "-Dparm_protocol=https" ^
 "-Dparam_hostname=%server%" ^
 "-Dparam_username=%username%" ^
 "-Dparam_password=%password%" ^
 "-Djavax.net.ssl.trustStore=%cd%\..\%keystore%" ^
 -cp "..\jars\*" org.redhelix.server.main.RedMatrixServerDb
endlocal