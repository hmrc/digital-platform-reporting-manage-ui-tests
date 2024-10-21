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

package uk.gov.hmrc.ui.pages.platformoperators

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.OperatorBasePage

case class UkTaxIdentifiersPage() extends OperatorBasePage("/platform-operator/add-platform-operator/uk-tin-type") {

  def selectUniqueTaxPayerReference(): Unit = click(By.cssSelector("#value_0"))

  def selectCompanyRegistrationNumber(): Unit = click(By.cssSelector("#value_1"))

  def selectVatRegistrationNumber(): Unit = click(By.cssSelector("#value_2"))

  def selectEmployerPAYEReferenceNumber(): Unit = click(By.cssSelector("#value_3"))

  def selectHmrcCharityReference(): Unit = click(By.cssSelector("#value_4"))
}