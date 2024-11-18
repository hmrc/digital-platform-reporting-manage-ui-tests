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

package support.utils

import java.io.PrintStream
import scala.io.{Codec, Source}

object FileUtils {

  def fileToUploadFrom(template: String, platformOperatorId: String): String = {
    val filePath       = getClass.getResource(template).getPath
    val inputStream    = Source.fromFile(filePath)(Codec.UTF8)
    val outputLines    = inputStream.getLines()
    val resultFileName = filePath.replace("Template.xml", s"-$platformOperatorId.xml")

    new PrintStream(resultFileName) {
      outputLines.foreach { line =>
        val formatted = line.replace("%platformOperatorId%", platformOperatorId) + "\n"
        write(formatted.getBytes("UTF8"))
      }
    }
    resultFileName
  }
}
