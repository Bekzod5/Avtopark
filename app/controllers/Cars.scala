package controllers

/**
 * Created by Bekzod on 23.03.2015.
 */
import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import models._
import play.api.Logger

import scala.slick.lifted.TableQuery


class Cars  extends Controller {

  val car = TableQuery[jadvalTable]

  def list = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL=${car.list}")
    Ok(views.html.carlist(car.list))
  }

}
