package ar.edu.unahur.obj2.caralibro

abstract class Privacidad {
    open fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) =
            unUsuario == unaPublicacion.creador!!
}


object Publico: Privacidad() {
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) = true
}

object SoloAmigos: Privacidad(){
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) =
        super.estaHabilitadoPara(unUsuario, unaPublicacion) || unaPublicacion.creador!!.amigos.contains(unUsuario)


}
object PrivadoConPermitidos: Privacidad(){
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) =
            super.estaHabilitadoPara(unUsuario, unaPublicacion) || unaPublicacion.creador!!.permitidos.contains(unUsuario)
}
object PublicoConExcluidos: Privacidad(){
    override fun estaHabilitadoPara(unUsuario: Usuario, unaPublicacion: Publicacion) =
            super.estaHabilitadoPara(unUsuario, unaPublicacion) || !unaPublicacion.creador!!.excluidos.contains(unUsuario)
}
