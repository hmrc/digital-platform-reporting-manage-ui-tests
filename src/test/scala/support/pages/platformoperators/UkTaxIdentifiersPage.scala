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

package support.pages.platformoperators

import org.openqa.selenium.By
import support.pages.OperatorBasePage

case class UkTaxIdentifiersPage() extends OperatorBasePage("/platform-operator/add-platform-operator/uk-tin-type") {

  def selectUniqueTaxPayerReference(): UkTaxIdentifiersPage = {
    click(By.cssSelector("#value_0"))
    this
  }

  def selectCompanyRegistrationNumber(): UkTaxIdentifiersPage = {
    click(By.cssSelector("#value_1"))
    this
  }

  def selectVatRegistrationNumber(): UkTaxIdentifiersPage = {
    click(By.cssSelector("#value_2"))
    this
  }

  def selectEmployerPAYEReferenceNumber(): UkTaxIdentifiersPage = {
    click(By.cssSelector("#value_3"))
    this
  }

  def selectHmrcCharityReference(): UkTaxIdentifiersPage = {
    click(By.cssSelector("#value_4"))
    this
  }
}
