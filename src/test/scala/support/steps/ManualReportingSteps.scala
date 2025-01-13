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
import support.pages.manualreporting._

import java.time.Year

object ManualReportingSteps {

  private val indexPage                  = IndexPage()
  private val selectPlatformOperatorPage = SelectPlatformOperatorPage()

  def addManualReporting(platformOperatorId: String, platformOperatorName: Option[String] = None): Unit = {
    indexPage.clickMakeManualAssumedReport()
    selectPlatformOperatorPage.withPlatformOperator(platformOperatorName.getOrElse("Platform Operator")).continue()
    StartPage(platformOperatorId).continue()
    CheckPlatformOperatorPage(platformOperatorId).selectYes().continue()
    CheckReportingNotificationsPage(platformOperatorId).selectYes().continue()
    CheckContactDetailsPage(platformOperatorId).selectYes().continue()
    ReportablePeriodPage(platformOperatorId).withYear(Year.now.toString).continue()
    AssumingOperatorNamePage(platformOperatorId).withName("Sherlock Holmes").continue()
    TaxResidentInUkPage(platformOperatorId).selectNo().continue()
    TaxResidencyCountryPage(platformOperatorId).withCountry("United States").continue()
    HasInternationalTaxIdentifierPage(platformOperatorId).selectYes().continue()
    InternationalTaxIdentifierPage(platformOperatorId).withTaxIdentificationNumber("TIN112233").continue()
    RegisteredAddressCountryPage(platformOperatorId).withCountry("United States").continue()
    RegisteredAddressPage(platformOperatorId).withAddress("221B Baker Street").continue()
    CheckYourAnswersPage(platformOperatorId).continue()
    SubmissionConfirmationPage(platformOperatorId, Year.now).clickManageYourDigitalPlatformReporting()
  }
}
