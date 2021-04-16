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
    val videoFiesta = Video(65)
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

    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
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
        it("de calidad 720HD ocupa tantos bytes como su duracion * 3")
        {
          videoFiesta.cambiarCalidad(720)
          videoFiesta.espacioQueOcupa().shouldBe(195)
        }
        it("de calidad 1080HD ocupa el doble de bytes que el 720HD")
        {
          videoFiesta.cambiarCalidad(1080)
          videoFiesta.espacioQueOcupa().shouldBe(390)
        }
      }


      describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(550548)
      }
      it("cantidad de amigos del usuario"){
        sava.esMasAmistosoQue(pizzi).shouldBeTrue()
        beccacece.esMasAmistosoQue(russo).shouldBeTrue()
        llop.esMasAmistosoQue(zubeldia).shouldBeFalse()
      }
    }

    describe("Stalkear") {
      it("Un usuario stalkea a otro:"){
        vivas.esStalkeadoPor(sava).shouldBeTrue()
      }
    }
  }
})
