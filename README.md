# GS_Devops

# Video do Youtube:
-- https://www.youtube.com/watch?v=ug1Ljq5oHAY

# Documentação

- Dockerfile:
```Dockerfile
# Usar a imagem base do JDK 17
FROM openjdk:17-jdk-slim AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos de construção do Gradle
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src/
COPY gradlew ./
COPY gradlew.bat ./

# Dar permissão de execução ao script gradlew
RUN chmod +x gradlew

#tive que adicionar essa duas linhas pois o meu pc não estava reconhecendo gradle
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix gradlew

# Executar o comando de construção do Gradle
RUN ./gradlew build --no-daemon

# Usar uma imagem mais leve para a aplicação final
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado da fase de construção
COPY --from=build /app/build/libs/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

```
- Comandos cmd:
```Bash
## Variaveis do Script
rg=rg-GSDevops &&
location=brazilsouth &&
appServicePlanName=plan-GSDevops &&
webAppName=JJSolution &&
sku=B2 &&
## Cria um novo Grupo de Recursos
az group create --name $rg --location $location &&
## Cria um novo Plano de Serviço para o App
az appservice plan create -n $appServicePlanName --location $location -g $rg --sku $sku &&
## Cria um novo Serviço de Aplicativo
## Cria a Infra em Windows
az webapp create -g $rg -p $appServicePlanName --runtime "JAVA:17" -n $webAppName
```

- Script sql(Oracle):
```sql
-- Criação da tabela Aparelho
CREATE TABLE Aparelho (
    idAparelho NUMBER(19, 0) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
    nome VARCHAR2(255) NOT NULL, 
    potencia NUMBER(19, 0) NOT NULL, 
    tipo VARCHAR2(255) NOT NULL
);
 
-- Criação da tabela Consumo
CREATE TABLE Consumo (
    idConsumo NUMBER(19, 0) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
    aparelho_id NUMBER(19, 0) NOT NULL, 
    data VARCHAR2(255) NOT NULL, 
    consumo_kwh NUMBER(19, 2) NOT NULL, 
    custo_estimado NUMBER(19, 2) NOT NULL, 
    CONSTRAINT FK_CONSUMO_APARELHO FOREIGN KEY (aparelho_id) 
        REFERENCES Aparelho(idAparelho)
        ON DELETE CASCADE
);
 
-- Criação da tabela PrecoEnergia
CREATE TABLE PrecoEnergia (
    idPrecoEnergia NUMBER(19, 0) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
    data VARCHAR2(255) NOT NULL, 
    preco_kwh NUMBER(19, 2) NOT NULL
);
 
-- Índices
CREATE INDEX IDX_CONSUMO_APARELHO ON Consumo(aparelho_id);
CREATE INDEX IDX_PRECOENERGIA_DATA ON PrecoEnergia(data);
```
Esta API permite gerenciar o consumo de energia de aparelhos, fornecendo operações de CRUD (Criar, Ler, Atualizar e Excluir) para as entidades relacionadas, como Aparelhos, Consumo, e Preço de Energia. O sistema é projetado para acompanhar o consumo energético e estimar os custos, promovendo eficiência no uso de energia.

## Endpoints

### Aparelhos

- **POST /aparelhos: Cria um novo aparelho.
- **GET /aparelhos: Retorna todos os aparelhos cadastrados.
- **GET /aparelhos/{id}: Retorna um aparelho específico pelo id.
- **PUT /aparelhos/{id}: Atualiza um aparelho existente.
- **DELETE /aparelhos/{id}: Exclui um aparelho.

### Consumos

- **POST /consumos: Registra o consumo de um aparelho.
- **GET /consumos: Retorna todos os consumos cadastrados.
- **GET /consumos/{id}: Retorna um consumo específico pelo id.
- **PUT /consumos/{id}: Atualiza os dados de um consumo existente.
- **DELETE /consumos/{id}: Exclui um consumo.

### Precos Energia

- **POST /precosenergia: Define o preço de energia para uma data específica.
- **GET /precosenergia: Retorna os preços cadastrados.
- **GET /precosenergia/{id}: Retorna o preço de energia de uma data específica pelo id.
- **PUT /precosenergia/{id}: Atualiza o preço de energia.
- **DELETE /precosenergia/{id}: Exclui o registro do preço.
---

## Exemplo de Requisições e Respostas

### Aparelhos

#### Criar Aparelho (POST `/aparelhos`)

```Json
{
  "nome": "Geladeira",
  "potencia": 150,
  "tipo": "Eletrodoméstico"
}
```
#### Atualizar (Put `/aparelhos/{id}`)
```Json
{
  "nome": "Geladeira Frost Free",
  "potencia": 200,
  "tipo": "Eletrodoméstico"
}
```
### Anuncios

#### Criar Consumo (POST `/consumo`)
```Json
{
  "aparelho": {
    "nome": "Geladeira",
  "potencia": 150,
  "tipo": "Eletrodoméstico"
  },
  "data": "2024-11-20",
  "consumoKwh": 15.5,
  "custoEstimado": 7.75
}
```
#### Atualizar (Put `/consumos/{id}`)
```Json
{
  "aparelho": {
    "nome": "Geladeira",
  "potencia": 150,
  "tipo": "Eletrodoméstico"
  },
  "data": "2024-12-20",
  "consumoKwh": 15.5,
  "custoEstimado": 7.75
}
```
### Criar Preco Energia (Post `/precoenergia`)
```Json
{
  "data": "2024-11-22",
  "precoKwh": 0.5
}
```
### Atualizar (Put `/precoenergia/{id}`)
```Json
{
  "data": "2024-11-22",
  "precoKwh": 1.5
}
```

# Integrantes
- Luiz Felipe RM552475
- Rennan Ferreira RM99364
- Jaisy Cibele RM552269
- Tomaz Pecoraro RM98499
- Gabriel Amâncio RM97936
