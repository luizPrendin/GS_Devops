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