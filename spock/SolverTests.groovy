import spock.lang.*
import com.greenmoonsoftware.eightpuzzle.*

class SolverTests extends spock.lang.Specification {
  def "canary test"() {
    when:
      def solver = new Solver("1234567890")
      def result = solver.solve()
    then:
      result != null
      result.moves == 0
      result.calculations == 1
  }
}