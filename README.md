**Implemented with:**

- Java 121
- JUnit 5.13.4
- Serenity BDD framework v4.2.34

### Short installation guide: ###

1. Install Java on your PC 
2. Install JDK for your PC 
3. Install Maven for your PC 

## Running tests

- Run the below command to build entire project without running tests - this will build entire project and resolve
  dependency
    ````
    mvn clean install -DskipTests
    ````
- Execution command variables:
dev env has a propper url that works. In qat env - url doesn't work, it is provided to show the possibility to use different env options

  * -Denvironment - environment name - `dev, qat`


   ````
       mvn clean verify -Denvironment=dev
   ````

## Serenity Report

After mvn execution Serenity report will be generated automatically

To generate report manually:
  ````
  mvn serenity:aggregate

  ````
Report will be generated to `/target/site/serenity/index.html`


## **Serenity Documentation** ##

https://serenity-bdd.github.io/docs/guide/user_guide_intro

