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


        vivas.agregarPublicacion(fotoEnCuzco)
        vivas.agregarPublicacion(fotoDelPartido)
        vivas.agregarPublicacion(textoDespedida)
        pizzi.agregarPublicacion(textoBienvenida)
        sava.agregarPublicacion(videoFiesta)



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



        describe("2.Poder darle me gusta a una publicación, y conocer cuántas veces fue votada de esta forma.") {

            ischia.leDaLikeA(textoBienvenida)
            zubeldia.leDaLikeA(textoBienvenida)
            vivas.agregarAmigo(sava)
            vivas.agregarPermitidos(sava)
            sava.leDaLikeA(textoDespedida)


            it("saber quien dio like a textoBienvenida") {
                textoBienvenida.quienDioLike.shouldBe(setOf(ischia, zubeldia))
            }
            it("cantidad de likes de textoBienvenida") {
                textoBienvenida.cantidadDeLikes().shouldBe(2)
            }
            it("textoDespedida debe tener 1 like"){
                textoDespedida.cantidadDeLikes().shouldBe(1)
            }
            it("saber quien dio like a textoDespedida"){
                textoDespedida.quienDioLike.shouldBe(setOf(sava))
            }
        }



        describe("3. Saber si un usuario es más amistoso que otro: esto ocurre cuando el primero tiene más amigos."){

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

            sava.agregarAmigo(vivas)
            vivas.agregarAmigo(llop)
            vivas.agregarAmigo(radaelli)
            chacho.agregarAmigo(llop)
            chacho.agregarAmigo(russo)
            chacho.agregarAmigo(beccacece)

            vivas.agregarPermitidos(sava)
            vivas.agregarPermitidos(llop)
            chacho.agregarPermitidos(llop)
            chacho.agregarPermitidos(russo)
            chacho.agregarPermitidos(beccacece)

            sava.agregarExcluidos(zubeldia)

            it("Modificar privacidad de una publicación"){
                textoBienvenida.cambiarPrivacidad(SoloAmigos)
                russo.puedeVer(textoBienvenida).shouldBeFalse()
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



        describe("5.Determinar los mejores amigos de un usuario: el conjunto de sus amigos que pueden ver todas sus publicaciones."){

            chacho.agregarAmigo(llop)
            chacho.agregarAmigo(russo)
            chacho.agregarAmigo(beccacece)

            chacho.agregarPermitidos(llop)
            chacho.agregarPermitidos(russo)
            chacho.agregarPermitidos(beccacece)

            vivas.agregarAmigo(llop)
            vivas.agregarAmigo(sava)

            vivas.agregarPermitidos(sava)
            vivas.agregarPermitidos(llop)

            it("mejores amigos de chacho"){
                chacho.mejoresAmigos().shouldBe((setOf(llop, russo, beccacece)))
            }
            it("mejores amigos de vivas"){
                vivas.mejoresAmigos().shouldBe((setOf(sava, llop)))
            }

        }



        describe("6. Saber cual es el amigo más popular que tiene un usuario. Es decir, el amigo que tiene mas me gusta entre todas sus publicaciones."){

            ischia.agregarAmigo(vivas)
            ischia.agregarAmigo(pizzi)
            pizzi.agregarAmigo(zubeldia)
            vivas.agregarAmigo(pizzi)
            sava.agregarAmigo(vivas)
            vivas.agregarAmigo(radaelli)
            vivas.agregarAmigo(llop)


            ischia.leDaLikeA(textoBienvenida)
            zubeldia.leDaLikeA(textoBienvenida)
            sava.leDaLikeA(textoDespedida)
            sava.leDaLikeA(fotoDelPartido)
            sava.leDaLikeA(fotoEnCuzco)

            it("saber quien es el amigo mas popular de ischia") {
                ischia.amigoMasPopular().shouldBe(vivas)
            }

            it("saber quien es el amigo mas popular de vivas"){
                vivas.amigoMasPopular().shouldBe(pizzi)
            }
        }



        describe("7. Saber si un usuario stalkea a otro. Lo cual ocurre si el stalker le dio me gusta a más del 90% de sus publicaciones.") {

            sava.agregarAmigo(vivas)
            vivas.agregarAmigo(radaelli)

            vivas.agregarPermitidos(sava)

            sava.leDaLikeA(textoDespedida)
            sava.leDaLikeA(fotoDelPartido)
            sava.leDaLikeA(fotoEnCuzco)
            radaelli.leDaLikeA(fotoEnCuzco)

            it("vivas es stalkeado por sava:") {
                vivas.esStalkeadoPor(sava).shouldBeTrue()
            }

            it("vivas no es stalkeado por radaelli:"){
                vivas.esStalkeadoPor(radaelli).shouldBeFalse()
            }
        }
    }
})