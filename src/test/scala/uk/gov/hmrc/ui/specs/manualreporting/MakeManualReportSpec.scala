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
import support.steps.{PlatformOperatorSteps, ReportingNotificationSteps, SubscriptionSteps, XmlSubmissionsSteps}

class MakeManualReportSpec extends SubmissionBaseSpec {

  private val indexPage                  = IndexPage()
  private val selectPlatformOperatorPage = SelectPlatformOperatorPage()
  private val resultPage                 = ResultPage

  Feature("Make New Assumed Report") {
    Scenario("Single Platform Operator with correct data - Not tax resident in the UK") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("A manual assumed report is started")
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
      HasInternationalTaxIdentifierPage(platformOperatorId).selectNo().continue()
      RegisteredAddressCountryPage(platformOperatorId).clickBack()
      HasInternationalTaxIdentifierPage(platformOperatorId).selectYes().continue()
      InternationalTaxIdentifierPage(platformOperatorId).withTaxIdentificationNumber("TIN112233").continue()
      RegisteredAddressCountryPage(platformOperatorId).withCountry("United States").continue()
      RegisteredAddressPage(platformOperatorId).withAddress("221B Baker Street").continue()
      CheckYourAnswersPage(platformOperatorId).continue()

      Then("The result page should be 'Assumed reporting submission created'")
      resultPage.url       should include("/confirmation-page")
      resultPage.heading shouldBe "Assumed reporting submission created"
    }

    Scenario("Single Platform Operator with correct data - Tax resident in the UK") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("A manual assumed report is started")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
      CheckContactDetailsPage(platformOperatorId).selectYes().continue()
      ReportablePeriodPage(platformOperatorId).withYear("2024").continue()
      AssumingOperatorNamePage(platformOperatorId).withName("Sherlock Holmes").continue()
      TaxResidentInUkPage(platformOperatorId).selectYes().continue()
      HasUKTaxIdentifierPage(platformOperatorId).selectNo().continue()
      RegisteredAddressCountryPage(platformOperatorId).clickBack()
      HasUKTaxIdentifierPage(platformOperatorId).selectYes().continue()
      UKTaxIdentifierPage(platformOperatorId).withTaxIdentificationNumber("TIN112233").continue()
      RegisteredAddressCountryPage(platformOperatorId).withCountry("United States").continue()
      RegisteredAddressPage(platformOperatorId).withAddress("221B Baker Street").continue()
      CheckYourAnswersPage(platformOperatorId).continue()

      Then("The result page should be 'Assumed reporting submission created'")
      resultPage.url       should include("/confirmation-page")
      resultPage.heading shouldBe "Assumed reporting submission created"
    }

    Scenario("Single Platform Operator with incorrect business details for platform operator") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)

      When("A manual assumed report is started")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()

      And("Business details are incorrect")
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

      When("A manual assumed report is started")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()

      And("Business details are correct")
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()

      And("Reporting notification details are incorrect")
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

      When("A manual assumed report is started")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.continue()
      StartPage(platformOperatorId).continue()

      And("Business details are correct")
      CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()

      And("Reporting notification details are correct")
      CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()

      And("Contact details are incorrect")
      CheckContactDetailsPage(platformOperatorId).selectNo().continue()
      ViewContactDetailsPage().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Multiple Platform Operators with correct data") {
      Given("Newly subscribed user with platform operator and reporting notification")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorNameOne = "Platform Operator One"
      val platformOperatorOne     = PlatformOperatorSteps.addPlatformOperator(platformOperatorNameOne)
      PlatformOperatorSteps.addPlatformOperator("Platform Operator Two")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorOne, Some(platformOperatorNameOne))

      When("A manual assumed report is started")
      indexPage.clickMakeManualAssumedReport()
      selectPlatformOperatorPage.withPlatformOperator(platformOperatorNameOne).continue()
      StartPage(platformOperatorOne).continue()
      CheckPlatformOperatorPage(platformOperatorOne).selectYes().continue()
      CheckReportingNotificationsPage(platformOperatorOne).selectYes().continue()
      CheckContactDetailsPage(platformOperatorOne).selectYes().continue()
      ReportablePeriodPage(platformOperatorOne).withYear("2024").continue()
      AssumingOperatorNamePage(platformOperatorOne).withName("Sherlock Holmes").continue()
      TaxResidentInUkPage(platformOperatorOne).selectNo().continue()
      TaxResidencyCountryPage(platformOperatorOne).withCountry("United States").continue()
      HasInternationalTaxIdentifierPage(platformOperatorOne).selectYes().continue()
      InternationalTaxIdentifierPage(platformOperatorOne).withTaxIdentificationNumber("TIN112233").continue()
      RegisteredAddressCountryPage(platformOperatorOne).withCountry("United States").continue()
      RegisteredAddressPage(platformOperatorOne).withAddress("221B Baker Street").continue()
      CheckYourAnswersPage(platformOperatorOne).continue()

      Then("The result page should be 'Assumed reporting submission created'")
      resultPage.url       should include("/confirmation-page")
      resultPage.heading shouldBe "Assumed reporting submission created"
    }

    Scenario("Single Platform Operator with correct data - XML already submitted for reporting period") {
      Given("User has submitted XML for current year")
      SubscriptionSteps.newlySubscribedOrganisation()
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator("Platform Operator One")
      ReportingNotificationSteps.addReportingNotificationFor(platformOperatorId)
      XmlSubmissionsSteps.addXmlSubmission(platformOperatorId)

      When("A manual assumed report is started for the same reporting period")
      indexPage.clickMakeManualAssumedReport()
      support.pages.manualreporting.SelectPlatformOperatorPage().continue()
      support.pages.manualreporting.StartPage(platformOperatorId).continue()
      ReportablePeriodPage(platformOperatorId).withYear("2024").continue()

      Then("The result page should be 'There is a problem with the report you are trying to make'")
      resultPage.url       should include(s"/assumed-reporting/$platformOperatorId/submissions-exist")
      resultPage.heading shouldBe "There is a problem with the report you are trying to make"
    }
  }
}
