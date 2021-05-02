package com.supermarkt.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TipoPagamentoAPITest {
	
	private static final String TIPOS_PAGAMENTO = "/tipo-pagamento";
	private static final String ADMIN_TIPOS_PAGAMENTO = "/admin/tipo-pagamento";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TipoPagamentoRepositorio repo;
	
	@Autowired
	private ObjectMapper json;
	
	private TipoPagamento visaCredito;
	private TipoPagamento ticketSupermercado;
	private List<TipoPagamento> tiposPagamento;
	
	@Before
	public void antes() {
		visaCredito = new TipoPagamento(1L, TipoPagamento.Forma.CARTAO_CREDITO, "Visa Crédito");
		ticketSupermercado = new TipoPagamento(2L, TipoPagamento.Forma.VALE_ALIMENTACAO, "Ticket Supermercado");

		tiposPagamento = Arrays.asList(visaCredito, ticketSupermercado);
	}

	@Test
	public void todas() throws Exception {
		Mockito.when(repo.findAllByOrderByNomeAsc()).thenReturn(Optional.of(tiposPagamento));

		this.mockMvc.perform(get(TIPOS_PAGAMENTO)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(2)).andExpect(jsonPath("$.[0].id").value(1L))
				.andExpect(jsonPath("$.[0].forma").value(TipoPagamento.Forma.CARTAO_CREDITO.name()))
				.andExpect(jsonPath("$.[0].nome").value("Visa Crédito")).andExpect(jsonPath("$.[1].id").value(2L))
				.andExpect(jsonPath("$.[1].forma").value(TipoPagamento.Forma.VALE_ALIMENTACAO.name()))
				.andExpect(jsonPath("$.[1].nome").value("Ticket Supermercado"));

		Mockito.verify(repo).findAllByOrderByNomeAsc();

	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void formas() throws Exception {
		this.mockMvc.perform(get(ADMIN_TIPOS_PAGAMENTO+"/formas")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(3))
				.andExpect(jsonPath("$.[0].codigo").value("CARTAO_CREDITO"))
				.andExpect(jsonPath("$.[1].codigo").value("CARTAO_DEBITO"))
				.andExpect(jsonPath("$.[2].codigo").value("VALE_ALIMENTACAO"));

		Mockito.verifyZeroInteractions(repo);
	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void adiciona() throws Exception {
		TipoPagamento visaCreditoSemId = new TipoPagamento(null, TipoPagamento.Forma.CARTAO_CREDITO,
				"Visa Crédito");

		Mockito.when(repo.save(visaCreditoSemId)).thenReturn(visaCredito);

		this.mockMvc.perform(post(ADMIN_TIPOS_PAGAMENTO).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json.writeValueAsString(visaCreditoSemId))).andDo(print()).andExpect(status().isCreated());

		Mockito.verify(repo).save(visaCreditoSemId);
	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void atualiza() throws Exception {
		Mockito.when(repo.save(visaCredito)).thenReturn(visaCredito);

		this.mockMvc.perform(put(ADMIN_TIPOS_PAGAMENTO+"/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json.writeValueAsString(visaCredito))).andDo(print()).andExpect(status().isAccepted());

		Mockito.verify(repo).save(visaCredito);
	}

	@Test @WithMockUser(username="admin",roles={"ADMIN"})
	public void remove() throws Exception {
		Mockito.doNothing().when(repo).deleteById(1L);

		this.mockMvc.perform(delete(ADMIN_TIPOS_PAGAMENTO+"/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isNoContent());

		Mockito.verify(repo).deleteById(1L);
		;
	}

}
