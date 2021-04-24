package ar.edu.unahur.obj2.caralibro

import java.lang.Exception

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val amigos = mutableSetOf<Usuario>()
  val permitidos = mutableSetOf<Usuario>()
  val excluidos = mutableSetOf<Usuario>()


  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
    publicacion.creador = this
  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
  fun agregarAmigo(unAmigo: Usuario) {
    amigos.add(unAmigo)
    unAmigo.amigos.add(this)
  }

  fun agregarPermitidos(unUsuario: Usuario){
    if(!this.amigos.contains(unUsuario)){
      throw Exception("El usuario que intenta agregar debe ser amigo")
    }
    if (excluidos.contains(unUsuario)){
      excluidos.remove(unUsuario)
    }
    permitidos.add(unUsuario)
  }

  fun agregarExcluidos(unUsuario: Usuario){
    if (this.permitidos.contains(unUsuario)) {
      permitidos.remove(unUsuario)
    }
    excluidos.add(unUsuario)
  }

  fun puedeVer(unaPublicacion: Publicacion) = unaPublicacion.puedeSerVistaPor(this)
  fun leDaLikeA(unaPublicacion: Publicacion) = unaPublicacion.quienDioLike.add(this)
  fun cantidadDeAmigos() = amigos.size
  fun esMasAmistosoQue(unUsuario: Usuario) = this.cantidadDeAmigos() > unUsuario.cantidadDeAmigos()
  fun esStalkeadoPor(unUsuario: Usuario) = cantidadDeLikesDe(unUsuario) > (this.cantidadDePublicaciones() * 0.9)

  fun cantidadDePublicaciones() = publicaciones.size
  fun cantidadDeLikesDe(unUsuario: Usuario) = publicaciones.count{it.tieneLikeDe(unUsuario)}
  fun mejoresAmigos() = this.permitidos
}