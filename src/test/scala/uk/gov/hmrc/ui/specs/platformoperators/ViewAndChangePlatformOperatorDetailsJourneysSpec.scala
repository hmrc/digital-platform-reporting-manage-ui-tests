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

package uk.gov.hmrc.ui.specs.platformoperators

import support.OperatorBaseSpec
import support.pages.platformoperators._
import support.pages.platformoperators.add.{CanPhonePrimaryContactPage, PlatformOperatorAddedPage}
import support.pages.{IndexPage, ResultPage}
import support.steps.SubscriptionSteps

class ViewAndChangePlatformOperatorDetailsJourneysSpec extends OperatorBaseSpec {

  private val indexPage                         = IndexPage()
  private val startPage                         = StartPage()
  private val addBusinessNamePage               = add.BusinessNamePage()
  private val addHasTradingNamePage             = add.HasTradingNamePage()
  private val ukTaxIdentifiersPage              = UkTaxIdentifiersPage()
  private val utrPage                           = UtrPage()
  private val addRegisteredInUkPage             = add.RegisteredInUkPage()
  private val addInternationalAddressPage       = add.InternationalAddressPage()
  private val addPrimaryContactNamePage         = add.PrimaryContactNamePage()
  private val addPrimaryContactEmailAddressPage = add.PrimaryContactEmailAddressPage()
  private val addCanPhonePrimaryContactPage     = CanPhonePrimaryContactPage()
  private val addHasSecondaryContactPage        = add.HasSecondaryContactPage()
  private val addCheckYourAnswersPage           = add.CheckYourAnswersPage()
  private val platformOperatorAddedPage         = PlatformOperatorAddedPage()
  private val platformOperatorsPage             = PlatformOperatorsPage()
  private val resultPage                        = ResultPage

  Feature("View and Change Platform Operator Journeys") {
    Scenario("View and Change Platform operator") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      And("Platform operator is added")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      addBusinessNamePage.withName("The Simpsons Ltd.").continue()
      addHasTradingNamePage.selectNo().continue()
      ukTaxIdentifiersPage.selectUniqueTaxPayerReference().continue()
      utrPage.withUtr("1234567890").continue()
      addRegisteredInUkPage.selectNo().continue()
      addInternationalAddressPage
        .withAddress("742 Evergreen Terrace", "Springfield", "90210", "United States")
        .continue()
      addPrimaryContactNamePage.withName("Marge Simpson").continue()
      addPrimaryContactEmailAddressPage.withEmail("marge.simpson@example.com").continue()
      addCanPhonePrimaryContactPage.selectNo().continue()
      addHasSecondaryContactPage.selectNo().continue()
      addCheckYourAnswersPage.continue()
      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()
      indexPage.clickViewPlatformOperators()
      platformOperatorsPage.clickAddNewPlatformOperator()
      startPage.clickBack()
      val platformOperatorId = platformOperatorsPage.clickView(index = 1)

      When("Platform Operator details are updated")
      val platformOperatorPage   = PlatformOperatorPage(platformOperatorId)
      platformOperatorPage.clickViewAndChangeDetails()
      val updateCheckYourAnswers = update.CheckYourAnswersPage(platformOperatorId)
      updateCheckYourAnswers.clickChangeBusinessName()
      update.BusinessNamePage(platformOperatorId).withName("Simpsons Ltd.").continue()
      updateCheckYourAnswers.clickTradeUnderDifferentName()
      update.HasTradingNamePage(platformOperatorId).selectYes().continue()
      update.TradingNamePage(platformOperatorId).withName("The Simpsons Ltd.").continue()
      updateCheckYourAnswers.clickUkTaxIdentifiers()
      update.UkTaxIdentifiersPage(platformOperatorId).selectVatRegistrationNumber().continue()
      update.VrnPage(platformOperatorId).withVatRegistrationNumber("GB123456789").continue()
      updateCheckYourAnswers.clickRegisteredInUk()
      update.RegisteredInUkPage(platformOperatorId).selectYes().continue()
      update.UkAddressPage(platformOperatorId).clickBack()
      update.RegisteredInUkPage(platformOperatorId).selectNo().continue()
      update
        .InternationalAddressPage(platformOperatorId)
        .withAddress("742 Evergreen Terrace", "Springfield", "90210", "United States")
        .continue()
      updateCheckYourAnswers.clickChangePrimaryContactName()
      update.PrimaryContactNamePage(platformOperatorId).withName("Homer Simpson").continue()
      updateCheckYourAnswers.clickChangePrimaryContactEmail()
      update.PrimaryContactEmailAddressPage(platformOperatorId).withEmail("homer.simpson@example.com").continue()
      updateCheckYourAnswers.clickCanContactByPhone()
      update.CanPhonePrimaryContactPage(platformOperatorId).selectYes().continue()
      update.PrimaryContactPhoneNumberPage(platformOperatorId).withPhoneNumber("123456789").continue()
      updateCheckYourAnswers.clickChangeSecondContact()
      update.HasSecondaryContactPage(platformOperatorId).selectYes().continue()
      update.SecondaryContactNamePage(platformOperatorId).withName("Marge Simpson").continue()
      update.SecondaryContactEmailAddressPage(platformOperatorId).withEmail("marge.simpson@example.com").continue()
      update.CanPhoneSecondaryContactPage(platformOperatorId).selectYes().continue()
      update.SecondaryContactPhoneNumberPage(platformOperatorId).withPhoneNumber("234567899").continue()
      updateCheckYourAnswers.continue()
      update.PlatformOperatorUpdatedPage(platformOperatorId).clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/digital-platform-reporting/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }
  }
}
