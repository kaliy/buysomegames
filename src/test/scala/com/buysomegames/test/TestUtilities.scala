package com.buysomegames.test

import scala.io.Source

object TestUtilities {
  def readResource(resourcePath: String): String = {
    Source.fromInputStream(getClass.getResourceAsStream(resourcePath)).getLines.mkString
  }
}
