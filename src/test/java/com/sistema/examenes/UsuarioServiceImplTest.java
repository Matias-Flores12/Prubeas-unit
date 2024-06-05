package com.sistema.examenes;

import com.sistema.examenes.modelo.Rol;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import com.sistema.examenes.repositorios.RolRepository;
import com.sistema.examenes.repositorios.UsuarioRepository;
import com.sistema.examenes.servicios.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UsuarioServiceImplTest {

    // Mocks para las dependencias de UsuarioServiceImpl
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    // Inyección de mocks en la instancia de UsuarioServiceImpl
    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    // Método que se ejecuta antes de cada prueba para inicializar los mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para el método guardarUsuario
    @Test
    void guardarUsuario() throws Exception {
        // Creación de un nuevo usuario y rol
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        // Asociación del rol con el usuario
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        // Creación de un set de roles de usuario
        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        usuarioRoles.add(usuarioRol);

        // Definición de comportamiento de los mocks
        when(usuarioRepository.findByUsername("testuser")).thenReturn(null); // El usuario no existe
        when(usuarioRepository.save(usuario)).thenReturn(usuario); // El usuario se guarda correctamente

        // Llamada al método que se está probando
        Usuario savedUsuario = usuarioService.guardarUsuario(usuario, usuarioRoles);

        // Verificaciones de la prueba
        assertNotNull(savedUsuario); // Verifica que el usuario guardado no es nulo
        assertEquals("testuser", savedUsuario.getUsername()); // Verifica que el nombre de usuario es correcto
        verify(usuarioRepository, times(1)).save(usuario); // Verifica que el método save fue llamado una vez
    }

    // Prueba para el método obtenerUsuario
    @Test
    void obtenerUsuario() {
        // Creación de un usuario para la prueba
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");

        // Definición de comportamiento del mock
        when(usuarioRepository.findByUsername("testuser")).thenReturn(usuario);

        // Llamada al método que se está probando
        Usuario foundUsuario = usuarioService.obtenerUsuario("testuser");

        // Verificaciones de la prueba
        assertNotNull(foundUsuario); // Verifica que el usuario encontrado no es nulo
        assertEquals("testuser", foundUsuario.getUsername()); // Verifica que el nombre de usuario es correcto
    }

    // Prueba para el método eliminarUsuario
    @Test
    void eliminarUsuario() {
        // ID del usuario a eliminar
        Long usuarioId = 1L;

        // Definición de comportamiento del mock
        doNothing().when(usuarioRepository).deleteById(usuarioId);

        // Llamada al método que se está probando
        usuarioService.eliminarUsuario(usuarioId);

        // Verificación de la prueba
        verify(usuarioRepository, times(1)).deleteById(usuarioId); // Verifica que el método deleteById fue llamado una vez
    }
}
