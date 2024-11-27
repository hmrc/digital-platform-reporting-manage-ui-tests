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

case class PlatformOperatorsPage() extends OperatorBasePage("/platform-operator/view") {

  private val addNewCssSelector = "a[href*='/digital-platform-reporting/platform-operator/add-platform-operator/start']"

  def clickAddNewPlatformOperator(): Unit = click(addNewCssSelector)

  def clickView(index: Int): String = {
    val operatorId = getText(By.cssSelector(".govuk-summary-list__value"))
    click(s".govuk-summary-list__actions > a:nth-child($index)")
    operatorId
  }

  def platformOperator: String = getText(By.cssSelector(".govuk-summary-list__value"))

  def goToManageOperator(): Unit = get(s"$manageBaseUrl/manage-reporting")
}
