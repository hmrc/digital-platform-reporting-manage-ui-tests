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

package uk.gov.hmrc.ui.pages.platformoperators.update

import org.openqa.selenium.By
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.pages.OperatorBasePage

case class InternationalAddressPage(platformOperatorId: String)
    extends OperatorBasePage(s"/platform-operator/$platformOperatorId/change-international-address") {

  def withAddress(
    addressLine1: String,
    city: String,
    postalOrZipCode: String,
    country: String,
    addressLine2: Option[String] = None,
    region: Option[String] = None
  ): InternationalAddressPage = {
    assertUrl(url)
    sendKeys(By.cssSelector("#line1"), addressLine1)
    addressLine2.foreach(value => sendKeys(By.cssSelector("#line2"), value))
    sendKeys(By.cssSelector("#city"), city)
    region.foreach(value => sendKeys(By.cssSelector("#region"), value))
    sendKeys(By.cssSelector("#postal"), postalOrZipCode)
    sendKeys(By.cssSelector("#country"), country)
    Driver.instance.findElement(By.cssSelector("#country__option--0")).click()
    this
  }
}
