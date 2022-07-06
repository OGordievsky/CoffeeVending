# CoffeeVending 
<br/>This is emulator of coffee fandomat based on JavaFX technology with connection to PostgreSQL database.
<br/>
<br/>When the application is launched, it opens a working window 800x600px.
<br/>
<br/>At the top, there is a list of products and a search bar on product name. 
<br/>When filling in the search bar - the list of products dynamically filtering. 
<br/>The list of goods is loaded from the PostgreSql database. 
<br/>When you click on the product line or click on the "AddProduct" button - one unit is added to the cart, while the same products are summed up.
<br/>
<br/>The shopping cart displays a list of added products, quantity and their total price. 
<br/>Any item in the basket can be deleted by clicking the button "Delete". 
<br/>Any manipulation with the items in the shopping cart results in to recalculate the total.
<br/>When you click on the "Pay" button, a window for entering the amount of payment for bank. 
<br/>If the entered payment amount matches the final the amount of goods in the basket, 
<br/>then the full receipt with goods is recorded in the databases and window return to the main working space with resetting the basket.
<br/>
<br/>Stack:
<br/>Java 8, JavaFX 8, PostgreSQL 9, Gradle 5.
<br/>fxml pages build in Scene Builder
<br/>
<br/>Before start this Application:
<br/>Ensure that your PC have <a href="https://www.java.com/en/download/help/index_installing.html">installed Java 8</a>
<br/><a href="https://www.postgresql.org/docs/current/tutorial-install.html">Create PostgreSQL</a> database name: "cashtest" with user: "coffetest" password "coffetest" 
<br/>(You can change this credentials in project file "db.properties")
<br/>
<br/>How to start it?
<br/>
<br>Simple way to start from OS
<br/>
<br/>1. Click  <a href="https://github.com/OGordievsky/CoffeeVending/raw/master/Application.rar">"Download"</a> after that the start upload archive from:
<br/>https://github.com/OGordievsky/CoffeeVending/blob/master/Application.rar
<br/>2. Unpack Application.rar into your directory.
<br/>3. Double-click on Start.bat
<br/>3.1 Or into project directory type next console command: <b>java -jar coffevending.jar</b> 
<br/>
<br/>From you favorite IDE's:
<br/>
<br/>1. Open you favorite IDE
<br/>2. Walk into yours "projects" directory
<br/>3. Ensure that your PC have <a href="https://git-scm.com/book/en/v2/Getting-Started-Installing-Git">Git</a>
<br/>3. In project directory type next console command: <b>git clone https://github.com/OGordievsky/CoffeeVending</b>
<br/>3. In yours IDE make next steps: File -> Open -> \projects\CoffeeVending -> Open as Java8-project
<br/>4. Find main class "Application" in: \CoffeeVending\src\coffevending\Application.java
<br/>5. Right click on Application.java select [Run] (green "Play" triangle)
<br/>
<br/>Enjoy to use
