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
import support.builders.UserCredentialsBuilder.anIndividualUser
import support.steps.SubscriptionSteps
import uk.gov.hmrc.ui.pages.contactdetails.individual._
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, IndexPage, ResultPage}

class IndividualJourneysSpec extends BaseSpec {

  private val loginPage                         = AuthLoginStubPage
  private val indexPage                         = IndexPage()
  private val individualContactDetailsPage      = IndividualContactDetailsPage()
  private val individualEmailAddressPage        = IndividualEmailAddressPage()
  private val individualEmailAddressUpdatedPage = IndividualEmailAddressUpdatedPage()
  private val canPhoneIndividualPage            = CanPhoneIndividualPage()
  private val individualPhoneNumberRemovedPage  = IndividualPhoneNumberRemovedPage()
  private val individualPhoneNumberPage         = IndividualPhoneNumberPage()
  private val individualPhoneNumberUpdatedPage  = IndividualPhoneNumberUpdatedPage()
  private val resultPage                        = ResultPage

  Feature("Individual Journeys") {
    Scenario("Change Contact Details") {
      Given("Individual user is subscribed")
      val enrolmentsData = SubscriptionSteps.subscribedIndividualEnrolment()

      And("Individual user logs in")
      loginPage.show()
      loginPage.loginAs(anIndividualUser.copy(enrolmentsData = enrolmentsData))

      When("Change contact details is clicked")
      indexPage.clickChangeContactDetails()

      And("Email address updated")
      individualContactDetailsPage.clickChangeEmail()
      individualEmailAddressPage.withEmail("rowan.bean.updated@example.com").continue()
      individualEmailAddressUpdatedPage.continue()

      And("Can we contact you by telephone and phone number are updated")
      individualContactDetailsPage.clickChangeCanWeContactYouByPhone()
      canPhoneIndividualPage.selectNo().continue()
      individualPhoneNumberRemovedPage.continue()
      individualContactDetailsPage.clickChangeCanWeContactYouByPhone()
      canPhoneIndividualPage.selectYes().continue()
      individualPhoneNumberPage.withPhoneNumber("0200000002").continue()
      individualPhoneNumberUpdatedPage.continue()

      And("Your contact details 'Back' button is clicked")
      individualContactDetailsPage.continue()

      Then("The result page should be 'Manage your Digital Platform Reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }
  }
}
