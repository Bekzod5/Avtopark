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
  val uzavto = TableQuery[BussyTable]

  def list = DBAction { implicit rs =>
//    Logger.info(s"SHOW_ALL=${car.list}")
    val carss = (for {
      (car, korxonasi) <- car leftJoin uzavto  on (_.korxonaid === _.id)
    } yield (car, korxonasi.name)).list
      .map { case (cars, korxonanomi) => Carss(cars, korxonanomi)}


    Ok(views.html.carlist(carss))
  }
    def addcars = DBAction {implicit rs =>
    Ok(views.html.addcars(uzavto.list))
  }

  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val nomi = formParams.get("nomi")(0)
    val modeli = formParams.get("modeli")(0)
    val korxonaId = formParams.get("korxonaId")(0).toInt



    val carId = (car returning car.map(_.id)) += jadval(None, nomi, modeli,korxonaId)
    Logger.info(s"LastId = $carId")
    Redirect(routes.Cars.list())
  }


  def remove(id: Int) = DBAction { implicit request =>
    car.filter(_.id === id).delete;
    Redirect(routes.Cars.list())
  }

  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val nomi = formParams.get("nomi")(0)
    val modeli = formParams.get("modeli")(0)
    val korxonaId = formParams.get("korxonaId")(0).toInt

    val cars = jadval(Some(id), nomi, modeli, korxonaId)
    car.filter(_.id === id).update(cars)

    Redirect(routes.Cars.list())
  }

  def showEditForm(carId: Int) = DBAction { implicit request =>
    val byId = car.findBy(_.id)
    val cars = byId(carId).list.head

    Ok(views.html.yanaEdit(cars, uzavto.list))
  }


}

