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

package uk.gov.hmrc.ui.pages.submissions

import support.utils.RegexUtils.UuidRegExString
import uk.gov.hmrc.ui.pages.SubmissionBasePage

case class SubmissionConfirmationPage(platformOperatorId: String)
    extends SubmissionBasePage(s"/submission/$platformOperatorId/$UuidRegExString/confirmation") {

  def clickManageYourDigitalPlatformReporting(): Unit =
    click("a[href*='http://localhost:20006/digital-platform-reporting/manage-reporting']")
}
