package ar.edu.unahur.obj2.caralibro

abstract class Privacidad {
    abstract fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion): Boolean
}

object Publico: Privacidad() {
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) = true
}

object SoloAmigos: Privacidad(){
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) =
        unaPublicacion.creador!!.amigos.contains(unUsuario)
}
object PrivadoConPermitidos: Privacidad(){
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) =
        unaPublicacion.creador!!.permitidos.contains(unUsuario)
}
object PublicoConExcluidos: Privacidad(){
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) =
        !unaPublicacion.creador!!.excluidos.contains(unUsuario)
}
