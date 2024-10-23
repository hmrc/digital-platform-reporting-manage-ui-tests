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
import support.steps.SubscriptionSteps
import uk.gov.hmrc.ui.pages.platformoperators._
import uk.gov.hmrc.ui.pages.platformoperators.add._
import uk.gov.hmrc.ui.pages.reportingnotification._
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, IndexPage, ResultPage}

class AddAndViewReportingNotificationJourneysSpec extends BaseSpec {

  private val loginPage                        = AuthLoginStubPage
  private val indexPage                        = IndexPage()
  private val startPage                        = StartPage()
  private val businessNamePage                 = BusinessNamePage()
  private val hasTradingNamePage               = HasTradingNamePage()
  private val tradingNamePage                  = TradingNamePage()
  private val hasTaxIdentifierPage             = HasTaxIdentifierPage()
  private val registeredInUkPage               = RegisteredInUkPage()
  private val internationalAddressPage         = InternationalAddressPage()
  private val primaryContactNamePage           = PrimaryContactNamePage()
  private val primaryContactEmailAddressPage   = PrimaryContactEmailAddressPage()
  private val canPhonePrimaryContactPage       = CanPhonePrimaryContactPage()
  private val primaryContactPhoneNumberPage    = PrimaryContactPhoneNumberPage()
  private val hasSecondaryContactPage          = HasSecondaryContactPage()
  private val secondaryContactNamePage         = SecondaryContactNamePage()
  private val secondaryContactEmailAddressPage = SecondaryContactEmailAddressPage()
  private val canPhoneSecondaryContactPage     = CanPhoneSecondaryContactPage()
  private val secondaryContactPhoneNumberPage  = SecondaryContactPhoneNumberPage()
  private val checkYourAnswersPage             = CheckYourAnswersPage()
  private val platformOperatorAddedPage        = PlatformOperatorAddedPage()
  private val resultPage                       = ResultPage

  private val platformOperatorsPage     = PlatformOperatorsPage()
  private val whichPlatformOperatorPage = WhichPlatformOperatorPage()

