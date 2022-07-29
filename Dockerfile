FROM java:8
EXPOSE 6060
ADD target/employee-management-data.jar employee-management-data.jar 
ENTRYPOINT ["java","-jar","employee-management-data.jar"]