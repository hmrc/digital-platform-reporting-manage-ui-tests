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

package uk.gov.hmrc.ui.specs.submissions

import support.SubmissionBaseSpec
import support.steps.{PlatformOperatorSteps, ReportingNotificationSteps, SubscriptionSteps}
import support.utils.FileUtils.fileToUploadFrom
import uk.gov.hmrc.ui.pages.submissions._
import uk.gov.hmrc.ui.pages.{IndexPage, ResultPage}

class ViewXMLSubmissionsSpec extends SubmissionBaseSpec {

  private val indexPage                  = IndexPage()
  private val selectPlatformOperatorPage = SelectPlatformOperatorPage()
  private val resultPage                 = ResultPage

  Feature("View XML Submissions Journeys") {
    Scenario("Single Platform Operator with correct data") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("An XML is submitted and View XML submissions is clicked")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()
      val fileToUpload = fileToUploadFrom("SubmissionSampleTemplate.xml", platformOperatorId)
      UploadPage(platformOperatorId).withFileToUpload(fileToUpload).continue()
      UploadingPage(platformOperatorId).waitUntilFinishIfUploading()
      SendFilePage(platformOperatorId).continue()
      CheckFilePage(platformOperatorId).waitUntilCheckIsFinished()
      SubmissionConfirmationPage(platformOperatorId).selectNo().continue()
      indexPage.clickViewXmlSubmissions()

      Then("The result page should be 'XML submissions'")
      resultPage.url       should include("/submission/view")
      resultPage.heading shouldBe "XML submissions"
    }
  }
}
