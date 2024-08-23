package dev.oscar;

public class CuentaAhorros extends Cuenta{

 public boolean activa;

public CuentaAhorros(float saldo, float tasa) {
super(saldo, tasa);
if (saldo < 10000) 
activa = false;
else
activa = true;
}

public void retirar(float cantidad) {
if (activa) 
super.retirar(cantidad); 
}

public void consignar(float cantidad) {
    if (activa) 
    super.consignar(cantidad); 
}

public void extractoMensual() {

if (numRetiros > 4) {
    comMensual += (numRetiros - 4) * 1000;
}
super.extractoMensual(); 
if ( saldo < 10000 )
activa = false;
}

public void imprimir() {
System.out.println("Saldo = " + saldo + " €");
System.out.println("Comisión mensual = " + comMensual+ " €");
System.out.println("Número de transacciones = " +(numConsignaciones + numRetiros));

}
    
}
