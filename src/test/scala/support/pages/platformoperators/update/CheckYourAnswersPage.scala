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

package support.pages.platformoperators.update

import support.pages.OperatorBasePage

case class CheckYourAnswersPage(platformOperatorId: String)
    extends OperatorBasePage(s"/platform-operator/$platformOperatorId/check-your-answers") {

  def clickChangeBusinessName(): Unit =
    click(s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-business-name']")

  def clickTradeUnderDifferentName(): Unit =
    click(s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-have-trading-name']")

  def clickChangeHasTin(): Unit =
    click(s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-have-tin']")

  def clickRegisteredInUk(): Unit = click(
    s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-registered-address-in-uk']"
  )

  def clickChangePrimaryContactName(): Unit =
    click(s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-contact-name']")

  def clickChangePrimaryContactEmail(): Unit =
    click(s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-email-address']")

  def clickCanContactByPhone(): Unit =
    click(s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-can-we-telephone']")

  def clickChangeSecondContact(): Unit =
    click(s"a[href*='/digital-platform-reporting/platform-operator/$platformOperatorId/change-second-contact']")
}
