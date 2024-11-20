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
import java.nio.file.Paths
import scala.io.Source

object FileUtils {

  def fileToUploadFrom(template: String, platformOperatorId: String): String = {
    val filePath       = Paths.get(getClass.getClassLoader.getResource(template).toURI).toFile.getAbsolutePath
    val outputLines    = Source.fromResource(template).getLines()
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
