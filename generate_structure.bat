@echo off
REM Batch script to generate the folder structure and empty files for Energy Consumption Dashboard project.

REM Create the main folders
mkdir .github\workflows
mkdir .idea
mkdir src\main\java\com\example\energyconsumptiondashboard\controller
mkdir src\main\java\com\example\energyconsumptiondashboard\service
mkdir src\main\java\com\example\energyconsumptiondashboard\model
mkdir src\main\java\com\example\energyconsumptiondashboard\repository
mkdir src\main\java\com\example\energyconsumptiondashboard\config
mkdir src\main\resources\static\css
mkdir src\main\resources\static\js
mkdir src\main\resources\static\images
mkdir src\main\resources\templates
mkdir src\test\java\com\example\energyconsumptiondashboard

REM Create the empty files in the project structure

REM Main files
echo. > .gitignore
echo. > LICENSE
echo. > README.md
echo. > pom.xml

REM .github workflows
echo. > .github\workflows\java.yml

REM .idea
echo. > .idea\modules.xml
echo. > .idea\workspace.xml
echo. > .idea\misc.xml

REM src files
echo. > src\main\java\com\example\energyconsumptiondashboard\EnergyConsumptionDashboardApplication.java
echo. > src\main\java\com\example\energyconsumptiondashboard\controller\EnergyUsageController.java
echo. > src\main\java\com\example\energyconsumptiondashboard\service\EnergyService.java
echo. > src\main\java\com\example\energyconsumptiondashboard\model\EnergyUsage.java
echo. > src\main\java\com\example\energyconsumptiondashboard\repository\EnergyUsageRepository.java
echo. > src\main\java\com\example\energyconsumptiondashboard\config\InfluxDBConfig.java

REM Resources files
echo. > src\main\resources\application.properties
echo. > src\main\resources\static\css\style.css
echo. > src\main\resources\static\js\script.js
echo. > src\main\resources\static\images\logo.png
echo. > src\main\resources\templates\index.html
echo. > src\main\resources\templates\energy-usage.html
echo. > src\main\resources\templates\alert.html
echo. > src\main\resources\templates\layout.html

REM Test files
echo. > src\test\java\com\example\energyconsumptiondashboard\EnergyConsumptionDashboardApplicationTests.java

REM Kubernetes folder
mkdir k8s
echo. > k8s\deployment.yaml
echo. > k8s\service.yaml
echo. > k8s\ingress.yaml
echo. > k8s\configmap.yaml

REM Docker and Docker Compose files
echo. > Dockerfile
echo. > docker-compose.yml

REM Print completion message
echo Folder structure and empty files have been generated.
pause
