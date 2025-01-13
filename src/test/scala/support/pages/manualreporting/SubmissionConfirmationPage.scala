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

package support.pages.manualreporting

import support.pages.SubmissionBasePage

import java.time.Year

case class SubmissionConfirmationPage(platformOperatorId: String, year: Year)
    extends SubmissionBasePage(s"/assumed-reporting/$platformOperatorId/${year.toString}/confirmation-page") {

  def clickManageYourDigitalPlatformReporting(): Unit =
    click("a[href*='http://localhost:20006/digital-platform-reporting/manage-reporting']")

  def clickSubmitAnotherReport(): Unit =
    click(s"a[href*='/digital-platform-reporting/submission/$platformOperatorId/start-page']")
}
