import static org.junit.jupiter.api.Assertions.*; // Importa assertThrows y assertEquals
import org.junit.jupiter.api.Test; // Importa la anotación @Test
import java.util.*;

class UsuarioTest {

    @Test
    void testValorarConciertoNullLanzaExcepcion() {
        // Usuario de prueba vacío
        Usuario u = new Usuario("Pepe", 25, new ArrayList<>(), new HashSet<>(), new HashMap<>());

        // Comprobación de que que al pasar 'null' salta la Exception
        Exception ex = assertThrows(Exception.class, () -> {
            u.valorar(null, 5);
        });

        // Oráculo: Verificación de que el mensaje es el que puse en el throw
        assertEquals("No puedes valorar un concierto al que no has asistido.", ex.getMessage());
    }
}