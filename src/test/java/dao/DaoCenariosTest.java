package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.google.gson.Gson;

public class DaoCenariosTest {
	public Connection connection;
	public Statement stmt;
	String jsonDadosString = "";
	Map mapDadosTeste;

	public static void main(String[] args) {
		DaoCenariosTest daoCenarios = new DaoCenariosTest();
		try {

		daoCenarios.convertToJson();
		System.out.println(daoCenarios.getData("url"));
				
		daoCenarios.insertExecucaoTeste();
		daoCenarios.finalizaExecucaoTeste();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String loadData() throws SQLException {

		try {
			System.out.println("Abrindo Conexões");
			connection = ConnectionFactoryTest.getConnection();
			stmt = connection.createStatement();
			connection.setAutoCommit(false);

			String sqlJson = "SELECT  dt.MASSA_TESTE FROM DADOS_TESTE dt WHERE ID_CASO_TESTE = 1";

			System.out.println("Buscando Massas");
			ResultSet sqlJsonResult = stmt.executeQuery(sqlJson);
			while (sqlJsonResult.next()) {
				jsonDadosString = sqlJsonResult.getString(1);
			}

			stmt.close();
			connection.close();
			System.out.println("Conexões com Banco Fechadas");

		} catch (Exception e) {
			throw new SQLException(e);
		}
		return jsonDadosString;
	}
	
	public void insertExecucaoTeste() throws SQLException {

		try {
			System.out.println("Abrindo Conexões");
			connection = ConnectionFactoryTest.getConnection();
			stmt = connection.createStatement();
			connection.setAutoCommit(false);

			String sqlInsert = "INSERT INTO execucao_testes (id_caso_teste, status,data_hora_execucao) "
					+ "VALUES (1, 'Em execução',sysdate)";

			System.out.println("Inserindo Dados de Teste em execução ...");
			int statusInsert = stmt.executeUpdate(sqlInsert);
			
			if (statusInsert == 1) {
				System.out.println("Dados de Teste inseridos com sucesso!");
				connection.commit();
			
			}else {
				System.out.println("Erro ao inserir Dados de Teste");
				connection.rollback();
			}
			stmt.close();
			connection.close();
			System.out.println("Conexões com Banco Fechadas");

		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
	
	public void finalizaExecucaoTeste() throws SQLException {

		try {
			System.out.println("Abrindo Conexões");
			connection = ConnectionFactoryTest.getConnection();
			stmt = connection.createStatement();
			connection.setAutoCommit(false);

			String sqlUpdate = "UPDATE execucao_testes set status = 'Concluído'"
					+ "WHERE id_caso_teste = 1";

			System.out.println("Atualizando Dados de Teste em execução ...");
			int statusInsert = stmt.executeUpdate(sqlUpdate);
			
			if (statusInsert == 0) {
		
				System.out.println("Erro ao atualizar Execução de Teste");
				connection.rollback();
			
			}else {
				System.out.println("Execução de teste atualizados com sucesso!");
				connection.commit();
			}
			stmt.close();
			connection.close();
			System.out.println("Conexões com Banco Fechadas");

		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
	
	
	

	public void convertToJson() throws SQLException {

		String json = loadData();
		mapDadosTeste = new Gson().fromJson(json, Map.class);

	}

	public Object getData(String chave) {
		return mapDadosTeste.get(chave);
	}

}
