package com.sistema.examenes;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.modelo.Examen;
import com.sistema.examenes.repositorios.ExamenRepository;
import com.sistema.examenes.servicios.impl.ExamenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExamenServiceImplTest {

    // Mock para la dependencia de ExamenRepository
    @Mock
    private ExamenRepository examenRepository;

    // Inyección del mock en la instancia de ExamenServiceImpl
    @InjectMocks
    private ExamenServiceImpl examenService;

    // Variables para los objetos de prueba
    private Examen examen;
    private Categoria categoria;

    // Método que se ejecuta antes de cada prueba para inicializar los mocks y los objetos de prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria();
        categoria.setCategoriaId(1L);

        examen = new Examen();
        examen.setExamenId(1L);
        examen.setTitulo("Examen de Matemáticas");
        examen.setDescripcion("Descripción del examen de matemáticas");
        examen.setCategoria(categoria);
        examen.setActivo(true);
    }

    // Prueba para el método agregarExamen
    @Test
    void agregarExamen() {
        // Simulación del comportamiento del repositorio al guardar un examen
        when(examenRepository.save(any(Examen.class))).thenReturn(examen);

        // Llamada al método agregarExamen del servicio
        Examen nuevoExamen = examenService.agregarExamen(examen);

        // Verificaciones de la prueba
        assertNotNull(nuevoExamen); // Verifica que el nuevo examen no es nulo
        assertEquals(examen.getTitulo(), nuevoExamen.getTitulo()); // Verifica que el título del examen es correcto
    }

    // Prueba para el método actualizarExamen
    @Test
    void actualizarExamen() {
        // Simulación del comportamiento del repositorio al actualizar un examen
        when(examenRepository.save(any(Examen.class))).thenReturn(examen);

        // Llamada al método actualizarExamen del servicio
        Examen examenActualizado = examenService.actualizarExamen(examen);

        // Verificaciones de la prueba
        assertNotNull(examenActualizado); // Verifica que el examen actualizado no es nulo
        assertEquals(examen.getTitulo(), examenActualizado.getTitulo()); // Verifica que el título del examen es correcto
    }

    // Prueba para el método obtenerExamenes
    @Test
    void obtenerExamenes() {
        // Creación de un conjunto de exámenes de prueba
        Set<Examen> examenes = new LinkedHashSet<>();
        examenes.add(examen);

        // Simulación del comportamiento del repositorio al obtener todos los exámenes
        when(examenRepository.findAll()).thenReturn(new ArrayList<>(examenes));

        // Llamada al método obtenerExamenes del servicio
        Set<Examen> examenesObtenidos = examenService.obtenerExamenes();

        // Verificaciones de la prueba
        assertNotNull(examenesObtenidos); // Verifica que los exámenes obtenidos no son nulos
        assertEquals(1, examenesObtenidos.size()); // Verifica que el tamaño del conjunto de exámenes es el esperado
    }

    // Prueba para el método obtenerExamen
    @Test
    void obtenerExamen() {
        // Simulación del comportamiento del repositorio al obtener un examen por su ID
        when(examenRepository.findById(anyLong())).thenReturn(Optional.of(examen));

        // Llamada al método obtenerExamen del servicio
        Examen examenObtenido = examenService.obtenerExamen(1L);

        // Verificaciones de la prueba
        assertNotNull(examenObtenido); // Verifica que el examen obtenido no es nulo
        assertEquals(examen.getTitulo(), examenObtenido.getTitulo()); // Verifica que el título del examen es correcto
    }

    // Prueba para el método eliminarExamen
    @Test
    void eliminarExamen() {
        // Simulación del comportamiento del repositorio al eliminar un examen
        doNothing().when(examenRepository).delete(any(Examen.class));

        // Llamada al método eliminarExamen del servicio
        examenService.eliminarExamen(1L);

        // Verificación de la prueba
        verify(examenRepository, times(1)).delete(any(Examen.class)); // Verifica que el método delete se llamó una vez
    }

    // Prueba para el método listarExamenesDeUnaCategoria
    @Test
    void listarExamenesDeUnaCategoria() {
        // Creación de una lista de exámenes de prueba
        List<Examen> examenes = new ArrayList<>();
        examenes.add(examen);

        // Simulación del comportamiento del repositorio al obtener exámenes por categoría
        when(examenRepository.findByCategoria(any(Categoria.class))).thenReturn(examenes);

        // Llamada al método listarExamenesDeUnaCategoria del servicio
        List<Examen> examenesObtenidos = examenService.listarExamenesDeUnaCategoria(categoria);

        // Verificaciones de la prueba
        assertNotNull(examenesObtenidos); // Verifica que los exámenes obtenidos no son nulos
        assertEquals(1, examenesObtenidos.size()); // Verifica que el tamaño de la lista de exámenes es el esperado
    }

    // Prueba para el método obtenerExamenesActivos
    @Test
    void obtenerExamenesActivos() {
        // Creación de una lista de exámenes de prueba
        List<Examen> examenes = new ArrayList<>();
        examenes.add(examen);

        // Simulación del comportamiento del repositorio al obtener exámenes activos
        when(examenRepository.findByActivo(anyBoolean())).thenReturn(examenes);

        // Llamada al método obtenerExamenesActivos del servicio
        List<Examen> examenesActivos = examenService.obtenerExamenesActivos();

        // Verificaciones de la prueba
        assertNotNull(examenesActivos); // Verifica que los exámenes activos obtenidos no son nulos
        assertEquals(1, examenesActivos.size()); // Verifica que el tamaño de la lista de exámenes activos es el esperado
    }

    // Prueba para el método obtenerExamenesActivosDeUnaCategoria
    @Test
    void obtenerExamenesActivosDeUnaCategoria() {
        // Creación de una lista de exámenes de prueba
        List<Examen> examenes = new ArrayList<>();
        examenes.add(examen);

        // Simulación del comportamiento del repositorio al obtener exámenes activos por categoría
        when(examenRepository.findByCategoriaAndActivo(any(Categoria.class), anyBoolean())).thenReturn(examenes);

        // Llamada al método obtenerExamenesActivosDeUnaCategoria del servicio
        List<Examen> examenesActivos = examenService.obtenerExamenesActivosDeUnaCategoria(categoria);

        // Verificaciones de la prueba
        assertNotNull(examenesActivos); // Verifica que los exámenes activos obtenidos no son nulos
        assertEquals(1, examenesActivos.size()); // Verifica que el tamaño de la lista de exámenes activos es el esperado
    }
}
