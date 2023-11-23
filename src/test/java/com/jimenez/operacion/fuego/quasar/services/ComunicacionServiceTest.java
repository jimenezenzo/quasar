package com.jimenez.operacion.fuego.quasar.services;

import com.jimenez.operacion.fuego.quasar.models.dao.PositionDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ComunicacionServiceTest {

    @Autowired
    private IComunicacionService comunicacionService;

    @Test
    public void testCalculoDeCoordenada()
    {
        List<Float> positions = new ArrayList<Float>(List.of(
                100.0F, 115.5F, 142.7F
        ));

        PositionDao positionDao = this.comunicacionService.descifrarPosition(positions);

        assertEquals(-100.0, positionDao.x());
        assertEquals(75.5, positionDao.y());
    }

    @Test
    public void testDecifradoDeMensaja()
    {
        List<String[]> messages = new ArrayList<>(List.of(
                new String[]{"este", "", "", "mensaje", ""},
                new String[]{"", "es", "", "", "secreto"},
                new String[]{"este", "", "un", "", ""}
        ));

        String decrifrado = this.comunicacionService.descifrarMessage(messages);

        assertEquals("este es un mensaje secreto", decrifrado);
    }
}
