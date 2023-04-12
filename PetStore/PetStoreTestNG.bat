set projectLocation=%CD%

cd %CD%

set classpath=%CD%\bin;%CD%\lib\

java org.testng.TestNG %CD%\testng.xml

pause