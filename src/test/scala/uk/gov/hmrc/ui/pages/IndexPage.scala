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

case class IndexPage() extends ManageBasePage("/manage-reporting") {

  def clickAddPlatformOperator(): Unit =
    click(s"a[href*='$platformOperatorBaseUrl/platform-operator/add-platform-operator/start']")

  def clickViewPlatformOperators(): Unit =
    click("a[href*='http://localhost:20005/digital-platform-reporting/platform-operator/view")

  def clickChangeContactDetails(): Unit =
    click("a[href*='/digital-platform-reporting/contact-details/view-contact-details']")

  def clickAddReportingNotification(): Unit =
    click(s"a[href*='$platformOperatorBaseUrl/reporting-notification/which-platform-operator']")

  def showManageOperator(): Unit = get(s"$baseUrl/manage-reporting")
}
