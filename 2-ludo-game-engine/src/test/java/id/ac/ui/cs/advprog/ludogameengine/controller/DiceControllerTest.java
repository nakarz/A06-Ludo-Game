package id.ac.ui.cs.advprog.ludogameengine.controller;

import id.ac.ui.cs.advprog.ludogameengine.model.Dice;
import id.ac.ui.cs.advprog.ludogameengine.repository.DiceRepository;
import id.ac.ui.cs.advprog.ludogameengine.service.DiceService;
import id.ac.ui.cs.advprog.ludogameengine.service.RollDiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DiceController.class)
public class DiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RollDiceService rollDiceService;

    @MockBean
    private DiceService diceService;

    @Mock
    private DiceRepository diceRepository;

    private Dice dice;

    @Test
    void allDiceData() {
        List<Dice> diceList = diceRepository.findAll();
        lenient().when(diceRepository.findAll()).thenReturn(diceList);
        List listDiceRes = diceRepository.findAll();
        Assertions.assertIterableEquals(diceList, listDiceRes);
    }

}
