package com.via.via.v1.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.via.via.customer.Customer;
import com.via.via.customer.CustomerRepository;
import com.via.via.customer.CustomerService;
import com.via.via.policy.PolicyRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTest() throws Exception {

        CustomerDto customer = new CustomerDto(null, "07450704420", "Agay Borges", "Recife", "PE");

        ObjectId id = ObjectId.get();

        given(customerService.create(any(Customer.class))).willAnswer((invocation) -> id);

        mockMvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(customer))
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(id.toString())));
    }

    @Test
    public void getTest() throws Exception {

        ObjectId id = ObjectId.get();
        Customer customer = new Customer(id, "07450704420", "Agay Borges", "Recife", "PE");

        given(customerService.retrieve(any(ObjectId.class))).willAnswer((invocation) -> Optional.of(customer));

        mockMvc.perform(get("/customers" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.cpf", is(customer.getCpf())))
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.city", is(customer.getCity())))
                .andExpect(jsonPath("$.uf", is(customer.getUf())));
    }

    @Test
    public void findAllTest() throws Exception {

        ObjectId id = ObjectId.get();
        Customer customer = new Customer(id, "07450704420", "Agay Borges", "Recife", "PE");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        Page<Customer> page = new PageImpl<>(customers);

        given(customerService.findAll(0, 10)).willAnswer((invocation) -> page);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(1)))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.content[0].cpf", is(customer.getCpf())));
    }

    @Test
    public void getNoFoundTest() throws Exception {

        ObjectId id = ObjectId.get();

        given(customerService.retrieve(any(ObjectId.class))).willAnswer((invocation) -> Optional.empty());

        mockMvc.perform(get("/customers/" + id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUpdateTest() throws Exception {

        ObjectId id = ObjectId.get();
        Customer customer = new Customer(id, "07450704420", "Agay Borges", "Recife", "PE");

        given(customerService.update(any(Customer.class))).willAnswer((invocation) -> true);

        mockMvc.perform(put("/customers/" + id.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer))
        )
                .andExpect(status().isNoContent());
    }


    @Test
    public void getUpdateCreatedTest() throws Exception {

        ObjectId id = ObjectId.get();
        Customer customer = new Customer(id, "07450704420", "Agay Borges", "Recife", "PE");

        given(customerService.update(any(Customer.class))).willAnswer((invocation) -> false);

        mockMvc.perform(put("/customers/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer))
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(id.toString())));
    }

    @Test
    public void deleteTest() throws Exception {

        ObjectId id = ObjectId.get();

        given(customerService.delete(any(ObjectId.class))).willAnswer((invocation) -> true);

        mockMvc.perform(delete("/customers/" + id.toString()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteNoFoundTest() throws Exception {

        ObjectId id = ObjectId.get();

        given(customerService.delete(any(ObjectId.class))).willAnswer((invocation) -> false);

        mockMvc.perform(delete("/customers/" + id.toString()))
                .andExpect(status().isNotFound());
    }
}
