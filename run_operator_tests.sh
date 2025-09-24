#!/bin/bash
BROWSER=${1:-firefox}
HEADLESS=${2:-true}

sbt clean -Dbrowser="${BROWSER}" -Dbrowser.option.headless="${HEADLESS}" "testOnly uk.gov.hmrc.ui.specs.* -- -n support.tags.OperatorFeature" testReport