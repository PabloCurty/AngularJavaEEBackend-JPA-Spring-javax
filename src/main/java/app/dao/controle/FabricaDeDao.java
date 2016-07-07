package app.dao.controle;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDao {
	// Esse m�todo pode ser executado de 2 formas:
	// 1. produtoDAO =
	// FabricaDeDao.<ProdutoDAOImpl>getDao(ProdutoDAOImpl.class);
	// 2. produtoDAO = FabricaDeDao.getDao(ProdutoDAOImpl.class);

	@SuppressWarnings("unchecked")
	public static <T> T getDao(String classeDoDaoComoString) throws Exception {
		
		// recebe string dao.impl.ProdutoDAOImpl
		Class<?> classeDoDao = Class.forName(classeDoDaoComoString); 

		
		// m�todo create retorna objeto que � subclasse de produto dao impl pelo parametro classe do dao
		// faz override dos metodos n�o final(nosso caso s�o s� 3)
		// chama o m�todo do interceptador
		
		// cria classe que extende produto dao impl(cria um proxy)
		return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO()); // biblioteca para criar proxy cgLib

		// O proxy deve estender a classe (ProdutoDAOImpl por exemplo),
		// que deve estender a classe JPADaoGenerico. O proxy deve ainda
		// chamar o m�todo intercept() da classe interceptadora, isto �, da
		// classe InterceptadorDeDAO (classe callback).

		// Enhancer enhancer = new Enhancer();
		// enhancer.setSuperclass(classeDoDao); // Superclasse do DAO
		// enhancer.setCallback(new InterceptadorDeDAO()); // Interceptador do
		// DAO

		// return (T) enhancer.create();
	}
}