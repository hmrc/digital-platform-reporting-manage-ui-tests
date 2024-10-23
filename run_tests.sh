#!/bin/bash
ENV=${1:-local}
BROWSER=${2:-chrome}
HEADLESS=${3:-true}

sbt clean -Denvironment="${ENV}" -Dbrowser="${BROWSER}" -Dbrowser.option.headless="${HEADLESS}" "testOnly uk.gov.hmrc.ui.specs.*" testReport