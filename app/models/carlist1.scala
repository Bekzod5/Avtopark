package models

/**
 * Created by Bekzod on 13.03.2015.
 *
 */



import play.api.db.slick.Config.driver.simple._




case class jadval(id: Option[Int],
                  nomi: String,
                  modeli: String,
                   korxonaid: Int)

case class Carss(carss: jadval,
                          korxonanomi: String)


case class korxona(id: Option[Int],
                   name: String)

class jadvalTable(tag: Tag) extends Table[jadval](tag, "CARLIST"){

  val uzavto=TableQuery[BussyTable]

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def nomi = column[String]("NOMI", O.Default(""))

  def modeli = column[String]("MODELI", O.Default(""))

  def korxonaid=column[Int]("KORXONA_ID",O.NotNull)

  def * = (id.?, nomi, modeli,korxonaid) <> (jadval.tupled, jadval.unapply _)



  def korxona = foreignKey("JADVAL_FK_KORXONA", korxonaid, uzavto)(_.id)


}

class BussyTable(tag: Tag) extends Table[korxona](tag, "BUSSY") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def * = (id.?, name) <> (korxona.tupled, korxona.unapply _)

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