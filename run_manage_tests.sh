#!/bin/bash
BROWSER=${1:-chrome}
HEADLESS=${2:-true}

sbt clean -Dbrowser="${BROWSER}" -Dbrowser.option.headless="${HEADLESS}" "testOnly uk.gov.hmrc.ui.specs.* -- -n support.tags.ManageFeature" testReport