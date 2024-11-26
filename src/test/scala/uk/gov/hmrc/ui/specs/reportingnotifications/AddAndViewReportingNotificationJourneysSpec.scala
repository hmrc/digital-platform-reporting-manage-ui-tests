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

package uk.gov.hmrc.ui.specs.reportingnotifications

import support.OperatorBaseSpec
import support.pages.reportingnotifications._
import support.pages.{IndexPage, ResultPage}
import support.steps.{PlatformOperatorSteps, SubscriptionSteps}

class AddAndViewReportingNotificationJourneysSpec extends OperatorBaseSpec {

  private val indexPage                 = IndexPage()
  private val resultPage                = ResultPage
  private val whichPlatformOperatorPage = WhichPlatformOperatorPage()

  Feature("Add Operator Notification Journeys") {
    Scenario("Add operator notification with RPO Notification for extended and active seller due diligence") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add notification")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectReportingPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      DueDiligencePage(platformOperatorId).selectExtendedTimeLimit().selectActiveSellerDue().continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      SuccessPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Add operator notification with EPO Notification") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add notification")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectExcludedPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      SuccessPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Add multiple operator notification with one RPO and one EPO Notification") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add multiple notifications")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectReportingPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      DueDiligencePage(platformOperatorId).selectExtendedTimeLimit().selectActiveSellerDue().continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      SuccessPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      ViewPage(platformOperatorId).selectYes().continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectExcludedPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      SuccessPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

      When("On view page select No")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      ViewPage(platformOperatorId).selectNo().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("View multiple operator notification with one RPO and one EPO Notification") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add multiple notifications")

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectReportingPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      DueDiligencePage(platformOperatorId).selectExtendedTimeLimit().selectActiveSellerDue().continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      SuccessPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      ViewPage(platformOperatorId).selectYes().continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectExcludedPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      CheckYourAnswersPage(platformOperatorId).continue()
      SuccessPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

      When("On view page select No")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      ViewPage(platformOperatorId).selectNo().continue()
      ManageReportingPage(platformOperatorId).clickViewReportingNotification()
      ViewPage(platformOperatorId).selectNo().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Add operator notification with EPO Notification where we have multiple operators") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      And("Already added more than one platform operator")
      val platformOperatorId1 = PlatformOperatorSteps.addPlatformOperator("Simpsons Ltd.")
      PlatformOperatorSteps.addPlatformOperator("Family Guy Ltd.")

      When("Add notification")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.withPlatformOperator("Simpsons Ltd.").continue()
      PlatformNotificationStartPage(platformOperatorId1).continue()
      AddNotificationPage(platformOperatorId1).selectExcludedPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId1).continue()
      CheckYourAnswersPage(platformOperatorId1).continue()
      SuccessPage(platformOperatorId1).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }
  }
}
