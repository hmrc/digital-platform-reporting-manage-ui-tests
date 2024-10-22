/*
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

import support.BaseSpec
import support.builders.UserCredentialsBuilder.anOrganisationUser
import support.steps.SubscriptionSteps
import uk.gov.hmrc.ui.pages.platformoperators._
import uk.gov.hmrc.ui.pages.platformoperators.add.{CanPhonePrimaryContactPage, PlatformOperatorAddedPage}
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, IndexPage, ResultPage}

class ViewAndChangePlatformOperatorDetailsJourneysSpec extends BaseSpec {

  private val loginPage                         = AuthLoginStubPage
  private val indexPage                         = IndexPage()
  private val startPage                         = StartPage()
  private val addBusinessNamePage               = add.BusinessNamePage()
  private val addHasTradingNamePage             = add.HasTradingNamePage()
  private val addHasTaxIdentifierPage           = add.HasTaxIdentifierPage()
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
      Given("Organisation user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedOrganisationEnrolment()

      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))

      And("Platform operator is added")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      addBusinessNamePage.withName("The Simpsons Ltd.").continue()
      addHasTradingNamePage.selectNo().continue()
      addHasTaxIdentifierPage.selectNo().continue()
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
      updateCheckYourAnswers.clickChangeHasTin()
      update.HasTaxIdentifierPage(platformOperatorId).selectYes().continue()
      update.TaxResidentInUkPage(platformOperatorId).selectNo().continue()
      update.TaxResidencyCountryPage(platformOperatorId).withCountry("United States").continue()
      update.InternationalTaxIdentifierPage(platformOperatorId).withTaxIdentificationNumber("1234").continue()
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
*/
