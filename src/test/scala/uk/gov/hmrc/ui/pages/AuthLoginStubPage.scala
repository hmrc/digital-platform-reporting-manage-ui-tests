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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.ui.models.UserCredentials

object AuthLoginStubPage extends BasePage("") {

  private val title: String  = "Authority Wizard"
  private val redirectionUrl = s"$baseUrl/manage-reporting"

  private val redirectUrlSelector     = By.cssSelector("#redirectionUrl")
  private val affinityGroupSelector   = By.cssSelector("#affinityGroupSelect")
  private val credentialRoleSelector  = By.cssSelector("#credential-role-select")
  private val enrolmentKeySelector    = By.cssSelector("#enrolment\\[0\\]\\.name")
  private val identifierNameSelector  = By.cssSelector("#input-0-0-name")
  private val identifierValueSelector = By.cssSelector("#input-0-0-value")
  private val submitSelector          = By.cssSelector("#submit-top")

  override val url: String = TestEnvironment.url("auth-login-stub") + "/gg-sign-in"

  def show(): Unit = {
    get(url)
    assert(getTitle == title, s"Title was: $getTitle, but expected is $title")
  }

  def loginAs(userCredentials: UserCredentials): Unit = {
    sendKeys(redirectUrlSelector, redirectionUrl)
    selectByValue(affinityGroupSelector, userCredentials.affinityGroup.toString)
    selectByValue(credentialRoleSelector, userCredentials.credentialRole.toString)
    sendKeys(enrolmentKeySelector, userCredentials.enrolmentsData.enrolmentKey)
    sendKeys(identifierNameSelector, userCredentials.enrolmentsData.identifierName)
    sendKeys(identifierValueSelector, userCredentials.enrolmentsData.identifierValue)
    click(submitSelector)
  }
}
