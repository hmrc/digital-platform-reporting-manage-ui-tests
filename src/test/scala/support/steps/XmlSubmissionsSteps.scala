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

package support.steps

import support.pages.IndexPage
import support.pages.submissions._
import support.utils.FileUtils.fileToUploadFrom

object XmlSubmissionsSteps {

  private val indexPage                  = IndexPage()
  private val selectPlatformOperatorPage = SelectPlatformOperatorPage()

  def addXmlSubmission(platformOperatorId: String): Unit = {
    indexPage.clickMakeXmlSubmission()
    selectPlatformOperatorPage.continue()
    StartPage(platformOperatorId).continue()
    CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
    CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
    CheckContactDetailsPage(platformOperatorId).selectYes().continue()
    val fileToUpload = fileToUploadFrom("SubmissionTemplate.xml", platformOperatorId)
    UploadPage(platformOperatorId).withFileToUpload(fileToUpload).continue()
    UploadingPage(platformOperatorId).waitUntilFinishIfUploading()
    SendFilePage(platformOperatorId).continue()
    CheckFilePage(platformOperatorId).waitUntilCheckIsFinished()
    SubmissionConfirmationPage(platformOperatorId).clickManageYourDigitalPlatformReporting()
  }
}
