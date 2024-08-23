package dev.oscar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaAhorrosTest {


    private CuentaAhorros cuentaAhorros;

    @BeforeEach
    public void setUp() {
     
        cuentaAhorros = new CuentaAhorros(10000.0f, 0.12f);
    }

    @Test
    public void testInicializacionActiva() {

        assertTrue(cuentaAhorros.activa);
    }

    @Test
    public void testInicializacionInactiva() {
     
        CuentaAhorros cuentaInactiva = new CuentaAhorros(5000.0f, 0.12f);
        assertFalse(cuentaInactiva.activa);
    }

    @Test
    public void testConsignarConCuentaActiva() {
        cuentaAhorros.consignar(2000.0f);
        assertEquals(12000.0f, cuentaAhorros.saldo, 0.001f);
        assertEquals(1, cuentaAhorros.numConsignaciones);
    }

    @Test
    public void testConsignarConCuentaInactiva() {
        CuentaAhorros cuentaInactiva = new CuentaAhorros(9000.0f, 0.12f);
        cuentaInactiva.consignar(2000.0f);
      
        assertEquals(9000.0f, cuentaInactiva.saldo, 0.001f);
        assertEquals(0, cuentaInactiva.numConsignaciones);
    }

    @Test
    public void testRetirarConCuentaActiva() {
        cuentaAhorros.retirar(5000.0f);
        assertEquals(5000.0f, cuentaAhorros.saldo, 0.001f);
        assertEquals(1, cuentaAhorros.numRetiros);
    }

    @Test
    public void testRetirarConCuentaInactiva() {
        CuentaAhorros cuentaInactiva = new CuentaAhorros(9000.0f, 0.12f);
        cuentaInactiva.retirar(1000.0f);
       
        assertEquals(9000.0f, cuentaInactiva.saldo, 0.001f);
        assertEquals(0, cuentaInactiva.numRetiros);
    }

    @Test
    public void testExtractoMensualConMasDeCuatroRetiros() {
      
        cuentaAhorros.retirar(1000.0f);
        cuentaAhorros.retirar(1000.0f);
        cuentaAhorros.retirar(1000.0f);
        cuentaAhorros.retirar(1000.0f);
        cuentaAhorros.retirar(1000.0f);
        
        cuentaAhorros.extractoMensual();
        
   
        assertEquals(4040.0f, cuentaAhorros.saldo, 0.001f);
        assertEquals(1000.0f, cuentaAhorros.comMensual, 0.001f);
    }

    @Test
    public void testCuentaSeInactivaDespuesDeExtractoMensual() {
        cuentaAhorros.retirar(9500.0f);
        cuentaAhorros.extractoMensual();
        
      
        assertFalse(cuentaAhorros.activa);
    }

    @Test
    public void testImprimir() {
        cuentaAhorros.consignar(2000.0f);
        cuentaAhorros.retirar(1000.0f);
        cuentaAhorros.extractoMensual();

     
        cuentaAhorros.imprimir();
    }
    
}
