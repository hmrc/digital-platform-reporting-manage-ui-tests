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

import java.nio.file.Paths

class MakeNewSubmissionSpec extends SubmissionBaseSpec {

  private val indexPage                  = IndexPage()
  private val selectPlatformOperatorPage = SelectPlatformOperatorPage()
  private val resultPage                 = ResultPage

  Feature("Make New XML Submission Journeys") {
    Scenario("Single Platform Operator with incorrect business details for platform operator") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("An XML Submission is started")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()

      And("Business details is incorrect")
      CheckPlatformOperatorPage(platformOperatorId).selectNo().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Single Platform Operator with incorrect reporting notification for platform operator") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("An XML Submission is started")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()

      And("Reporting notification is incorrect")
      CheckReportingNotificationsPage(platformOperatorId).selectNo().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Single Platform Operator with incorrect contact details") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("An XML Submission is started")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()

      And("Contact details is incorrect")
      CheckContactDetailsPage(platformOperatorId).selectNo().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Single Platform Operator with correct data") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("An XML is submitted")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()
      val fileToUpload = fileToUploadFrom("SubmissionTemplate.xml", platformOperatorId)
      UploadPage(platformOperatorId).withFileToUpload(fileToUpload).continue()
      UploadingPage(platformOperatorId).waitUntilFinishIfUploading()
      SendFilePage(platformOperatorId).continue()
      CheckFilePage(platformOperatorId).waitUntilCheckIsFinished()
      SubmissionConfirmationPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Single Platform Operator with upload failures") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("File submission is started")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()

      And("Non XML file is uploaded")
      val nonXMLFile = Paths.get(getClass.getClassLoader.getResource("NotXml.xml").toURI).toFile.getAbsolutePath
      UploadPage(platformOperatorId).withFileToUpload(nonXMLFile).continue()
      UploadingPage(platformOperatorId).waitUntilFinishIfUploading()

      Then("Error should be shown")
      UploadFailedPage(platformOperatorId).assertContainsError("The selected file must be XML")

      When("File with invalid schema is uploaded")
      UploadFailedPage(platformOperatorId)
        .withFileToUpload(fileToUploadFrom("InvalidSchemaTemplate.xml", platformOperatorId))
        .continue()
      UploadingPage(platformOperatorId).waitUntilFinishIfUploading()

      Then("'There is a problem with the data in your file' page should be shown")
      UploadFailedPage(platformOperatorId).heading shouldBe "There is a problem with the data in your file"

      When("File with unknown platform operator id is uploaded")
      UploadFailedPage(platformOperatorId).clickUploadDifferentFile()
      UploadPage(platformOperatorId)
        .withFileToUpload(fileToUploadFrom("SubmissionTemplate.xml", "unknown-po-id"))
        .continue()
      UploadingPage(platformOperatorId).waitUntilFinishIfUploading()

      Then("Error should be shown")
      UploadFailedPage(platformOperatorId).assertContainsError("The Platform Operator IDs do not match")
    }

    Scenario("Single Platform Operator with failed submission") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("File that fails submission is submitted")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()
      val fileToUpload = fileToUploadFrom("FailedSubmissionTemplate.xml", platformOperatorId)
      UploadPage(platformOperatorId).withFileToUpload(fileToUpload).continue()
      UploadingPage(platformOperatorId).waitUntilFinishIfUploading()
      SendFilePage(platformOperatorId).continue()
      CheckFilePage(platformOperatorId).waitUntilCheckIsFinished()

      Then("The result page should be 'There is a problem with your file'")
      resultPage.url       should include("/file-errors")
      resultPage.heading shouldBe "There is a problem with your file"
    }

    Scenario("Multiple Platform Operators with correct data") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorNameOne = "Platform Operator One"
      val platformOperatorOne     = PlatformOperatorSteps.addPlatformOperator(platformOperatorNameOne)
      PlatformOperatorSteps.addPlatformOperator("Platform Operator Two")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorOne, Some(platformOperatorNameOne))

      When("An XML is submitted")
      indexPage.clickMakeXmlSubmission()
      selectPlatformOperatorPage.withPlatformOperator(platformOperatorNameOne).continue()
      StartPage(platformOperatorOne).continue()
      CheckPlatformOperatorPage(platformOperatorOne).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorOne).selectYes().continue()
      CheckContactDetailsPage(platformOperatorOne).selectYes().continue()
      val fileToUpload = fileToUploadFrom("SubmissionTemplate.xml", platformOperatorOne)
      UploadPage(platformOperatorOne).withFileToUpload(fileToUpload).continue()
      UploadingPage(platformOperatorOne).waitUntilFinishIfUploading()
      SendFilePage(platformOperatorOne).continue()
      SubmissionConfirmationPage(platformOperatorOne).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }
  }
}
