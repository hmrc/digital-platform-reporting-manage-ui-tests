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

package uk.gov.hmrc.ui.specs

import support.BaseSpec
import support.builders.EnrolmentsDataBuilder.anEnrolmentsData
import support.builders.UserCredentialsBuilder.aUserCredentials
import support.models.auth.{Individual, Organisation, User}
import uk.gov.hmrc.ui.pages.{AuthLoginStubPage, ResultPage}

class UserAuthenticationSpec extends BaseSpec {

  private val loginPage  = AuthLoginStubPage
  private val resultPage = ResultPage

  Feature("User Authentication") {
    Scenario("User with Affinity Group of Organisation and Credential Role of User") {
      Given("user is on the authentication page")
      loginPage.show()

      When("enters AffinityGroup = Organisation and CredentialRole = User")
      loginPage.loginAs(aUserCredentials.copy(affinityGroup = Organisation, credentialRole = User))

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }

    Scenario("User with Affinity Group of Individual and Credential Role of User") {
      Given("user is on the authentication page")
      loginPage.show()

      When("enters AffinityGroup = Individual and CredentialRole = User")
      val individualCredentials = aUserCredentials.copy(
        affinityGroup = Individual,
        credentialRole = User,
        enrolmentsData = anEnrolmentsData.copy(identifierValue = "I123")
      )
      loginPage.loginAs(individualCredentials)

      Then("The result page should be 'Manage your digital platform reporting'")
      resultPage.url       should include("/digital-platform-reporting/manage-reporting")
      resultPage.heading shouldBe "Manage your digital platform reporting"
    }
  }
}
