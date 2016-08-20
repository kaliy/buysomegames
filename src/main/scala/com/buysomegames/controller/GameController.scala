package com.buysomegames.controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class GameController extends Controller {
  get("/games") { request: Request =>
    "{\"hello\":\"world\"}"
  }
}
