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

package uk.gov.hmrc.ui.pages.contactdetails.organisation

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

case class OrganisationContactDetailsPage() extends BasePage("/contact-details/view-contact-details") {

  def clickChangeFirstContact(): OrganisationContactDetailsPage =
    click("div.govuk-summary-card:nth-child(2) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")

  def clickChangeSecondContact(): OrganisationContactDetailsPage =
    click("div.govuk-summary-card:nth-child(3) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")

  private def click(cssSelector: String): OrganisationContactDetailsPage = {
    assertUrl(url)
    click(By.cssSelector(cssSelector))
    this
  }
}