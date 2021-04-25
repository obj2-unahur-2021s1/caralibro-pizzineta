package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
    describe("Caralibro") {

        val textoBienvenida = Texto("Buenos dias a todos", Publico)
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz", Publico)
        val fotoEnCuzco = Foto(768, 1024, SoloAmigos)
        val fotoDelPartido = Foto(1080, 1920, SoloAmigos)
        val videoFiesta = Video(65, SD480, PublicoConExcluidos)
        val textoDespedida = Texto("Hicimos lo que pudimos", PrivadoConPermitidos)

        val radaelli = Usuario()
        val llop = Usuario()
        val russo = Usuario()
        val pizzi = Usuario()
        val sava = Usuario()
        val beccacece = Usuario()
        val ischia = Usuario()
        val zubeldia = Usuario()
        val vivas = Usuario()
        val chacho = Usuario()


        sava.agregarAmigo(beccacece)
        ischia.agregarAmigo(vivas)
        ischia.agregarAmigo(pizzi)
        pizzi.agregarAmigo(zubeldia)
        beccacece.agregarAmigo(zubeldia)
        vivas.agregarAmigo(pizzi)
        russo.agregarAmigo(radaelli)
        radaelli.agregarAmigo(llop)
        llop.agregarAmigo(sava)
        sava.agregarAmigo(radaelli)
        sava.agregarAmigo(vivas)
        vivas.agregarAmigo(radaelli)
        chacho.agregarAmigo(llop)
        chacho.agregarAmigo(russo)
        chacho.agregarAmigo(beccacece)
        vivas.agregarAmigo(llop)

        vivas.agregarPublicacion(fotoEnCuzco)
        vivas.agregarPublicacion(fotoDelPartido)
        vivas.agregarPublicacion(textoDespedida)
        pizzi.agregarPublicacion(textoBienvenida)
        sava.agregarPublicacion(videoFiesta)
        vivas.agregarPermitidos(sava)
        sava.leDaLikeA(textoDespedida)
        sava.leDaLikeA(fotoDelPartido)
        sava.leDaLikeA(fotoEnCuzco)
        ischia.leDaLikeA(textoBienvenida)
        zubeldia.leDaLikeA(textoBienvenida)

        vivas.agregarPermitidos(llop)

        chacho.agregarPermitidos(llop)
        chacho.agregarPermitidos(russo)
        chacho.agregarPermitidos(beccacece)

        sava.agregarExcluidos(zubeldia)

        describe("Una publicación") {
            describe("de tipo foto") {
                it("ocupa ancho * alto * compresion bytes") {
                    fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
                }
                it("Modificar el factor de compresion para todas las fotos")
                {
                    factorDeCompresion.tamanio = 0.8
                    fotoEnCuzco.espacioQueOcupa().shouldBe(629146)
                }
            }

            describe("de tipo texto") {
                it("ocupa tantos bytes como su longitud") {
                    saludoCumpleanios.espacioQueOcupa().shouldBe(45)
                }
            }
            describe("de tipo video") {
                it("de calidad 480SD ocupa tantos bytes como su duracion") {
                    videoFiesta.espacioQueOcupa().shouldBe(65)
                }
                it("de calidad 720HD ocupa tantos bytes como su duracion * 3") {
                    videoFiesta.cambiarCalidad(HD720)
                    videoFiesta.espacioQueOcupa().shouldBe(195)
                }
                it("de calidad HD1080 ocupa el doble de bytes que el 720HD") {
                    videoFiesta.cambiarCalidad(HD1080)
                    videoFiesta.espacioQueOcupa().shouldBe(390)
                }
            }
        }



        describe("Un usuario") {
            it("puede calcular el espacio que ocupan sus publicaciones") {
                val juana = Usuario()
                factorDeCompresion.tamanio = 0.7
                juana.agregarPublicacion(fotoEnCuzco)
                juana.agregarPublicacion(saludoCumpleanios)
                juana.espacioDePublicaciones().shouldBe(550548)
            }
        }

        describe("2. Poder darle me gusta a una publicación, y conocer cuántas veces fue votada de esta forma.") {
            it("textoBienvenida debe tener 2 likes") {
                textoBienvenida.cantidadDeLikes().shouldBe(2)
            }
            it("textoDespedida debe tener 1 like"){
                textoDespedida.cantidadDeLikes().shouldBe(1)
            }
        }
        describe("3. Saber si un usuario es más amistoso que otro: esto ocurre cuando el primero tiene más amigos."){
            it("Sava es mas amistoso que Pizzi") {
                sava.esMasAmistosoQue(pizzi).shouldBeTrue()
            }
            it("Beccacece es mas amistoso que Russo") {
                beccacece.esMasAmistosoQue(russo).shouldBeTrue()
            }
            it("Zubeldia no es mas amistoso que Llop") {
                zubeldia.esMasAmistosoQue(llop).shouldBeFalse()
            }
        }
        describe("4. Saber si un usuario puede ver una cierta publicacion."){
            it("Modificar privacidad de una publicación"){
                textoBienvenida.cambiarPrivacidad(SoloAmigos)
            }
            it("Russo puede ver textoBienvenida"){
                russo.puedeVer(textoBienvenida).shouldBeTrue()
            }
            it("Chacho puede ver textoBienvenida"){
                chacho.puedeVer(textoBienvenida).shouldBeTrue()
            }
            it("Radaelli puede ver fotoDelPartido"){
                radaelli.puedeVer(fotoDelPartido).shouldBeTrue()
            }
            it("Beccacece no puede ver fotoDelPartido"){
                beccacece.puedeVer(fotoDelPartido).shouldBeFalse()
            }
            it("Sava puede ver textoDespedida"){
                sava.puedeVer(textoDespedida).shouldBeTrue()
            }
            it("Russo no puede ver textoDespedida"){
                russo.puedeVer(textoDespedida).shouldBeFalse()
            }
            it("Zubeldia no puede ver videoFiesta"){
                zubeldia.puedeVer(videoFiesta).shouldBeFalse()
            }
            it("Vivas puede ver videoFiesta"){
                vivas.puedeVer(videoFiesta).shouldBeTrue()
            }
        }
        describe("Funciones de likes") {
            it("saber quien dio like") {
                textoBienvenida.quienDioLike.shouldBe(setOf(ischia, zubeldia))
            }
            it("cantidad de likes") {
                textoBienvenida.cantidadDeLikes().shouldBe(2)
            }

        }
        describe("Saber quien es mas popular"){
            it("saber quien es el amigo mas popular") {
                ischia.amigoMasPopular().shouldBe(vivas)
            }
            it("mejores amigos de un usuario"){
                chacho.mejoresAmigos().shouldBe((setOf(llop, russo, beccacece)))
            }
        }
        describe("7. Saber si un usuario stalkea a otro. Lo cual ocurre si el stalker le dio me gusta a más del 90% de sus publicaciones.") {
            it("Un usuario es stalkeadp por otro:") {
                vivas.esStalkeadoPor(sava).shouldBeTrue()
            }
        }
    }
})