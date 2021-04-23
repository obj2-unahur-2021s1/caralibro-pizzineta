package ar.edu.unahur.obj2.caralibro

import java.lang.Exception
import kotlin.math.ceil

abstract class Publicacion(var privacidad: Privacidad) {

  abstract fun espacioQueOcupa(): Int

  val quienDioLike = mutableSetOf<Usuario>()

  var creador: Usuario? = null

  fun cantidadDeLikes() = quienDioLike.size

  fun tieneLikeDe(unUsuario: Usuario) = quienDioLike.contains(unUsuario)

  fun puedeSerVistaPor(unUsuario: Usuario) = privacidad.estaHabilitadoPara(unUsuario, this)

  fun cambiarPrivacidad(nuevaPrivacidad: Privacidad) {
    this.privacidad = nuevaPrivacidad
  }
}

class Foto(val alto: Int, val ancho: Int, privacidad: Privacidad) : Publicacion(privacidad) {

  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion.tamanio).toInt()
}

class Texto(val contenido: String, privacidad: Privacidad) : Publicacion(privacidad) {
  override fun espacioQueOcupa() = contenido.length
}

class Video(val duracion:Int, var calidad:Calidad, privacidad: Privacidad): Publicacion(privacidad){
  override fun espacioQueOcupa()=calidad.espacioQueOcupa(duracion)

  fun cambiarCalidad(unaCalidad: Calidad) {
    this.calidad = unaCalidad
  }
}

abstract class Calidad() {
  abstract fun espacioQueOcupa(duracion: Int): Int
}

object SD480: Calidad(){
  override fun espacioQueOcupa(duracion: Int) = duracion
}
object HD720: Calidad(){
  override fun espacioQueOcupa(duracion: Int) = duracion * 3
}
object HD1080: Calidad(){
  override fun espacioQueOcupa(duracion: Int) = HD720.espacioQueOcupa(duracion) * 2
}

object factorDeCompresion {
  var tamanio = 0.7
}


