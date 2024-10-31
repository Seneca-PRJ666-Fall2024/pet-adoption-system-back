FROM openjdk:23-jdk

WORKDIR /app

COPY build/libs/pet-adoption-system-0.0.1-SNAPSHOT.jar ./PetAdoptionBackEnd.jar

EXPOSE 8080

CMD ["java", "-jar", "PetAdoptionBackEnd.jar"]
