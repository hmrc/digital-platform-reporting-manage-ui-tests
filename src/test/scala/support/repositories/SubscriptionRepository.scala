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

import com.mongodb.client.model.ReturnDocument
import org.mongodb.scala.model.{Filters, FindOneAndUpdateOptions, Updates}
import org.mongodb.scala.{Document, MongoClient}
import play.api.libs.json.Json
import support.models.Subscription

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import org.mongodb.scala.SingleObservableFuture
import org.mongodb.scala.documentToUntypedDocument
import org.mongodb.scala.ObservableFuture

object SubscriptionRepository {

  private lazy val mongoClient: MongoClient = MongoClient()

  def insert(subscription: Subscription): String = Await.result(
    mongoClient
      .getDatabase("digital-platform-reporting-stubs")
      .getCollection("subscriptions")
      .insertOne(Document(Json.toJson(subscription.copy(_id = nextSubscriptionId().toString)).toString()))
      .toFuture()
      .map(item => item.getInsertedId.asString().getValue),
    2 seconds
  )

  private def nextSubscriptionId(): Int = Await
    .result(
      mongoClient
        .getDatabase("digital-platform-reporting-stubs")
        .getCollection("subscription-ids")
        .findOneAndUpdate(
          filter = Filters.eq("_id", "subscriptionId"),
          update = Updates.inc("nextId", 1),
          options = FindOneAndUpdateOptions()
            .upsert(true)
            .returnDocument(ReturnDocument.AFTER)
        )
        .map(_.getInteger("nextId"))
        .toFuture(),
      2 seconds
    )
    .head
}
