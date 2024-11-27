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

package support.pages.platformoperators.add

import support.pages.OperatorBasePage

case class PlatformOperatorAddedPage()
    extends OperatorBasePage("/platform-operator/add-platform-operator/added-successfully") {

  private val manageLinkCssSelector = "a[href*='http://localhost:20006/digital-platform-reporting/manage-reporting']"
  private val addLinkCssSelector    =
    "a[href*='/digital-platform-reporting/platform-operator/add-platform-operator/start']"

  def clickManageYourDigitalPlatformReporting(): Unit = click(manageLinkCssSelector)

  def clickAddAnotherPlatformOperator(): Unit = click(addLinkCssSelector)
}
