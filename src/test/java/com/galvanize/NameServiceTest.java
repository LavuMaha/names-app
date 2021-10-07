package com.galvanize;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class NameServiceTest {
    @MockBean
    NameRepository nameRepository;

    @Test
    public void postName(){
        NameService nameService = new NameService(nameRepository);
        NewName before = new NewName("Perry");
        NewName after = new NewName(1L, "Perry");
        when(nameRepository.save(any(NewName.class))).thenReturn(after);
        assertEquals(after, nameService.post(before));
    }

    @Test
    public void getName(){
        NameService nameService = new NameService(nameRepository);
        NewName after = new NewName(1L, "Perry");
        when(nameRepository.findById(anyLong())).thenReturn(Optional.of(after));
        assertEquals(Optional.of(after), nameService.getName(after.getId()));
    }
}
