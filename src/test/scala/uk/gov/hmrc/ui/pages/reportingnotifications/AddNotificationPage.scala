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

package uk.gov.hmrc.ui.pages.reportingnotifications

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.OperatorBasePage

case class AddNotificationPage(platformOperatorId: String)
    extends OperatorBasePage(s"/reporting-notification/$platformOperatorId/add-notification") {

  def selectReportingPlatformOperator(): AddNotificationPage = selectOption("#value_0")

  def selectExcludedPlatformOperator(): AddNotificationPage = selectOption("#value_1")

  private def selectOption(cssSelector: String): AddNotificationPage = {
    assertUrl(url)
    click(By.cssSelector(cssSelector))
    this
  }
}
