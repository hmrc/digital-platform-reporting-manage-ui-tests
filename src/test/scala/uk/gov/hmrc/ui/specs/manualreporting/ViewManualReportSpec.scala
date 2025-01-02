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

import java.time.Year

class ViewManualReportSpec extends SubmissionBaseSpec {

  private val indexPage                  = IndexPage()
  private val selectPlatformOperatorPage = SelectPlatformOperatorPage()
  private val resultPage                 = ResultPage

  Feature("View New Assumed Report") {
    Scenario("Single Platform Operator with correct data - View Manual Assumed Report") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("A manual assumed report is submitted and Viewed")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()
      ReportablePeriodPage(platformOperatorId).withYear(Year.now.toString).continue()
      AssumingOperatorNamePage(platformOperatorId).withName("Sherlock Holmes").continue()
      TaxResidentInUkPage(platformOperatorId).selectNo().continue()
      TaxResidencyCountryPage(platformOperatorId).withCountry("United States").continue()
      HasInternationalTaxIdentifierPage(platformOperatorId).selectYes().continue()
      InternationalTaxIdentifierPage(platformOperatorId).withTaxIdentificationNumber("TIN112233").continue()
      RegisteredAddressCountryPage(platformOperatorId).withCountry("United States").continue()
      RegisteredAddressPage(platformOperatorId).withAddress("221B Baker Street").continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      AssumedReportingSubmissionCreatedPage(platformOperatorId, Year.now.toString)
        .clickManageYourDigitalPlatformReporting()
      indexPage.clickViewManualAssumedReport()

      Then("The result page should be 'Assumed reporting history'")
      resultPage.url       should include("/assumed-reporting/view")
      resultPage.heading shouldBe "Assumed reporting history"
    }

    Scenario("Single Platform Operator with correct data - Edit Manual Assumed Report") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("A manual assumed report is submitted and Viewed")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()
      ReportablePeriodPage(platformOperatorId).withYear(Year.now.toString).continue()
      AssumingOperatorNamePage(platformOperatorId).withName("Sherlock Holmes").continue()
      TaxResidentInUkPage(platformOperatorId).selectNo().continue()
      TaxResidencyCountryPage(platformOperatorId).withCountry("United States").continue()
      HasInternationalTaxIdentifierPage(platformOperatorId).selectYes().continue()
      InternationalTaxIdentifierPage(platformOperatorId).withTaxIdentificationNumber("TIN112233").continue()
      RegisteredAddressCountryPage(platformOperatorId).withCountry("United States").continue()
      RegisteredAddressPage(platformOperatorId).withAddress("221B Baker Street").continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      AssumedReportingSubmissionCreatedPage(platformOperatorId, Year.now.toString)
        .clickManageYourDigitalPlatformReporting()

      indexPage.clickViewManualAssumedReport()
      ViewAssumedReportsPage(platformOperatorId, Year.now.toString).clickEditAssumedReport()

      And("Change assumed operator name is clicked")
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).clickChangeAssumingOperatorName()
      ChangeAssumingOperatorNamePage(platformOperatorId, Year.now.toString).withName("Enola Holmes").continue()

      And("Change registered address is clicked")
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).clickChangeRegisteredAddress()
      ChangeRegisteredAddressPage(platformOperatorId, Year.now.toString).withAddress("222B Baker Street").continue()

      And("Change international TIN is clicked")
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).clickChangeInternationalTin()
      ChangeInternationalTaxIdentifierPage(platformOperatorId, Year.now.toString)
        .withTaxIdentificationNumber("TIN112234")
        .continue()

      And("Change has international TIN is clicked")
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).clickChangeHaveInternationalTin()
      ChangeHasInternationalTaxIdentifierPage(platformOperatorId, Year.now.toString).selectNo().continue()
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).clickChangeHaveInternationalTin()
      ChangeHasInternationalTaxIdentifierPage(platformOperatorId, Year.now.toString).selectYes().continue()
      ChangeInternationalTaxIdentifierPage(platformOperatorId, Year.now.toString)
        .withTaxIdentificationNumber("TIN112235")
        .continue()

      And("Change tax resident in UK is clicked")
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).clickChangeTaxResidentInUK()
      ChangeTaxResidentInUkPage(platformOperatorId, Year.now.toString).selectNo().continue()
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).clickChangeTaxResidentInUK()
      ChangeTaxResidentInUkPage(platformOperatorId, Year.now.toString).selectYes().continue()
      ChangeHasUKTaxIdentifierPage(platformOperatorId, Year.now.toString).selectYes().continue()
      ChangeUKTaxIdentifierPage(platformOperatorId, Year.now.toString)
        .withTaxIdentificationNumber("TIN112236")
        .continue()
      CheckYourAnswersAssumedReportPage(platformOperatorId, Year.now.toString).continue()

      Then("The result page should be 'Assumed reporting details successfully updated'")
      resultPage.url       should include(
        s"/assumed-reporting/$platformOperatorId/${Year.now.toString}/update-confirmation-page"
      )
      resultPage.heading shouldBe "Assumed reporting details successfully updated"
    }
  }
}
