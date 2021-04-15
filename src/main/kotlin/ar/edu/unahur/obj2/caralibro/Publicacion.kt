package ar.edu.unahur.obj2.caralibro

import java.lang.Exception
import kotlin.math.ceil

abstract class Publicacion {
  abstract fun espacioQueOcupa(): Int
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

class Video(val duracion: Int, var calidad: Int) : Publicacion() {
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
    this.calidad = unaCalidad
  }
}

