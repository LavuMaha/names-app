package com.galvanize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class NameControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    NameService nameService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void nameControllerExists(){
        NameController nameController;
        nameController = new NameController(nameService);
        assertNotNull(nameController);
    }

    @Test
    public void postName() throws Exception{
        NewName name = new NewName("Perry");
        String json = objectMapper.writeValueAsString(name);
        name.setId(1);
        when(nameService.post(any(NewName.class))).thenReturn(name);
        mvc.perform(post("/names").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(name));
    }

    @Test
    public void getName() throws Exception{
        NewName name = new NewName(1L, "Perry");
        when(nameService.getName(anyLong())).thenReturn(Optional.of(name));
        mvc.perform(get("/names/"+name.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(name));
    }

    @Test
    public void patchName() throws Exception{
        NewName name = new NewName("Perry");
        String json = objectMapper.writeValueAsString(name);
        name.setId(1L);
        when(nameService.patchName(anyLong(), any(NewName.class))).thenReturn(Optional.of(name));
        mvc.perform(patch("/names/"+name.getId()).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteName() throws Exception{
        when(nameService.deleteName(anyLong())).thenReturn(true);
        mvc.perform(delete("/names/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void getAllNames() throws Exception{
        NewName name1 = new NewName(1L, "Perry");
        NewName name2 = new NewName(2L, "Platypus");
        List<NewName> nameList = Arrays.asList(name1, name2);
        when(nameService.getAllNames()).thenReturn(nameList);
        mvc.perform(get("/names"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(nameList.get(0)))
                .andExpect(jsonPath("$[1]").value(nameList.get(1)));
    }

}
