-- Criação Tabela dados_teste
CREATE TABLE dados_teste 
(id_caso_teste number(5) primary key  NOT NULL,
massa_teste varchar2(1000) NOT NULL,
caso_teste varchar2(50) NOT NULL);
-- Criação Tabela execucao_teste
CREATE TABLE execucao_testes 
(id_caso_teste number(5) NOT NULL,
status varchar2(100) NOT NULL,
data_hora_execucao DATE NOT NULL);

-- Alteração Formato de Data
alter session set nls_date_format = 'dd/MM/yyyy hh24:mi:ss';

-- Relacionando tabela de massas com tabela de execução
ALTER TABLE execucao_testes ADD CONSTRAINT dados_teste_fk FOREIGN KEY (id_caso_teste) REFERENCES dados_teste(id_caso_teste);

--Inserindo registros na dados_teste
INSERT INTO dados_teste (id_caso_teste, massa_teste,caso_teste)
VALUES (1, '{"url":"https://amazon.com.br","email":"pemiya7700@whowlft.com","senha":"testeautomacao", "produto1":"Iphone Xs 64Gb Prata","DescricaoProduto1":"Iphone Xs 64Gb Prata (Prata)","produto2":"galaxy s20 ultra","DescricaoProduto2":"Celular Samsung Galaxy S20+"}','Teste_Amazon');

--Inserindo registros na tabela execucao_testes
INSERT INTO execucao_testes (id_caso_teste, status,data_hora_execucao)
VALUES (1, 'Em execução','23/10/2020 19:04:44');


COMMIT;


--Selecionando tudo da tabela dados_teste
SELECT *
FROM dados_teste
WHERE id_caso_teste=1;

--Selecionando tudo da tabela execucao_testes
SELECT * 
FROM EXECUCAO_TESTES et;

COMMIT;

ALTER TABLE dados_teste add (descricao_caso_teste varchar2(200));
ALTER TABLE dados_teste drop column (descricao_caso_teste);
