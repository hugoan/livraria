package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {

	@AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {
		
		String metodo = context.getMethod().getName();
		String nomeClasse = context.getTarget().getClass().getSimpleName();
		
		long millis = System.currentTimeMillis();

		// chamada do método DAO
		Object object = context.proceed();
		System.out.println("[INFO] " + nomeClasse + " -> " + metodo);
		System.out.println("[INFO] Tempo gasto no acesso ao BD: " + (System.currentTimeMillis() - millis) + "ms");
		return object;
	}
}
