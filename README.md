**Implemented with:**

- Java 1.8
- JUnit 4.13
- Apache Maven 3.6.1
- Serenity BDD framework v3.1.5 (`Thucydides` in past, docs: http://www.thucydides.info/docs/serenity/)

### Short installation guide: ###

1. Install Java on your PC (https://java.com/en/download/help/download_options.xml)
2. Install JDK for your PC (https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
3. Install Maven for your PC (https://maven.apache.org/download.cgi), (https://maven.apache.org/install.html)

## Running tests

- Run the below command to build entire project without running tests - this will build entire project and resolve
  dependency
    ````
    mvn clean install -DskipTests
    ````
- Execution command variables:
  * -Dserenity.data.dir - path to the test data files (i.e. `chase/usa`)
  * -Dcountry - execution country code - `usa, canada`
  * -Denvironment - environment name - `dev, qat, uscst`
  * -Dtags - execution tags (i.e. `chase:cof:regression`)
  * -Dbank - bank
    name - `chase, woodforest, fdrc_bypass35, fdrc_nashville35, fdrc_nashville36, fdrc_nashville38, worldpay`

To execute tests for certification in particular environment (make sure the env variable given is present in
serenity.conf)

   ````
       mvn clean verify -Dserenity.data.dir=chase/usa -Dcountry=usa -Denvironment=qat -Dtags=chase:ecom:cert -Dbank=chase
   ````

#### Running tests in parallel mode

- User can run following command to execute tests in parallel, the default thread count is '2' i.e two browser instances
  will open and tests get executed in parallel in each browser
   ````
   mvn clean -Pparallel verify -Dcountry=usa
   ````
- User can also define the number of parallel threads to execute tests
   ````
   mvn clean -Pparallel verify -Dparallel.count=3 -Dserenity.data.dir=usa
   ````

**PLEASE NOTE:**

Sometimes you might have problems with `pom.xml` file dependencies update on your PC. Just try to completely clean the
local cache by executing next command:

$ _`mvn dependency:purge-local-repository clean`_

## **Serenity Documentation** ##

http://www.thucydides.info/docs/serenity/

## Jenkins execution

### Providing execution command:

`cmdmap.txt` file contains mapping between Jenkins job name and the execution command

Job format - `{Job#}#{Job Name}={mvn execution command}#{emailGroup#}`

Sample:
`01#Chase US E-commerce cert=mvn clean verify -Dserenity.data.dir=chase/usa -Dcountry=usa -Denvironment=dev -Dtags="chase:ecom:cert" -Dbank=chase#EmailGroup1`

### Schedule execution configuration:

`autojenkins.txt` file contains list of the Jenkins jobs that will be executed daily-based on scheduled time.
`jksmoke.txt` file contains list of the Jenkins jobs with Smoke tests that will be executed on the scheduled time.

Job format - `{Job#}:{environment}`. By default, DEV environment is configured.

Sample: `01:QAT`