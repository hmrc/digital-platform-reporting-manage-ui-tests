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

import support.BaseSpec
import support.builders.UserCredentialsBuilder.anOrganisationUser
import support.steps.{PlatformOperatorSteps, SubscriptionSteps}
import uk.gov.hmrc.ui.pages.platformoperators._
import uk.gov.hmrc.ui.pages.platformoperators.add._
import uk.gov.hmrc.ui.pages.reportingnotification._
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, IndexPage, ResultPage}

class AddAndViewReportingNotificationJourneysSpec extends BaseSpec {

  private val loginPage                 = AuthLoginStubPage
  private val indexPage                 = IndexPage()
  private val resultPage                = ResultPage
  private val whichPlatformOperatorPage = WhichPlatformOperatorPage()

  Feature("Add Operator Notification Journeys") {
    Scenario("Add operator notification with RPO Notification for extended and active seller due diligence") {
      Given("Organisation user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedOrganisationEnrolment()

      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add notification")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectReportingPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      DueDiligencePage(platformOperatorId).selectExtendedTimelimit().selectActiveSellerDue().continue()
      NotificationCheckYourAnswersPage(platformOperatorId).continue()
      NotificationSuccessPage(platformOperatorId).clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

    }

    Scenario("Add operator notification with EPO Notification") {
      Given("Organisation user is subscribed")
      val enrolmentsData     = SubscriptionSteps.subscribedOrganisationEnrolment()
      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add notification")

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectExcludedPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      NotificationCheckYourAnswersPage(platformOperatorId).continue()
      NotificationSuccessPage(platformOperatorId).clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

    }

    Scenario("Add multiple operator notification with one RPO and one EPO Notification") {
      Given("Organisation user is subscribed")
      val enrolmentsData     = SubscriptionSteps.subscribedOrganisationEnrolment()
      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add multiple notifications")

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectReportingPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      DueDiligencePage(platformOperatorId).selectExtendedTimelimit().selectActiveSellerDue().continue()
      NotificationCheckYourAnswersPage(platformOperatorId).continue()
      NotificationSuccessPage(platformOperatorId).clickManageYourDigitalPlatformReproting()

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      NotificationViewPage(platformOperatorId).selectYes().continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectExcludedPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      NotificationCheckYourAnswersPage(platformOperatorId).continue()
      NotificationSuccessPage(platformOperatorId).clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

      When("On view page select No")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      NotificationViewPage(platformOperatorId).selectNo().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("View multiple operator notification with one RPO and one EPO Notification") {
      Given("Organisation user is subscribed")
      val enrolmentsData     = SubscriptionSteps.subscribedOrganisationEnrolment()
      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      val platformOperatorId = PlatformOperatorSteps.addPlatformOperator()

      When("Add multiple notifications")

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectReportingPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      DueDiligencePage(platformOperatorId).selectExtendedTimelimit().selectActiveSellerDue().continue()
      NotificationCheckYourAnswersPage(platformOperatorId).continue()
      NotificationSuccessPage(platformOperatorId).clickManageYourDigitalPlatformReproting()

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      NotificationViewPage(platformOperatorId).selectYes().continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      AddNotificationPage(platformOperatorId).selectExcludedPlatformOperator().continue()
      FirstPeriodPage(platformOperatorId).continue()
      NotificationCheckYourAnswersPage(platformOperatorId).continue()
      NotificationSuccessPage(platformOperatorId).clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

      When("On view page select No")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      NotificationViewPage(platformOperatorId).selectNo().continue()
      ManageReportingPage(platformOperatorId).clickViewReportingNotification()
      NotificationViewPage(platformOperatorId).selectNo().continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }
  }
}
