# testing-task

### Supported and tested browsers: `chrome` `firefox`
### Configuration file: `./src/main/resources/common.properties`
##### Properties from Configuration file can be overridden by gradle command line arguments

## how to run
* `git clone https://github.com/akutsenko/testing-task.git`
* tests can be run tests locally, using selenium grid, in fully dockerized env.

### Local test execution:
* Java 8 or later should be installed
* webdriver binaries will be automatically downloaded by WebDriverManager
* `gradlew clean test -Dis.remote=false -Dbrowser={browserName}`
* `gradlew allureReport` - to download allure report
* `gradlew allureServe` - to launch allure report

### Remote test execution:
* for utilizing "classic selenium grid" infrastructure ensure that hub with appropriate nodes are running 
* `gradlew clean test -Dis.remote=true -Dbrowser={browserName} -Dhub.url={hubURL}`
* `gradlew allureReport` - to download allure report
* `gradlew allureServe` - to launch allure report

### Dockerized test execution:
* `docker-compose up` - will create container with test project and installed allure report. UI tests will be executed in separate container(s)
* wait for the tests to be executed and allure report to be launched: `Server started at <http://<ip address>>:1111/>. Press <Ctrl+C> to exit`
* open your local browser and navigate to url `http://localhost:1111` to see the allure report details
