package controllers

import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class zavod extends Controller {

  val uzavto = TableQuery[BussyTable]

  def list = DBAction { implicit rs =>
    Ok(views.html.listkorxona(uzavto.list))
  }

  def showAddForm = DBAction { implicit rs =>
    Ok(views.html.addkorxona())
  }


  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val carId = (uzavto returning uzavto.map(_.id)) += korxona(None, name)
    Logger.info(s"LastId = $carId")
    Redirect(routes.zavod.list())
  }


  def remove(id: Int) = DBAction { implicit request =>
    uzavto.filter(_.id === id).delete;
    Redirect(routes.zavod.list())
  }

  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val name = formParams.get("name")(0)

    val korxonauz = korxona(Some(id), name)
    uzavto.filter(_.id === id).update(korxonauz)
    Redirect(routes.zavod.list())
  }

  def showEditForm(korxonaid: Int) = DBAction { implicit request =>
    val byId = uzavto.findBy(_.id)
    val korxonauz = byId(korxonaid).list.head

    Ok(views.html.Editkorxonaname(korxonauz))
  }

}