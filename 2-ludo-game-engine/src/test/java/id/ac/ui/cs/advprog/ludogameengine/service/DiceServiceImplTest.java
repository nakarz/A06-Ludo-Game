package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Dice;
import id.ac.ui.cs.advprog.ludogameengine.repository.DiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiceServiceImplTest {

    @Mock
    private DiceRepository diceRepository;

    @InjectMocks
    private DiceServiceImpl diceService;

    private Dice dice;

    @Test
    void findById() {
        when(diceRepository.findById(dice.getId())).thenReturn(dice);
        assertEquals(diceService.findById(dice.getId()), dice);
    }

    @Test
    void playerChance() {

    }
}
