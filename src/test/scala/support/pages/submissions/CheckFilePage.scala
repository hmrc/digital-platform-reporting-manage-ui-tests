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

package support.pages.submissions

import org.openqa.selenium.By
import support.pages.SubmissionBasePage
import support.utils.RegexUtils.UuidRegExString

case class CheckFilePage(platformOperatorId: String)
    extends SubmissionBasePage(s"/submission/$platformOperatorId/$UuidRegExString/check-file") {

  def waitUntilCheckIsFinished(): Unit =
    while (getCurrentUrl.contains("/check-file") && elementExists(s".govuk-button"))
      click(By.cssSelector(".govuk-button"))
}