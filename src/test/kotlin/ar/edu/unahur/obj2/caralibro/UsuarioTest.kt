package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
    describe("Caralibro") {
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
        val fotoEnCuzco = Foto(768, 1024)
        val fotoDelPartido = Foto(1080, 1920)
        val videoFiesta = Video(65, SD480)
        val textoDespedida = Texto("Hicimos lo que pudimos")
        val radaelli = Usuario()
        val llop = Usuario()
        val russo = Usuario()
        val pizzi = Usuario()
        val sava = Usuario()
        val beccacece = Usuario()
        val ischia = Usuario()
        val zubeldia = Usuario()
        val vivas = Usuario()
        val textoBienvenida = Texto("Buenos dias a todos")

        sava.agregarAmigo(beccacece)
        ischia.agregarAmigo(vivas)
        pizzi.agregarAmigo(zubeldia)
        beccacece.agregarAmigo(zubeldia)
        vivas.agregarAmigo(pizzi)
        russo.agregarAmigo(radaelli)
        radaelli.agregarAmigo(llop)
        llop.agregarAmigo(sava)
        sava.agregarAmigo(radaelli)
        sava.agregarAmigo(vivas)
        vivas.agregarPublicacion(fotoEnCuzco)
        vivas.agregarPublicacion(fotoDelPartido)
        vivas.agregarPublicacion(textoDespedida)
        sava.leDaLikeA(textoDespedida)
        sava.leDaLikeA(fotoDelPartido)
        sava.leDaLikeA(fotoEnCuzco)
        pizzi.agregarPublicacion(textoBienvenida)
        ischia.leDaLikeA(textoBienvenida)
        zubeldia.leDaLikeA(textoBienvenida)

        describe("Una publicación") {
            describe("de tipo foto") {
                it("ocupa ancho * alto * compresion bytes") {
                    fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
                }
                it("modificar el tamaño para todas las fotos")
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


        describe("Un usuario") {
            it("puede calcular el espacio que ocupan sus publicaciones") {
                val juana = Usuario()
                factorDeCompresion.tamanio = 0.7
                juana.agregarPublicacion(fotoEnCuzco)
                juana.agregarPublicacion(saludoCumpleanios)
                juana.espacioDePublicaciones().shouldBe(550548)
            }
        }

        describe("Saber si un usuario es mas amistoso que otro"){
            it("Saber si un usuario es más amistoso que otro") {
                sava.esMasAmistosoQue(pizzi).shouldBeTrue()
                beccacece.esMasAmistosoQue(russo).shouldBeTrue()
                llop.esMasAmistosoQue(zubeldia).shouldBeFalse()
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
        describe("Stalkear") {
            it("Un usuario es stalkeadp por otro:") {
                vivas.esStalkeadoPor(sava).shouldBeTrue()
            }
        }
    }
})

