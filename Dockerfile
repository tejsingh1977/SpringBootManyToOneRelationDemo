FROM openjdk:11
COPY ./target/manytonerelation.jar manytonerelation.jar
CMD ["java","-jar","manytonerelation.jar"]