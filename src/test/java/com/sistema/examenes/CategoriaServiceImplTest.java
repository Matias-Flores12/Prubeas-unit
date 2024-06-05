package com.sistema.examenes;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.repositorios.CategoriaRepository;
import com.sistema.examenes.servicios.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoriaServiceImplTest {

    // Mock para la dependencia de CategoriaRepository
    @Mock
    private CategoriaRepository categoriaRepository;

    // Inyección del mock en la instancia de CategoriaServiceImpl
    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    // Variable para la categoría de prueba
    private Categoria categoria;

    // Método que se ejecuta antes de cada prueba para inicializar los mocks y la categoría de prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria();
        categoria.setCategoriaId(1L);
        categoria.setTitulo("Matemáticas");
        categoria.setDescripcion("Descripción de Matemáticas");
    }

    // Prueba para el método agregarCategoria
    @Test
    void agregarCategoria() {
        // Simulación del comportamiento del repositorio al guardar una categoría
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        // Llamada al método agregarCategoria del servicio
        Categoria nuevaCategoria = categoriaService.agregarCategoria(categoria);

        // Verificaciones de la prueba
        assertNotNull(nuevaCategoria); // Verifica que la nueva categoría no es nula
        assertEquals(categoria.getTitulo(), nuevaCategoria.getTitulo()); // Verifica que el título de la categoría es correcto
    }

    // Prueba para el método actualizarCategoria
    @Test
    void actualizarCategoria() {
        // Simulación del comportamiento del repositorio al actualizar una categoría
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        // Llamada al método actualizarCategoria del servicio
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(categoria);

        // Verificaciones de la prueba
        assertNotNull(categoriaActualizada); // Verifica que la categoría actualizada no es nula
        assertEquals(categoria.getTitulo(), categoriaActualizada.getTitulo()); // Verifica que el título de la categoría es correcto
    }

    // Prueba para el método obtenerCategorias
    @Test
    void obtenerCategorias() {
        // Creación de un conjunto de categorías de prueba
        Set<Categoria> categorias = new LinkedHashSet<>();
        categorias.add(categoria);

        // Simulación del comportamiento del repositorio al obtener todas las categorías
        when(categoriaRepository.findAll()).thenReturn(new ArrayList<>(categorias));

        // Llamada al método obtenerCategorias del servicio
        Set<Categoria> categoriasObtenidas = categoriaService.obtenerCategorias();

        // Verificaciones de la prueba
        assertNotNull(categoriasObtenidas); // Verifica que las categorías obtenidas no son nulas
        assertEquals(1, categoriasObtenidas.size()); // Verifica que el tamaño del conjunto de categorías es el esperado
    }

    // Prueba para el método obtenerCategoria
    @Test
    void obtenerCategoria() {
        // Simulación del comportamiento del repositorio al obtener una categoría por su ID
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoria));

        // Llamada al método obtenerCategoria del servicio
        Categoria categoriaObtenida = categoriaService.obtenerCategoria(1L);

        // Verificaciones de la prueba
        assertNotNull(categoriaObtenida); // Verifica que la categoría obtenida no es nula
        assertEquals(categoria.getTitulo(), categoriaObtenida.getTitulo()); // Verifica que el título de la categoría es correcto
    }

    // Prueba para el método eliminarCategoria
    @Test
    void eliminarCategoria() {
        // Simulación del comportamiento del repositorio al eliminar una categoría
        doNothing().when(categoriaRepository).delete(any(Categoria.class));

        // Llamada al método eliminarCategoria del servicio
        categoriaService.eliminarCategoria(1L);

        // Verificación de la prueba
        verify(categoriaRepository, times(1)).delete(any(Categoria.class)); // Verifica que el método delete se llamó una vez
    }
}
