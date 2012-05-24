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
  
  @Unroll
  def "simple test"() {
    setup:
      ApplicationState.instance.initialize(3, 3, "1 2 3 4 5 6 7 8 0")
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
      "573681420"|14
      "780354612"|18
  }
}