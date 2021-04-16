package ar.edu.unahur.obj2.caralibro

import java.lang.Exception
import kotlin.math.ceil

abstract class Publicacion {
  abstract fun espacioQueOcupa(): Int
  val quienDioLike = mutableSetOf<Usuario>()

  fun cantidadDeLikes() = quienDioLike.size

  fun tieneLikeDe(unUsuario: Usuario) = quienDioLike.contains(unUsuario)
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

class Video(val duracion: Int) : Publicacion() {
  var calidad = 480
  val calidadesAdmitidas = listOf(480, 720, 1080)
  override fun espacioQueOcupa() = //PROVISORIO
    if (calidad == 480) {
      duracion
    }
    else if (calidad == 720) {
      duracion * 3
    }
    else if (calidad == 1080) {
      (duracion * 3) * 2
    }
    else {
      throw Exception("Calidad no admitida")
    }

  fun cambiarCalidad(unaCalidad: Int) {
    if (calidadesAdmitidas.contains(unaCalidad)) {
      this.calidad = unaCalidad
    }
  }
}

