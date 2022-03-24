package controller;

import java.util.concurrent.Semaphore;

public class ControllerBanco extends Thread {
	
	private int conta;
	private int valor;
	private double saldo;
	private Semaphore semaforo1;
	private Semaphore semaforo2;
	
	public ControllerBanco(int conta, int valor, double saldo, Semaphore semaforo1, Semaphore semaforo2) {
		super();
		this.conta = conta;
		this.valor = valor;
		this.saldo = saldo;
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
	}
	
	@Override
	public void run() {		
			if (conta % 2 == 0){
				try{
					semaforo1.acquire();
					sacar();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo1.release();
				}
			} else {
				try{
					semaforo2.acquire();
					depositar();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo2.release();
				}
			}
	}

	

	private void depositar() {
		try {
			System.out.println("Realizando depósito...");
			saldo += valor;
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Depósito na conta " + conta + " realizado com sucesso!");
		
	}

	private void sacar() {
		try {
			System.out.println("Realizando saque...");
			saldo -= valor;
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Saque da conta " + conta + " realizado com sucesso!");
	}
}