package dev.oscar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CuentaTest {

       private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta(1000.0f, 0.12f); 
    }

    @Test
    public void testConsignar() {
        cuenta.consignar(500.0f);
        assertEquals(1500.0f, cuenta.saldo, 0.001f);
        assertEquals(1, cuenta.numConsignaciones);
    }

    @Test
    public void testRetirarExitoso() {
        cuenta.retirar(400.0f);
        assertEquals(600.0f, cuenta.saldo, 0.001f);
        assertEquals(1, cuenta.numRetiros);
    }

    @Test
    public void testRetirarFallido() {
        cuenta.retirar(1200.0f);
        assertEquals(1000.0f, cuenta.saldo, 0.001f);
        assertEquals(0, cuenta.numRetiros);
    }

    @Test
    public void testCalcularInteres() {
        cuenta.calcularInterés();
        assertEquals(1010.0f, cuenta.saldo, 0.001f); 
    }

    @Test
    public void testExtractoMensual() {
        cuenta.consignar(500.0f);
        cuenta.retirar(200.0f);
        cuenta.comMensual = 10.0f;
        cuenta.extractoMensual();

        assertEquals(1302.9f, cuenta.saldo, 0.001f); 
    }
    
}
