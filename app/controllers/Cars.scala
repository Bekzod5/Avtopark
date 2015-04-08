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
    def addcars = Action {
    Ok(views.html.addcars())
  }

  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val nomi = formParams.get("nomi")(0)
    val modeli = formParams.get("modeli")(0)


    println("Nomi: " + nomi)
    println("Nomi: " + nomi)
    val carId = (car returning car.map(_.id)) += jadval(None, nomi, modeli)
    Redirect(routes.Cars.list())
  }


  def remove(id: Int) = DBAction { implicit request =>
    car.filter(_.id === id).delete;
    Redirect(routes.Cars.list())
  }



}

