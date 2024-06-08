import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals


class LightingTest {
    private val outContent = ByteArrayOutputStream()
    private val errContent = ByteArrayOutputStream()
    private val originalOut: PrintStream = System.out
    private val originalErr: PrintStream = System.err

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

//    @Test
//    fun testTurnOnLog() {
//        val lighting = Lighting()
//        val lightingOnCommand: Command = LightingOnCommand(lighting)
//        lightingOnCommand.execute()
//        assertEquals("Light was turned on." + System.lineSeparator(), outContent.toString())
//    }

    @Test
    fun testCommandInvoker() {
        val lighting = Lighting()
        val lightingOnCommand: Command = LightingOnCommand(lighting)

        val commandInvoker = CommandInvoker()
        commandInvoker.setCommand(lightingOnCommand)
        commandInvoker.runCommand()

        assertEquals("Light was turned on." + System.lineSeparator(), outContent.toString())
    }

    @Test
    fun testThrowCommandMustBeSetException() {
        val commandInvoker = CommandInvoker()

        val exception = assertThrows<CommandMustBeSetException> {
            commandInvoker.runCommand()
        }
        assertEquals("Command must be set.", exception.message)
    }
}