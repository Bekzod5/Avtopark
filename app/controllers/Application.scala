package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def news = Action {
    Ok(views.html.news())
  }

  def log= Action{
    Ok(views.html.logi())
  }

}