  Feature("Add Operator Notification Journeys") {
    Scenario("Add operator notification with RPO Notification for extended and active seller due diligence") {
      Given("Organisation user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedOrganisationEnrolment()

      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("The Simpsons Ltd.").continue()
      hasTradingNamePage.selectNo().continue()
      hasTaxIdentifierPage.clickBack()
      hasTradingNamePage.selectYes().continue()
      tradingNamePage.withName("Simpsons Ltd.").continue()
      hasTaxIdentifierPage.selectNo().continue()
      registeredInUkPage.selectNo().continue()
      internationalAddressPage.withAddress("742 Evergreen Terrace", "Springfield", "90210", "United States").continue()
      primaryContactNamePage.withName("Marge Simpson").continue()
      primaryContactEmailAddressPage.withEmail("marge.simpson@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      hasSecondaryContactPage.clickBack()
      canPhonePrimaryContactPage.selectYes().continue()
      primaryContactPhoneNumberPage.withPhoneNumber("078 12345678").continue()
      hasSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      hasSecondaryContactPage.selectYes().continue()
      secondaryContactNamePage.withName("Lisa Simpson").continue()
      secondaryContactEmailAddressPage.withEmail("lisa.simpson@example.com").continue()
      canPhoneSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      canPhoneSecondaryContactPage.selectYes().continue()
      secondaryContactPhoneNumberPage.withPhoneNumber("078 12345679").continue()
      checkYourAnswersPage.continue()

      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()
      indexPage.clickViewPlatformOperators()
      val platformOperatorId = platformOperatorsPage.getOperatorId(index = 1)
      indexPage.showManageOperator()

      When("Add notification")
      val addNotificationPage              = AddNotificationPage(platformOperatorId)
      val firstPeriodPage                  = FirstPeriodPage(platformOperatorId)
      val dueDiligencePage                 = DueDiligencePage(platformOperatorId)
      val notificationCheckYourAnswersPage = NotificationCheckYourAnswersPage(platformOperatorId)
      val notificationSuccessPage          = NotificationSuccessPage(platformOperatorId)

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      PlatformNotificationStartPage(platformOperatorId).continue()
      addNotificationPage.selectReportingPlatformOperator().continue()
      firstPeriodPage.continue()
      dueDiligencePage.selectExtendedTimelimit()
      dueDiligencePage.selectActiveSellerDue()
      dueDiligencePage.continue()
      notificationCheckYourAnswersPage.continue()
      notificationSuccessPage.clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

    }

    Scenario("Add operator notification with EPO Notification") {
      Given("Organisation user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedOrganisationEnrolment()
      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("The Simpsons Ltd.").continue()
      hasTradingNamePage.selectNo().continue()
      hasTaxIdentifierPage.clickBack()
      hasTradingNamePage.selectYes().continue()
      tradingNamePage.withName("Simpsons Ltd.").continue()
      hasTaxIdentifierPage.selectNo().continue()
      registeredInUkPage.selectNo().continue()
      internationalAddressPage.withAddress("742 Evergreen Terrace", "Springfield", "90210", "United States").continue()
      primaryContactNamePage.withName("Marge Simpson").continue()
      primaryContactEmailAddressPage.withEmail("marge.simpson@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      hasSecondaryContactPage.clickBack()
      canPhonePrimaryContactPage.selectYes().continue()
      primaryContactPhoneNumberPage.withPhoneNumber("078 12345678").continue()
      hasSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      hasSecondaryContactPage.selectYes().continue()
      secondaryContactNamePage.withName("Lisa Simpson").continue()
      secondaryContactEmailAddressPage.withEmail("lisa.simpson@example.com").continue()
      canPhoneSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      canPhoneSecondaryContactPage.selectYes().continue()
      secondaryContactPhoneNumberPage.withPhoneNumber("078 12345679").continue()
      checkYourAnswersPage.continue()

      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()
      indexPage.clickViewPlatformOperators()
      val platformOperatorId = platformOperatorsPage.getOperatorId(index = 1)
      indexPage.showManageOperator()

      When("Add notification")
      val platformNotificationStartPage    = PlatformNotificationStartPage(platformOperatorId)
      val addNotificationPage              = AddNotificationPage(platformOperatorId)
      val firstPeriodPage                  = FirstPeriodPage(platformOperatorId)
      val notificationCheckYourAnswersPage = NotificationCheckYourAnswersPage(platformOperatorId)
      val notificationSuccessPage          = NotificationSuccessPage(platformOperatorId)

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      platformNotificationStartPage.continue()
      addNotificationPage.selectExcludedPlatformOperator()
      addNotificationPage.continue()
      firstPeriodPage.continue()
      notificationCheckYourAnswersPage.continue()
      notificationSuccessPage.clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

    }

    Scenario("Add multiple operator notification with one RPO and one EPO Notification") {
      Given("Organisation user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedOrganisationEnrolment()
      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("The Simpsons Ltd.").continue()
      hasTradingNamePage.selectNo().continue()
      hasTaxIdentifierPage.clickBack()
      hasTradingNamePage.selectYes().continue()
      tradingNamePage.withName("Simpsons Ltd.").continue()
      hasTaxIdentifierPage.selectNo().continue()
      registeredInUkPage.selectNo().continue()
      internationalAddressPage.withAddress("742 Evergreen Terrace", "Springfield", "90210", "United States").continue()
      primaryContactNamePage.withName("Marge Simpson").continue()
      primaryContactEmailAddressPage.withEmail("marge.simpson@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      hasSecondaryContactPage.clickBack()
      canPhonePrimaryContactPage.selectYes().continue()
      primaryContactPhoneNumberPage.withPhoneNumber("078 12345678").continue()
      hasSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      hasSecondaryContactPage.selectYes().continue()
      secondaryContactNamePage.withName("Lisa Simpson").continue()
      secondaryContactEmailAddressPage.withEmail("lisa.simpson@example.com").continue()
      canPhoneSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      canPhoneSecondaryContactPage.selectYes().continue()
      secondaryContactPhoneNumberPage.withPhoneNumber("078 12345679").continue()
      checkYourAnswersPage.continue()

      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()
      indexPage.clickViewPlatformOperators()
      val platformOperatorId = platformOperatorsPage.getOperatorId(index = 1)
      indexPage.showManageOperator()

      When("Add multiple notifications")
      val platformNotificationStartPage    = PlatformNotificationStartPage(platformOperatorId)
      val addNotificationPage              = AddNotificationPage(platformOperatorId)
      val firstPeriodPage                  = FirstPeriodPage(platformOperatorId)
      val dueDiligencePage                 = DueDiligencePage(platformOperatorId)
      val notificationCheckYourAnswersPage = NotificationCheckYourAnswersPage(platformOperatorId)
      val notificationSuccessPage          = NotificationSuccessPage(platformOperatorId)
      val notificationViewPage             = NotificationViewPage(platformOperatorId)

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      platformNotificationStartPage.continue()
      addNotificationPage.selectReportingPlatformOperator()
      addNotificationPage.continue()
      firstPeriodPage.continue()
      dueDiligencePage.selectExtendedTimelimit()
      dueDiligencePage.selectActiveSellerDue()
      dueDiligencePage.continue()
      notificationCheckYourAnswersPage.continue()
      notificationSuccessPage.clickManageYourDigitalPlatformReproting()

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      notificationViewPage.selectYes()
      notificationViewPage.continue()
      platformNotificationStartPage.continue()
      addNotificationPage.selectExcludedPlatformOperator()
      addNotificationPage.continue()
      firstPeriodPage.continue()
      notificationCheckYourAnswersPage.continue()
      notificationSuccessPage.clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

      When("On view page select No")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      notificationViewPage.selectNo()
      notificationViewPage.continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("View multiple operator notification with one RPO and one EPO Notification") {
      Given("Organisation user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedOrganisationEnrolment()
      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))
      And("Already added the platform operator")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("The Simpsons Ltd.").continue()
      hasTradingNamePage.selectNo().continue()
      hasTaxIdentifierPage.clickBack()
      hasTradingNamePage.selectYes().continue()
      tradingNamePage.withName("Simpsons Ltd.").continue()
      hasTaxIdentifierPage.selectNo().continue()
      registeredInUkPage.selectNo().continue()
      internationalAddressPage.withAddress("742 Evergreen Terrace", "Springfield", "90210", "United States").continue()
      primaryContactNamePage.withName("Marge Simpson").continue()
      primaryContactEmailAddressPage.withEmail("marge.simpson@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      hasSecondaryContactPage.clickBack()
      canPhonePrimaryContactPage.selectYes().continue()
      primaryContactPhoneNumberPage.withPhoneNumber("078 12345678").continue()
      hasSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      hasSecondaryContactPage.selectYes().continue()
      secondaryContactNamePage.withName("Lisa Simpson").continue()
      secondaryContactEmailAddressPage.withEmail("lisa.simpson@example.com").continue()
      canPhoneSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      canPhoneSecondaryContactPage.selectYes().continue()
      secondaryContactPhoneNumberPage.withPhoneNumber("078 12345679").continue()
      checkYourAnswersPage.continue()

      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()
      indexPage.clickViewPlatformOperators()
      val platformOperatorId = platformOperatorsPage.getOperatorId(index = 1)
      indexPage.showManageOperator()

      When("Add multiple notifications")
      val platformNotificationStartPage    = PlatformNotificationStartPage(platformOperatorId)
      val addNotificationPage              = AddNotificationPage(platformOperatorId)
      val firstPeriodPage                  = FirstPeriodPage(platformOperatorId)
      val dueDiligencePage                 = DueDiligencePage(platformOperatorId)
      val notificationCheckYourAnswersPage = NotificationCheckYourAnswersPage(platformOperatorId)
      val notificationSuccessPage          = NotificationSuccessPage(platformOperatorId)
      val notificationViewPage             = NotificationViewPage(platformOperatorId)
      val manageViewPage                   = ManageReportingPage(platformOperatorId)

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      platformNotificationStartPage.continue()
      addNotificationPage.selectReportingPlatformOperator()
      addNotificationPage.continue()
      firstPeriodPage.continue()
      dueDiligencePage.selectExtendedTimelimit()
      dueDiligencePage.selectActiveSellerDue()
      dueDiligencePage.continue()
      notificationCheckYourAnswersPage.continue()
      notificationSuccessPage.clickManageYourDigitalPlatformReproting()

      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      notificationViewPage.selectYes()
      notificationViewPage.continue()
      platformNotificationStartPage.continue()
      addNotificationPage.selectExcludedPlatformOperator()
      addNotificationPage.continue()
      firstPeriodPage.continue()
      notificationCheckYourAnswersPage.continue()
      notificationSuccessPage.clickManageYourDigitalPlatformReproting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"

      When("On view page select No")
      indexPage.clickAddReportingNotification()
      whichPlatformOperatorPage.continue()
      notificationViewPage.selectNo()
      notificationViewPage.continue()
      manageViewPage.clickViewReportingNotification()
      notificationViewPage.selectNo()
      notificationViewPage.continue()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

  }
}
