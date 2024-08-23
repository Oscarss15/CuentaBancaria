package dev.oscar;

public class CuentaCorriente extends Cuenta{

float sobregiro;


public CuentaCorriente(float saldo, float tasa) {
super(saldo, tasa); 
sobregiro = 0; 
}


public void retirar(float cantidad) {

float resultado = saldo - cantidad; 

if (resultado < 0) {
sobregiro = sobregiro - resultado;
saldo = 0;
} else {
super.retirar(cantidad);

}
}
public void consignar(float cantidad) {
    if (sobregiro > 0) {
        if (cantidad >= sobregiro) {
            saldo = cantidad - sobregiro;
            sobregiro = 0;
        } else {
            sobregiro -= cantidad;
        }
    } else {
        super.consignar(cantidad);
    }
}
public void extractoMensual() {
    if (saldo >= comMensual) {
        saldo -= comMensual;
    } else {
        sobregiro += comMensual;
        saldo = 0;
    }
    super.calcularInterés();
}
       
        public void imprimir() {
        System.out.println("Saldo = " + saldo +" €");
        System.out.println("Cargo mensual = " + comMensual + " €");
        System.out.println("Número de transacciones = " +(numConsignaciones + numRetiros));
        System.out.println("Valor de sogregiro = " + (numConsignaciones + numRetiros));
     
        }
}