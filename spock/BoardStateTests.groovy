import spock.lang.*
import com.greenmoonsoftware.eightpuzzle.*

class BoardStateTests extends spock.lang.Specification {
  def "canary test"() {
    setup:
      def boardState = new BoardState("123456780", null)
    when:
      def cost = boardState.calculateCost()
      
    then:
      cost == 0
  }
  
  def "simple test"() {
    setup:
      def boardState = new BoardState(i, null)
    when:
      def cost = boardState.calculateCost()
    then:
      cost == c
    where:
      i|c
      "123450786"|1
      "123456708"|1
      "012345678"|12
  }
}