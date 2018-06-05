Hochschule für Technik HSR Rapperswil

MAS in Software Engineering
## Modul Software Testing

# Sourcecode für Uebungen
thomas.briner@gmail.com


## Intro
Dieses Repository bildet die Basis für die praktischen Uebungen zum Modul Software Testing. Es besteht aus den folgenden Modulen:


├── admin<br>
├── api<br>
├── core<br>
├── site<br>
└── selenium-tests<br>

## Preconditions
Um den Code für die Vorlesung benützen zu können, brauchen Sie folgende Software installiert:
- Java JDK >= 1.8
- Maven
- eine IDE mit JUnit5 Integration (getestet mit IDEA Intellij und Eclipse >= Oxygen)

Das Repository beinhaltet die benötigten Konfigurationsdateien für Eclipse und Intellij.


## Installation
Klonen Sie das Repository auf Ihrem lokalen Rechner, welcher die obigen Preconditions erfüllt.

### System under Test "Heat Clinic" bauen
Wechseln Sie in das entsprechende Verzeichnis

`$ cd HSR-SW-Testing-Code
`

Der Inhalt des Verzeichnisses sieht folgendermassen aus:

`$ ls -l`  
`total 112`  
`-rw-r--r--   1 briner  staff  34978 14 Mai 07:15 LICENSE`  
`-rw-r--r--   1 briner  staff   7676 14 Mai 07:15 README.md`  
`drwxr-xr-x  11 briner  staff    352  4 Jun 20:22 admin`  
`drwxr-xr-x  10 briner  staff    320  4 Jun 20:22 api`  
`-rw-r--r--   1 briner  staff    574  4 Jun 20:01 boot-community-demo.iml`  
`drwxr-xr-x  11 briner  staff    352  4 Jun 20:22 core`  
`-rw-r--r--   1 briner  staff   6457  4 Jun 20:23 pom.xml`  
`drwxr-xr-x  18 briner  staff    576  4 Jun 20:33 selenium-tests`  
`drwxr-xr-x  11 briner  staff    352  4 Jun 20:22 site`  
`drwxr-xr-x   3 briner  staff     96  4 Jun 19:56 target`
`

Um die benötigten Abhängigkeiten für das System under Test aufzulösen, führen Sie folgenden Befehl aus:

`$ mvn clean install`  
`[INFO] Scanning for projects...`  
`[INFO] ------------------------------------------------------------------------`  
`[INFO] Reactor Build Order:`  
`[INFO] `  
`[INFO] Broadleaf Spring Boot Community Demo                               [pom]`  
`[INFO] Community Demo Core                                                [jar]`  
`[INFO] Community Demo Site                                                [jar]`  
`[INFO] Community Demo Admin                                               [jar]`  
`[INFO] Community Demo API                                                 [jar]`  
`...`  
`[INFO] ------------------------------------------------------------------------`  
`[INFO] Reactor Summary:`  
`[INFO]`   
`[INFO] Broadleaf Spring Boot Community Demo 1.0.0-SNAPSHOT  SUCCESS [  1.219 s]`  
`[INFO] Community Demo Core ................................ SUCCESS [  5.426 s]`  
`[INFO] Community Demo Site ................................ SUCCESS [  8.659 s]`  
`[INFO] Community Demo Admin ............................... SUCCESS [  1.751 s]`  
`[INFO] Community Demo API 1.0.0-SNAPSHOT .................. SUCCESS [  2.275 s]`  
`[INFO] ------------------------------------------------------------------------`  
`[INFO] BUILD SUCCESS`  
`[INFO] ------------------------------------------------------------------------`  
`[INFO] Total time: 20.301 s`  
`[INFO] Finished at: 2018-06-05T06:40:36+02:00`  
`[INFO] ------------------------------------------------------------------------`  

### Modul Selenium Tests bauen

Das Modul selenium-tests benötigt als Build-Werkzeug gradle. Das ist aber direkt
im Projekt enthalten und muss nicht zusätzlich installiert werden.

Um das Modul selenium-tests zu bauen, gehen Sie folgendermassen vor:

`$ cd selenium-tests/`

`$ ./gradlew build`


`Starting a Gradle Daemon (subsequent builds will be faster)`  
`:compileJava NO-SOURCE`  
`:processResources NO-SOURCE`  
`:classes UP-TO-DATE`  
`:jar`  
`:assemble`  
`:compileTestJava`  
`:processTestResources`  
`:testClasses`  
`:test`  
`:check`  
`:build`  

`BUILD SUCCESSFUL `  
``Total time: 9.96 secs`
``

### System under Test "Heat Clinic" starten
Wechseln Sie ins Module "site"

`cd HSR-SW-Testing-Code/site/`

Starten Sie den Webshop mit folgendem Befehl:

`$ mvn spring-boot:run`  

Das ist der erwartete Output:

`[INFO] Scanning for projects...`  
`[INFO]`   
`[INFO] ----------< com.mycompany-community:boot-community-demo-site >----------`  
`[INFO] Building Community Demo Site 1.0.0-SNAPSHOT`  
`...`  
`2018-06-05 17:29:47.415  INFO 56886 --- [           main] c.c.c.s.s.SolrIndexCleanupServiceImpl    : All indexes rebuilt at startup because value was create`  
`2018-06-05 17:29:47.767  INFO 56886 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8443 (https) 8080 (http)`  
`2018-06-05 17:29:47.781  INFO 56886 --- [           main] com.community.SiteApplication            : Started SiteApplication in 69.671 seconds (JVM running for 70.63)`  

``

