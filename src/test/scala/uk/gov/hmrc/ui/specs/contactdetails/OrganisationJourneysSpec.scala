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

package uk.gov.hmrc.ui.specs.contactdetails

import support.BaseSpec
import support.builders.UserCredentialsBuilder.anOrganisationUser
import uk.gov.hmrc.ui.pages.contactdetails.organisation._
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, IndexPage, ResultPage}

class OrganisationJourneysSpec extends BaseSpec {

  private val loginPage                        = AuthLoginStubPage
  private val indexPage                        = IndexPage()
  private val organisationContactDetailsPage   = OrganisationContactDetailsPage()
  private val primaryContactNamePage           = PrimaryContactNamePage()
  private val primaryContactEmailAddressPage   = PrimaryContactEmailAddressPage()
  private val canPhonePrimaryContactPage       = CanPhonePrimaryContactPage()
  private val primaryContactUpdatedPage        = PrimaryContactUpdatedPage()
  private val primaryContactPhoneNumberPage    = PrimaryContactPhoneNumberPage()
  private val hasSecondaryContactPage          = HasSecondaryContactPage()
  private val secondaryContactNamePage         = SecondaryContactNamePage()
  private val secondaryContactEmailAddressPage = SecondaryContactEmailAddressPage()
  private val canPhoneSecondaryContactPage     = CanPhoneSecondaryContactPage()
  private val secondaryContactPhoneNumberPage  = SecondaryContactPhoneNumberPage()
  private val secondaryContactUpdatedPage      = SecondaryContactUpdatedPage()
  private val resultPage                       = ResultPage

  Feature("Organisation Journeys") {
    Scenario("Change Contact Details") {
      Given("Organisation user logs in")
      loginPage.show()
      loginPage.loginAs(anOrganisationUser)

      When("Change contact details is clicked")
      indexPage.clickChangeContactDetails()

      And("First contact details are updated")
      organisationContactDetailsPage.clickChangeFirstContact()
      primaryContactNamePage.withName("KGF Corporation").continue()
      primaryContactEmailAddressPage.withEmail("kgf.updated@example.com").continue()
      canPhonePrimaryContactPage.selectNo().continue()
      primaryContactUpdatedPage.continue()
      organisationContactDetailsPage.clickChangeFirstContact()
      primaryContactNamePage.continue()
      primaryContactEmailAddressPage.continue()
      canPhonePrimaryContactPage.selectYes().continue()
      primaryContactPhoneNumberPage.withPhoneNumber("0202333222").continue()
      primaryContactUpdatedPage.continue()

      And("Second contact details are updated")
      organisationContactDetailsPage.clickChangeSecondContact()
      hasSecondaryContactPage.selectYes().continue()
      secondaryContactNamePage.withName("TGIF Corporation").continue()
      secondaryContactEmailAddressPage.withEmail("tgif.updated@example.com").continue()
      canPhoneSecondaryContactPage.selectYes().continue()
      secondaryContactPhoneNumberPage.withPhoneNumber("0202222333").continue()
      secondaryContactUpdatedPage.continue()
      organisationContactDetailsPage.clickChangeSecondContact()
      hasSecondaryContactPage.selectNo().continue()
      secondaryContactUpdatedPage.continue()

      And("Your contact details 'Back' is clicked")
      organisationContactDetailsPage.continue()

      Then("The result page should be 'Manage your Digital Platform Reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your Digital Platform Reporting"
    }
  }
}
