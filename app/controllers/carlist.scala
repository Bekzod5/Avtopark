package controllers

/**
 * Created by Bekzod on 11.03.2015.
 */

import models.carlist1
import play.api.mvc._

object carlist extends Controller{
  def cars=Action {
    Ok(views.html.carlist(carlist1.table))
  }


}


