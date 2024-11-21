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

package support.steps

import uk.gov.hmrc.ui.pages.IndexPage
import uk.gov.hmrc.ui.pages.reportingnotifications._

object ReportingNotificationSteps {

  private val indexPage                 = IndexPage()
  private val whichPlatformOperatorPage = WhichPlatformOperatorPage()

  def addReportingNotificationFor(platformOperatorId: String, platformOperator: Option[String] = None): Unit = {
    indexPage.clickAddReportingNotification()
    platformOperator.foreach(whichPlatformOperatorPage.withPlatformOperator)
    whichPlatformOperatorPage.continue()
    PlatformNotificationStartPage(platformOperatorId).continue()
    AddNotificationPage(platformOperatorId).selectReportingPlatformOperator().continue()
    FirstPeriodPage(platformOperatorId).continue()
    DueDiligencePage(platformOperatorId).selectExtendedTimeLimit().selectActiveSellerDue().continue()
    CheckYourAnswersPage(platformOperatorId).continue()
    SuccessPage(platformOperatorId).clickManageYourDigitalPlatformReporting()
  }
}