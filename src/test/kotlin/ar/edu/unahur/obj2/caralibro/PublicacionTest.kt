package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PublicacionTest : DescribeSpec ({

    describe("caralibro"){

        val textoBienvenida = Texto("Bienvenidos a la unahur")
       // val videoBienvenida = Video(30, 1080)
        // val fotoBienvenida = Foto(1080, 1920)
        val naomi = Usuario()
        val lucila = Usuario()
        val diego = Usuario()
        diego.agregarPublicacion(textoBienvenida)
        naomi.agregarAmigo(lucila)
        naomi.leDaLikeA(textoBienvenida)
        lucila.leDaLikeA(textoBienvenida)

        describe("una publicacion")
        {
            it("saber quien dio like")
            {
                textoBienvenida.quienDioLike.shouldBe(setOf(naomi, lucila))
            }
            it("cantidad de likes")
            {
                textoBienvenida.cantidadDeLikes().shouldBe(2)
            }
        }
    }
})