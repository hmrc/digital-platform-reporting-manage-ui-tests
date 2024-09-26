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

import support.builders.EnrolmentsDataBuilder.anEnrolmentsData
import support.models._
import support.models.auth.EnrolmentsData
import support.repositories.SubscriptionRepository
import uk.gov.hmrc.selenium.component.PageObject

import java.time.Instant

object SubscriptionSteps extends PageObject {

  def subscribedIndividualEnrolment(): EnrolmentsData = {
    val dprsId = SubscriptionRepository.insert(
      Subscription(
        _id = "",
        gbUser = true,
        tradingName = None,
        primaryContact = IndividualContact(Individual("Rowan", "Bean"), "rowan.bean@example.com", None),
        secondaryContact = None,
        Instant.now()
      )
    )
    anEnrolmentsData.copy(identifierValue = dprsId)
  }

  def subscribedOrganisationEnrolment(): EnrolmentsData = {
    val dprsId = SubscriptionRepository.insert(
      Subscription(
        _id = "",
        gbUser = true,
        tradingName = None,
        primaryContact = OrganisationContact(Organisation("Homer Simpson"), "homer.simpson@example.com", None),
        secondaryContact = None,
        Instant.now()
      )
    )
    anEnrolmentsData.copy(identifierValue = dprsId)
  }
}
