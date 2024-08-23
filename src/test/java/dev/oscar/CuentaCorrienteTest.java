package dev.oscar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaCorrienteTest {

     private CuentaCorriente cuentaCorriente;

    @BeforeEach
    public void setUp() {
        cuentaCorriente = new CuentaCorriente(1000.0f, 0.12f); // saldo inicial 1000 y tasa anual 12%
    }

    @Test
    public void testRetirarSinSobregiro() {
        cuentaCorriente.retirar(500.0f);
        assertEquals(500.0f, cuentaCorriente.saldo, 0.001f);
        assertEquals(0.0f, cuentaCorriente.sobregiro, 0.001f);
    }

    @Test
    public void testRetirarConSobregiro() {
        cuentaCorriente.retirar(1500.0f);
        assertEquals(0.0f, cuentaCorriente.saldo, 0.001f);
        assertEquals(500.0f, cuentaCorriente.sobregiro, 0.001f);
    }

    @Test
    public void testConsignarParaCubrirSobregiro() {
        cuentaCorriente.retirar(1500.0f); // sobregiro de 500
        cuentaCorriente.consignar(700.0f); // Consignación de 700
        assertEquals(200.0f, cuentaCorriente.saldo, 0.001f);
        assertEquals(0.0f, cuentaCorriente.sobregiro, 0.001f);
    }

    @Test
    public void testConsignarSinSobregiro() {
        cuentaCorriente.consignar(500.0f);
        assertEquals(1500.0f, cuentaCorriente.saldo, 0.001f);
        assertEquals(0.0f, cuentaCorriente.sobregiro, 0.001f);
    }

    @Test
    public void testExtractoMensual() {
        cuentaCorriente.retirar(1500.0f); // sobregiro de 500
        cuentaCorriente.comMensual = 10.0f;
        cuentaCorriente.extractoMensual();
        
        assertEquals(0.0f, cuentaCorriente.saldo, 0.001f);
        assertEquals(510.0f, cuentaCorriente.sobregiro, 0.001f);
        assertEquals(10.0f, cuentaCorriente.comMensual, 0.001f);
    }

    @Test
    public void testImprimir() {
        cuentaCorriente.retirar(1200.0f); // sobregiro de 200
        cuentaCorriente.consignar(300.0f); // Cubre el sobregiro y deja saldo
        cuentaCorriente.imprimir();
        
        // No se verifica la salida en consola, pero se asegura de que el método no cause errores
    }
}
