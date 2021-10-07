# Names App - Conducktor ConfigMaps and Secrets Exercise

This Spring Boot application currently makes use of an h2 in memory database. For the purpose of this exercise, you will need to update the `application-dev.properties` file accordingly so that the application connects to a MySQL database hosted on AWS. To learn about how to properly configure your Spring Boot app to connect to MySQL, read the section titled "Create the application.properties File" in the [following documentation](https://spring.io/guides/gs/accessing-data-mysql/).

Furthermore, make the configuration of the database dynamic, such that the values for database connection are retrieved from environment variables at runtime. Doing so will enable the Conducktor platform to set the values by through secrets and ConfigMaps when the application is deployed.

Don't forget to create an appropriately configured `dev_deploy.yml.j2` file for your new Conducktor deployment of this application.

Load the database schema into your database instance, by connecting to your database instance with the above credentials, using the `mysql` CLI, and running the `APP_names_data.sql` script, made available to you in the project repository. To get acquainted with the `mysql` CLI and its use, read [the following docs](https://dev.mysql.com/doc/refman/5.7/en/mysql.html).