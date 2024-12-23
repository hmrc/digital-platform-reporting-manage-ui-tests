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

package uk.gov.hmrc.ui.specs.manualreporting

import support.SubmissionBaseSpec
import support.pages.manualreporting._
import support.pages.{IndexPage, ResultPage}
import support.steps.{PlatformOperatorSteps, ReportingNotificationSteps, SubscriptionSteps}

class DeleteManualReportSpec extends SubmissionBaseSpec {

  private val indexPage                  = IndexPage()
  private val selectPlatformOperatorPage = SelectPlatformOperatorPage()
  private val resultPage                 = ResultPage

  Feature("Delete Manual Assumed Report") {
    Scenario("Single Platform Operator with correct data - Delete Manual Assumed Report") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("A manual assumed report is submitted and deleted")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()
      ReportablePeriodPage(platformOperatorId).withYear("2024").continue()
      AssumingOperatorNamePage(platformOperatorId).withName("Sherlock Holmes").continue()
      TaxResidentInUkPage(platformOperatorId).selectNo().continue()
      TaxResidencyCountryPage(platformOperatorId).withCountry("United States").continue()
      HasInternationalTaxIdentifierPage(platformOperatorId).selectYes().continue()
      InternationalTaxIdentifierPage(platformOperatorId).withTaxIdentificationNumber("TIN112233").continue()
      RegisteredAddressCountryPage(platformOperatorId).withCountry("United States").continue()
      RegisteredAddressPage(platformOperatorId).withAddress("221B Baker Street").continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      AssumedReportingSubmissionCreatedPage(platformOperatorId, "2024").clickManageYourDigitalPlatformReporting()
      indexPage.clickViewManualAssumedReport()
      ViewAssumedReportsPage(platformOperatorId, "2024").clickDeleteAssumedReport()
      DeleteAssumedReportPage(platformOperatorId, "2024").selectYes().continue()

      Then("The result page should be 'Assumed reporting details have been successfully deleted'")
      resultPage.url       should include(s"/assumed-reporting/$platformOperatorId/deleted/2024")
      resultPage.heading shouldBe "Assumed reporting details have been successfully deleted"
    }
  }
}
