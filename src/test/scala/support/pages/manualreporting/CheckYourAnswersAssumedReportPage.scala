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

case class CheckYourAnswersAssumedReportPage(platformOperatorId: String, reportingPeriod: String)
    extends SubmissionBasePage(s"/assumed-reporting/$platformOperatorId/$reportingPeriod/check-your-answers") {

  private val changeAssumingOperatorNameLinkCssSelector =
    s"a[href*='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/change-assuming-operator-name']"

  private val changeTaxResidentInUKLinkCssSelector =
    s"a[href*='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/change-tax-resident-in-uk']"

  private val changeTaxResidentCountryLinkCssSelector =
    s"a[href*='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/change-tax-resident-country']"

  private val changeHaveInternationalTinLinkCssSelector =
    s"a[href*='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/change-have-international-tin']"

  private val changeInternationalTinLinkCssSelector =
    s"a[href*='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/change-international-tin']"

  private val changeRegisteredAddressCountryLinkCssSelector =
    s"a[href='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/change-registered-address-country']"

  private val changeRegisteredAddressLinkCssSelector =
    s"a[href='/digital-platform-reporting/assumed-reporting/$platformOperatorId/$reportingPeriod/change-registered-address']"

  def clickChangeAssumingOperatorName(): Unit = click(changeAssumingOperatorNameLinkCssSelector)

  def clickChangeTaxResidentInUK(): Unit = click(changeTaxResidentInUKLinkCssSelector)

  def clickChangeTaxResidentCountry(): Unit = click(changeTaxResidentCountryLinkCssSelector)

  def clickChangeHaveInternationalTin(): Unit = click(changeHaveInternationalTinLinkCssSelector)

  def clickChangeInternationalTin(): Unit = click(changeInternationalTinLinkCssSelector)

  def clickChangeRegisteredAddressCountry(): Unit = click(changeRegisteredAddressCountryLinkCssSelector)

  def clickChangeRegisteredAddress(): Unit = click(changeRegisteredAddressLinkCssSelector)

}
