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

package support.steps

import support.pages.IndexPage
import support.pages.platformoperators.add._
import support.pages.platformoperators.{PlatformOperatorsPage, StartPage}
import uk.gov.hmrc.selenium.component.PageObject

object PlatformOperatorSteps extends PageObject {

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

  private val platformOperatorsPage = PlatformOperatorsPage()

  def addPlatformOperator(platformOperator: String = "Simpsons Ltd."): String = {
    indexPage.clickAddPlatformOperator()
    startPage.continue()
    businessNamePage.withName(platformOperator).continue()
    hasTradingNamePage.selectNo().continue()
    hasTaxIdentifierPage.clickBack()
    hasTradingNamePage.selectYes().continue()
    tradingNamePage.withName(s"The $platformOperator").continue()
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
    val platformOperatorId = platformOperatorsPage.platformOperator
    platformOperatorsPage.goToManageOperator()
    platformOperatorId
  }
}
