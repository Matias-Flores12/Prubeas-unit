package com.sistema.examenes;

import java.util.ArrayList;
import com.sistema.examenes.modelo.Examen;
import com.sistema.examenes.modelo.Pregunta;
import com.sistema.examenes.repositorios.PreguntaRepository;
import com.sistema.examenes.servicios.impl.PreguntaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PreguntaServiceImplTest {

    // Mock para la dependencia de PreguntaRepository
    @Mock
    private PreguntaRepository preguntaRepository;

    // Inyección del mock en la instancia de PreguntaServiceImpl
    @InjectMocks
    private PreguntaServiceImpl preguntaService;

    // Variables para los objetos de prueba
    private Pregunta pregunta;
    private Examen examen;

    // Método que se ejecuta antes de cada prueba para inicializar los mocks y los objetos de prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        examen = new Examen();
        examen.setExamenId(1L);

        pregunta = new Pregunta();
        pregunta.setPreguntaId(1L);
        pregunta.setContenido("¿Cuál es la capital de Francia?");
        pregunta.setExamen(examen);
        pregunta.setOpcion1("París");
        pregunta.setOpcion2("Londres");
        pregunta.setOpcion3("Berlín");
        pregunta.setOpcion4("Madrid");
        pregunta.setRespuesta("París");
    }

    // Prueba para el método agregarPregunta
    @Test
    void agregarPregunta() {
        // Simulación del comportamiento del repositorio al guardar una pregunta
        when(preguntaRepository.save(any(Pregunta.class))).thenReturn(pregunta);

        // Llamada al método agregarPregunta del servicio
        Pregunta nuevaPregunta = preguntaService.agregarPregunta(pregunta);

        // Verificaciones de la prueba
        assertNotNull(nuevaPregunta); // Verifica que la nueva pregunta no es nula
        assertEquals(pregunta.getContenido(), nuevaPregunta.getContenido()); // Verifica que el contenido de la pregunta es correcto
    }

    // Prueba para el método actualizarPregunta
    @Test
    void actualizarPregunta() {
        // Simulación del comportamiento del repositorio al actualizar una pregunta
        when(preguntaRepository.save(any(Pregunta.class))).thenReturn(pregunta);

        // Llamada al método actualizarPregunta del servicio
        Pregunta preguntaActualizada = preguntaService.actualizarPregunta(pregunta);

        // Verificaciones de la prueba
        assertNotNull(preguntaActualizada); // Verifica que la pregunta actualizada no es nula
        assertEquals(pregunta.getContenido(), preguntaActualizada.getContenido()); // Verifica que el contenido de la pregunta es correcto
    }

    // Prueba para el método obtenerPreguntas
    @Test
    void obtenerPreguntas() {
        // Creación de un conjunto de preguntas de prueba
        Set<Pregunta> preguntas = new HashSet<>();
        preguntas.add(pregunta);

        // Simulación del comportamiento del repositorio al obtener todas las preguntas
        when(preguntaRepository.findAll()).thenReturn(new ArrayList<>(preguntas));

        // Llamada al método obtenerPreguntas del servicio
        Set<Pregunta> preguntasObtenidas = preguntaService.obtenerPreguntas();

        // Verificaciones de la prueba
        assertNotNull(preguntasObtenidas); // Verifica que las preguntas obtenidas no son nulas
        assertEquals(1, preguntasObtenidas.size()); // Verifica que el tamaño del conjunto de preguntas es el esperado
    }

    // Prueba para el método obtenerPregunta
    @Test
    void obtenerPregunta() {
        // Simulación del comportamiento del repositorio al obtener una pregunta por su ID
        when(preguntaRepository.findById(anyLong())).thenReturn(Optional.of(pregunta));

        // Llamada al método obtenerPregunta del servicio
        Pregunta preguntaObtenida = preguntaService.obtenerPregunta(1L);

        // Verificaciones de la prueba
        assertNotNull(preguntaObtenida); // Verifica que la pregunta obtenida no es nula
        assertEquals(pregunta.getContenido(), preguntaObtenida.getContenido()); // Verifica que el contenido de la pregunta es correcto
    }

    // Prueba para el método obtenerPreguntasDelExamen
    @Test
    void obtenerPreguntasDelExamen() {
        // Creación de un conjunto de preguntas de prueba
        Set<Pregunta> preguntas = new HashSet<>();
        preguntas.add(pregunta);

        // Simulación del comportamiento del repositorio al obtener preguntas por examen
        when(preguntaRepository.findByExamen(any(Examen.class))).thenReturn(preguntas);

        // Llamada al método obtenerPreguntasDelExamen del servicio
        Set<Pregunta> preguntasObtenidas = preguntaService.obtenerPreguntasDelExamen(examen);

        // Verificaciones de la prueba
        assertNotNull(preguntasObtenidas); // Verifica que las preguntas obtenidas no son nulas
        assertEquals(1, preguntasObtenidas.size()); // Verifica que el tamaño del conjunto de preguntas es el esperado
    }

    // Prueba para el método eliminarPregunta
    @Test
    void eliminarPregunta() {
        // Simulación del comportamiento del repositorio al eliminar una pregunta por su ID
        doNothing().when(preguntaRepository).deleteById(anyLong());

        // Llamada al método eliminarPregunta del servicio
        preguntaService.eliminarPregunta(1L);

        // Verificación de la prueba
        verify(preguntaRepository, times(1)).deleteById(anyLong()); // Verifica que el método deleteById se llamó una vez
    }

    // Prueba para el método listarPregunta
    @Test
    void listarPregunta() {
        // Simulación del comportamiento del repositorio al obtener una pregunta por su ID
        when(preguntaRepository.getOne(anyLong())).thenReturn(pregunta);

        // Llamada al método listarPregunta del servicio
        Pregunta preguntaObtenida = preguntaService.listarPregunta(1L);

        // Verificaciones de la prueba
        assertNotNull(preguntaObtenida); // Verifica que la pregunta obtenida no es nula
        assertEquals(pregunta.getContenido(), preguntaObtenida.getContenido()); // Verifica que el contenido de la pregunta es correcto
    }
}
