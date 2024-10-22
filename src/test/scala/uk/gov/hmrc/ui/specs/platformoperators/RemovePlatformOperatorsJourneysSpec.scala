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
import uk.gov.hmrc.ui.pages.platformoperators.add._
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, IndexPage, ResultPage}

class RemovePlatformOperatorsJourneysSpec extends BaseSpec {

  private val loginPage                      = AuthLoginStubPage
  private val indexPage                      = IndexPage()
  private val startPage                      = StartPage()
  private val businessNamePage               = BusinessNamePage()
  private val hasTradingNamePage             = HasTradingNamePage()
  private val hasTaxIdentifierPage           = HasTaxIdentifierPage()
  private val registeredInUkPage             = RegisteredInUkPage()
  private val internationalAddressPage       = InternationalAddressPage()
  private val primaryContactNamePage         = PrimaryContactNamePage()
  private val primaryContactEmailAddressPage = PrimaryContactEmailAddressPage()
  private val canPhonePrimaryContactPage     = CanPhonePrimaryContactPage()
  private val hasSecondaryContactPage        = HasSecondaryContactPage()
  private val checkYourAnswersPage           = CheckYourAnswersPage()
  private val platformOperatorAddedPage      = PlatformOperatorAddedPage()
  private val platformOperatorsPage          = PlatformOperatorsPage()
  private val resultPage                     = ResultPage

  Feature("Remove Platform Operator Journeys") {
    Scenario("Remove Platform operator") {
      Given("Organisation user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedOrganisationEnrolment()

      And("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser.copy(enrolmentsData = enrolmentsData))

      And("Platform operator is added")
      indexPage.clickAddPlatformOperator()
      startPage.continue()
      businessNamePage.withName("The Simpsons Ltd.").continue()
      hasTradingNamePage.selectNo().continue()
      hasTaxIdentifierPage.selectNo().continue()
      registeredInUkPage.selectNo().continue()
      internationalAddressPage.withAddress("742 Evergreen Terrace", "Springfield", "90210", "United States").continue()
      primaryContactNamePage.withName("Marge Simpson").continue()
      primaryContactEmailAddressPage.withEmail("marge.simpson@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      hasSecondaryContactPage.selectNo().continue()
      checkYourAnswersPage.continue()
      platformOperatorAddedPage.clickManageYourDigitalPlatformReporting()
      indexPage.clickViewPlatformOperators()
      platformOperatorsPage.clickAddNewPlatformOperator()
      startPage.clickBack()
      val platformOperatorId   = platformOperatorsPage.clickView(index = 1)
      val platformOperatorPage = PlatformOperatorPage(platformOperatorId)

      When("Platform Operator is removed")
      platformOperatorPage.clickRemovePlatformOperator()
      val removePlatformOperatorPage  = RemovePlatformOperatorPage(platformOperatorId)
      removePlatformOperatorPage.selectNo().continue()
      platformOperatorPage.clickRemovePlatformOperator()
      removePlatformOperatorPage.selectYes().continue()
      val platformOperatorRemovedPage = PlatformOperatorRemovedPage(platformOperatorId)
      platformOperatorRemovedPage.clickBackToViewPlatformOperators()

      Then("The result page should be 'Platform operators'")
      resultPage.url       should include("/platform-operator/view")
      resultPage.heading shouldBe "Platform operators"
    }
  }
}
*/
