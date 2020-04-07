# ClientBuck
 A Customer Relationship Management (CRM) CRUD Application using Spring MVC and Hibernate.
 
 ## Overview
 A Customer and Client Relationship Application that helps in managing the customers efficiently. All the CRUD features are enabled that grants full access to the administrator to save a new customer, list the customers, update customer information and delete any customer. The admin can also list the customers based on any custom criteria. Role based access will be enabled for security using Spring Security features.
 
 ## Application Architecture
 ![image](https://user-images.githubusercontent.com/34190266/78684957-bfa53600-790e-11ea-88ba-cbaad271bc85.png)
 
## Different Components Involved

### Application Layer 
It usually consists of the web browser which sends the request to the front controller (Dispatcher Servlet) which loads the landing      page of the application. It also contains the view template. It's usually the part through which customer interacts with the app. 

### The Controller 
Most of the main business logic is found in the Controller which contains the appropriate mappings (GET/POST) for different requests.
It communicates with the service layer for most of the functionality.

### The Service Layer 
It follows the **Service Facade** design pattern. It is an intermediate layer between the custom controller and the DAO (Data Access Object) Layer. It comes in handy when we need to integrate data from multiple sources repositories.

### The DAO Layer 
It is a common design pattern i.e. **Data Access Object**. This layer is responsible for interfacing with the database and providing all the database related facilities.
<br>

## Starter Files for the Application : 

spring-mvc-crud-demo-servlet.xml :
```

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
    xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/mvc
		http://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/tx 
		http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!-- Add support for component scanning -->
	<context:component-scan base-package="com.saty.springdemo" />

	<!-- Add support for conversion, formatting and validation support -->
	<mvc:annotation-driven/>

	<!-- Define Spring MVC view resolver -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>

    <!-- Step 1: Define Database DataSource / connection pool -->
	<bean id="myDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
          destroy-method="close">
        <property name="driverClass" value= "oracle.jdbc.driver.OracleDriver" />
        <property name="jdbcUrl" value="jdbc:oracle:thin:@//localhost:1521/XE" />
        <property name="user" value="system" />
        <property name="password" value="admin" /> 

        <!-- these are connection pool properties for C3P0 -->
        <property name="minPoolSize" value="5" />
        <property name="maxPoolSize" value="20" />
        <property name="maxIdleTime" value="30000" />
	</bean>  
	
    <!-- Step 2: Setup Hibernate session factory -->
	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
		<property name="dataSource" ref="myDataSource" />
		<property name="packagesToScan" value="com.saty.springdemo.entity" />
		<property name="hibernateProperties">
		   <props>
		      <prop key="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</prop>
		      <prop key="hibernate.show_sql">true</prop>
		   </props>
		</property>
   </bean>	  

    <!-- Step 3: Setup Hibernate transaction manager -->
	<bean id="myTransactionManager"
            class="org.springframework.orm.hibernate5.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>
    
    <!-- Step 4: Enable configuration of transactional behavior based on annotations: css,images, js etc. -->
	<tx:annotation-driven transaction-manager="myTransactionManager" />
	
	<!--  Add support for reading web resources -->
	<mvc:resources location = "/resources/" mapping = "/resources/**"></mvc:resources>
	

</beans>
```

*(web.xml)* : 
```

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>spring-mvc-crud-demo</display-name>

  <absolute-ordering />

  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>index.html</welcome-file>
  </welcome-file-list>

  <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>/WEB-INF/spring-mvc-crud-demo-servlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  
  <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
</web-app>
```
