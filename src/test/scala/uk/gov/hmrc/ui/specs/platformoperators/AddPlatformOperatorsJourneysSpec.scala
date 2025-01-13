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
import support.pages.platformoperators.add._
import support.pages.{IndexPage, ResultPage}
import support.steps.SubscriptionSteps

class AddPlatformOperatorsJourneysSpec extends OperatorBaseSpec {

  private val indexPage                        = IndexPage()
  private val startPage                        = StartPage()
  private val businessNamePage                 = BusinessNamePage()
  private val hasTradingNamePage               = HasTradingNamePage()
  private val tradingNamePage                  = TradingNamePage()
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
  private val ukAddressPage                    = UkAddressPage()
  private val crownDependenciesAddressPage     = CrownDependenciesAddressPage()
  private val ukTaxIdentifiersPage             = UkTaxIdentifiersPage()
  private val utrPage                          = UtrPage()
  private val crnPage                          = CrnPage()
  private val vrnPage                          = VrnPage()
  private val emprefPage                       = EmprefPage()
  private val chrnPage                         = ChrnPage()
  private val resultPage                       = ResultPage

  Feature("Add Platform Operator Journeys") {
    Scenario("Add Platform operator with TIN, tax resident in UK, registered in International") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      When("Add Platform operator is added")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("The Simpsons Ltd.").continue()
      hasTradingNamePage.selectYes().continue()
      tradingNamePage.withName("Simpsons Ltd.").continue()
      ukTaxIdentifiersPage
        .selectUniqueTaxPayerReference()
        .selectCompanyRegistrationNumber()
        .selectVatRegistrationNumber()
        .selectEmployerPAYEReferenceNumber()
        .selectHmrcCharityReference()
        .continue()
      utrPage.withUtr("1234567890").continue()
      crnPage.withCompanyRegistrationNumber("AB123456").continue()
      vrnPage.withVatRegistrationNumber("GB123456789").continue()
      emprefPage.withEmployerPAYEReference("123/AB456").continue()
      chrnPage.withHmrcCharityReference("X12345").continue()
      registeredInUkPage.selectInternational().continue()
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
      platformOperatorAddedPage.clickAddAnotherPlatformOperator()
      startPage.clickBack()
      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Add Platform operator with TIN, tax resident in UK, registered in UK") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      When("Add Platform operator is added")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("Mr Bean Ltd.").continue()
      hasTradingNamePage.selectYes().continue()
      tradingNamePage.withName("Rowan Bean Ltd.").continue()
      ukTaxIdentifiersPage.selectUniqueTaxPayerReference()
      ukTaxIdentifiersPage.selectCompanyRegistrationNumber()
      ukTaxIdentifiersPage.selectVatRegistrationNumber()
      ukTaxIdentifiersPage.selectEmployerPAYEReferenceNumber()
      ukTaxIdentifiersPage.selectHmrcCharityReference()
      ukTaxIdentifiersPage.continue()
      utrPage.withUtr("1234567890").continue()
      crnPage.withCompanyRegistrationNumber("AB123456").continue()
      vrnPage.withVatRegistrationNumber("GB123456789").continue()
      emprefPage.withEmployerPAYEReference("123/AB456").continue()
      chrnPage.withHmrcCharityReference("X12345").continue()
      registeredInUkPage.selectUk().continue()
      ukAddressPage.withAddress("Flat 2, 12 Arbour Road", "Highbury", "RR1 1RR").continue()
      primaryContactNamePage.withName("Rowan Bean").continue()
      primaryContactEmailAddressPage.withEmail("rowan.pean@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      hasSecondaryContactPage.clickBack()
      canPhonePrimaryContactPage.selectYes().continue()
      primaryContactPhoneNumberPage.withPhoneNumber("020 12345678").continue()
      hasSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      hasSecondaryContactPage.selectYes().continue()
      secondaryContactNamePage.withName("Irma Gobb").continue()
      secondaryContactEmailAddressPage.withEmail("irma.gobb@example.com").continue()
      canPhoneSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      canPhoneSecondaryContactPage.selectYes().continue()
      secondaryContactPhoneNumberPage.withPhoneNumber("020 12345679").continue()
      checkYourAnswersPage.continue()
      platformOperatorAddedPage.clickAddAnotherPlatformOperator()
      startPage.clickBack()
      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("Add Platform operator with TIN, tax resident in UK, registered in JerseyGuernseyIoM") {
      Given("Newly subscribed Organisation user")
      SubscriptionSteps.newlySubscribedOrganisation()

      When("Add Platform operator is added")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("Mr Bean Ltd.").continue()
      hasTradingNamePage.selectYes().continue()
      tradingNamePage.withName("Rowan Bean Ltd.").continue()
      ukTaxIdentifiersPage.selectUniqueTaxPayerReference()
      ukTaxIdentifiersPage.selectCompanyRegistrationNumber()
      ukTaxIdentifiersPage.selectVatRegistrationNumber()
      ukTaxIdentifiersPage.selectEmployerPAYEReferenceNumber()
      ukTaxIdentifiersPage.selectHmrcCharityReference()
      ukTaxIdentifiersPage.continue()
      utrPage.withUtr("1234567890").continue()
      crnPage.withCompanyRegistrationNumber("AB123456").continue()
      vrnPage.withVatRegistrationNumber("GB123456789").continue()
      emprefPage.withEmployerPAYEReference("123/AB456").continue()
      chrnPage.withHmrcCharityReference("X12345").continue()
      registeredInUkPage.selectJerseyGuernseyIoM().continue()
      crownDependenciesAddressPage.withAddress("Flat 2, 12 Arbour Road", "Highbury", "RR1 1RR", "Jersey").continue()
      primaryContactNamePage.withName("Rowan Bean").continue()
      primaryContactEmailAddressPage.withEmail("rowan.pean@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      hasSecondaryContactPage.clickBack()
      canPhonePrimaryContactPage.selectYes().continue()
      primaryContactPhoneNumberPage.withPhoneNumber("020 12345678").continue()
      hasSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      hasSecondaryContactPage.selectYes().continue()
      secondaryContactNamePage.withName("Irma Gobb").continue()
      secondaryContactEmailAddressPage.withEmail("irma.gobb@example.com").continue()
      canPhoneSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.clickBack()
      canPhoneSecondaryContactPage.selectYes().continue()
      secondaryContactPhoneNumberPage.withPhoneNumber("020 12345679").continue()
      checkYourAnswersPage.continue()
      platformOperatorAddedPage.clickAddAnotherPlatformOperator()
      startPage.clickBack()
      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }
  }
}
