**This is the template README. Please update this with project specific content.**

# digital-platform-reporting-manage-ui-tests

<SERVICE_NAME> UI journey tests.

## Pre-requisites

### Services

Start Mongo Docker container as follows:

```bash
docker run --rm -d -p 27017:27017 --name mongo percona/percona-server-mongodb:5.0
```

Start `<SERVICE_MANAGER_PROFILE>` services as follows:

```bash
sm2 --start DPRS_ALL
```

## Tests

Run tests as follows:

* Argument `<environment>` must be `local`, `dev`, `qa` or `staging`.
* Argument `<browser>` must be `chrome`, `edge`, or `firefox`.
* Argument `<headless>` must be `true` or `false`.

```bash
sbt clean -Denvironment="<environment>" -Dbrowser="<browser>" --Dbrowser.option.headless="<headless>" "testOnly uk.gov.hmrc.ui.specs.*" testReport
```

Alternatively, to run all tests, you can use:
```bash
./run_tests.sh dev firefox true
```

To run tests in relation to Manage FE, you can use:
```bash
./run_manage_tests.sh dev firefox true
```

To run tests in relation to Operator FE, you can use:
```bash
./run_operator_tests.sh dev firefox true
```

To run tests in relation to Submission FE, you can use:
```bash
./run_submission_tests.sh dev firefox true
```

The above command has default values and if no parameters are passed the default values are: local, chrome and false.


## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
