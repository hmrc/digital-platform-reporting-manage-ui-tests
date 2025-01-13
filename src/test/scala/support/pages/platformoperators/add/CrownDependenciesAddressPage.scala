/*
 * Copyright 2025 HM Revenue & Customs
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

package support.pages.platformoperators.add

import org.openqa.selenium.By
import support.pages.OperatorBasePage
import uk.gov.hmrc.selenium.webdriver.Driver

case class CrownDependenciesAddressPage()
    extends OperatorBasePage("/platform-operator/add-platform-operator/crown-dependencies-address") {

  def withAddress(
    addressLine1: String,
    townOrCity: String,
    postCode: String,
    country: String,
    addressLine2: Option[String] = None,
    county: Option[String] = None,
    postalOrZipCode: Option[String] = None
  ): CrownDependenciesAddressPage = {
    assertUrl(url)
    sendKeys(By.cssSelector("#line1"), addressLine1)
    addressLine2.foreach(value => sendKeys(By.cssSelector("#line2"), value))
    sendKeys(By.cssSelector("#town"), townOrCity)
    sendKeys(By.cssSelector("#postCode"), postCode)
    county.foreach(value => sendKeys(By.cssSelector("#county"), value))
    postalOrZipCode.foreach(value => sendKeys(By.cssSelector("#postCode"), value))
    sendKeys(By.cssSelector("#country"), country)
    Driver.instance.findElement(By.cssSelector("#country__option--0")).click()
    this
  }
}
