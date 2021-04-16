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
  fun esMasAmistosoQue(unUsuario: Usuario) = this.cantidadDeAmigos() > unUsuario.cantidadDeAmigos()
  fun esStalkeadoPor(unUsuario: Usuario) = cantidadDeLikesDe(unUsuario) > (this.cantidadDePublicaciones() * 0.9)

  fun cantidadDePublicaciones() = publicaciones.size
  fun cantidadDeLikesDe(unUsuario: Usuario) = publicaciones.count{it.tieneLikeDe(unUsuario)}

}
