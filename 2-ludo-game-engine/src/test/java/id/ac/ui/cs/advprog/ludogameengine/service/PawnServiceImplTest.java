package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Pawn;
import id.ac.ui.cs.advprog.ludogameengine.repository.PawnRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PawnServiceImplTest {

    @Mock
    private PawnRepository pawnRepository;

    @InjectMocks
    private PawnServiceImpl pawnService;

    private Pawn pawn;

    @Test
    void findById() {
        when(pawnRepository.findById(pawn.getId())).thenReturn(pawn);
        assertEquals(pawnService.findById(pawn.getId()), pawn);
    }

    @Test
    void updateLastPosition() {

    }
}
