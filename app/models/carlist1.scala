package models

/**
 * Created by Bekzod on 13.03.2015.
 *
 */



import play.api.db.slick.Config.driver.simple._




case class jadval(id: Option[Int],
                  nomi: String,
                  modeli: String)

class jadvalTable(tag: Tag) extends Table[jadval](tag, "CARLIST"){

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def nomi = column[String]("NOMI", O.Default(""))

  def modeli = column[String]("MODELI", O.Default(""))

  def * = (id.?, nomi, modeli) <> (jadval.tupled, jadval.unapply _)

}
object for_num {
  var p = 0

  def inc_num = {
    p += 1
    p
  }

  def nul = {
    p=0
  }

}