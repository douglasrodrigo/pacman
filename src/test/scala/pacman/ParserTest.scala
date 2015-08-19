package pacman

import org.specs2.mutable._
import org.specs2.matcher.ParserMatchers

class ParserTest extends Specification with ParserMatchers {
  val parsers = pacman.Parser

  private val eol = sys.props("line.separator")

  "Parser pacman" should {
    "Sucesso ao reconhecer coordenada do pacman" in {
      Parser.pacman("P=(1,2)") must beASuccess.withResult(Pacman(1,2))
    }

    "Sucesso ao reconhecer coordenada da comida" in {
      Parser.food("C=(10,8)") must beASuccess.withResult(Food(10,8))
    }

    "Sucesso ao reconhecer o tamanho da grid" in {
      Parser.gridSize("10x8") must beASuccess.withResult((10,8))
    }

    "Sucesso ao reconhecer um mapa" in {
      Parser.pacmanGrid("P=(0,1)\nC=(1,3)\n2x4\n█☺·█\n···ϖ") must beASuccess
    }
  }
}
