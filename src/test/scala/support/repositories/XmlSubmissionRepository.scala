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

package support.repositories

import org.mongodb.scala.{Document, MongoClient}
import org.mongodb.scala.SingleObservableFuture
import play.api.libs.json.{JsNull, Json}
import uk.gov.hmrc.mongo.play.json.formats.MongoJavatimeFormats

import java.time.{Instant, Year}
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object XmlSubmissionRepository extends MongoJavatimeFormats.Implicits {

  private lazy val mongoClient: MongoClient = MongoClient()

  def insertRejectedSubmissionWithoutOperatorId(dprsId: String, operatorId: String, conversationId: String): Unit = {
    val now           = Instant.now()
    val reportingYear = Year.now().minusYears(1).getValue
    val fileName      = "missing-poid.xml"

    val localSubmission = Document(
      Json
        .obj(
          "_id"                  -> conversationId,
          "submissionType"       -> "Xml",
          "dprsId"               -> dprsId,
          "operatorId"           -> operatorId,
          "operatorName"         -> "Platform Operator One",
          "assumingOperatorName" -> JsNull,
          "state"                -> Json.obj(
            "type"            -> "Rejected",
            "fileName"        -> fileName,
            "reportingPeriod" -> reportingYear
          ),
          "created"              -> now,
          "updated"              -> now
        )
        .toString()
    )

    val deliveredSubmission = Document(
      Json
        .obj(
          "subscriptionId"       -> dprsId,
          "submissionId"         -> conversationId,
          "fileName"             -> fileName,
          "operatorId"           -> JsNull,
          "operatorName"         -> "Platform Operator One",
          "reportingPeriod"      -> reportingYear.toString,
          "submissionDateTime"   -> now,
          "submissionCaseId"     -> "DPI-SUB-MISSING-POID",
          "submissionStatus"     -> "REJECTED",
          "assumingReporterName" -> JsNull,
          "body"                 -> ""
        )
        .toString()
    )

    Await.result(
      for {
        _ <- mongoClient
               .getDatabase("digital-platform-reporting")
               .getCollection("submissions")
               .insertOne(localSubmission)
               .toFuture()
        _ <- mongoClient
               .getDatabase("digital-platform-reporting-stubs")
               .getCollection("submissions")
               .insertOne(deliveredSubmission)
               .toFuture()
      } yield (),
      5.seconds
    )
  }
}
