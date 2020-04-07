# ClientBuck
 A Customer Relationship Management (CRM) CRUD Application using Spring MVC and Hibernate.
 
 ## Overview
 A Customer and Client Relationship Application that helps in managing the customers efficiently. All the CRUD features are enabled that grants full access to the administrator to save a new customer, list the customers, update customer information and delete any customer. The admin can also list the customers based on any custom criteria. Role based access will be enabled for security using Spring Security features.
 
 ## Application Architecture
 ![image](https://user-images.githubusercontent.com/34190266/78684957-bfa53600-790e-11ea-88ba-cbaad271bc85.png)
 
## Different Components Involved
<br><br>
***Application Layer*** :
It usually consists of the web browser which sends the request to the front controller (Dispatcher Servlet) which loads the landing      page of the application. 
<br><br>
***The Contoller*** :
Most of the main business logic is found in the Controller which contains the appropriate mappings (GET/POST) for different requests.
It communicates with the service layer for most of the functionality.
<br><br>
***The Service Layer*** :
It follows the **Service Facade** design pattern. It is an intermediate layer between the custom controller and the DAO (Data Access Object) Layer. It comes in handy when we need to integrate data from multiple sources repositories.
   
