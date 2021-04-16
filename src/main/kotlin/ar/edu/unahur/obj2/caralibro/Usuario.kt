package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val amigos = mutableSetOf<Usuario>()
  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
  fun agregarAmigo(unAmigo: Usuario) {
    amigos.add(unAmigo)
    unAmigo.amigos.add(this)
  }

  fun leDaLikeA(unaPublicacion: Publicacion) = unaPublicacion.quienDioLike.add(this)
  fun cantidadDeAmigos() = amigos.size
  fun esMasAmistosoQue(unUsuario: Usuario)= this.cantidadDeAmigos() > unUsuario.cantidadDeAmigos()
  fun stalkeaA(unUsuario: Usuario)=

  }

}
