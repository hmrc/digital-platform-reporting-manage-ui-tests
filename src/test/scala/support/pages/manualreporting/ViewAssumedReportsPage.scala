/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package support.pages.manualreporting

import support.pages.SubmissionBasePage

case class ViewAssumedReportsPage(platformOperatorId: String, reportingPeriod: String)
    extends SubmissionBasePage("/assumed-reporting/view") {
  private val editLinkCssSelector   =
    s"a[href*='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/initialise']"
  private val deleteLinkCssSelector =
    s"a[href*='/digital-platform-reporting/assumed-reporting/$platformOperatorId/delete/$reportingPeriod']"

  def clickEditAssumedReport(): Unit = click(editLinkCssSelector)

  def clickDeleteAssumedReport(): Unit = click(deleteLinkCssSelector)

}
