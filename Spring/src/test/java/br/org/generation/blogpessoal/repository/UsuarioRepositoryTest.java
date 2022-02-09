package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest 
{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start()
	{
		//Ordem: id, email, senha, nome e foto.
		usuarioRepository.save(new Usuario(0L , "steven@email.com", "giantwoman", "Steven Universe ", null));
		usuarioRepository.save(new Usuario(0L, "sakura@email.com", "cardcaptors","Sakura Kinomoto Silva", null));
		usuarioRepository.save(new Usuario(0L, "finn@email.com", "horaaventura", "Finn The Human Silva", null));
		usuarioRepository.save(new Usuario(0L, "jolyne@email.com", "stonefree","Jolyne Cujoh Silva",null));
	}
	
	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario()
	{
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("steven@email.com");
		assertTrue(usuario.get().getUsuario().equals("steven@email.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuario()
	{
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Sakura Kinomoto Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Finn The Human Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Jolyne Cujoh Silva"));
		
	}
	
	@AfterAll
	void end()
	{
		usuarioRepository.deleteAll();
	}
}
