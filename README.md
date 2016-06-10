# LadStorm
This is Durable Apache storm implementation. One liner description of this program is
"Storm will consume data from topic, and then it will push the data to two database tables"

For every message in topic, one row in the table named "FirstTable" will be added. 
For every message in topic, two rows by splitting records will be added to table named "SecondTable"
I have kept every class in com.pd folder (Will modularize later) 

Prerequisites<br/>
1> Java 1.6 or higher<br/>
2> Eclipse (essential but not required )<br/>
3> Apache Maven 3.x or above<br/>
4> Apache Active MQ<br/>
5> Any database ( I have used Oracle in this example)<br/> 

<br/><br/>
Procedure to run below program<br/>

Download program from the git using command if you have git, or simply download zip.<br/> 
git pull https://github.com/prakashd1/LadStorm.git<br/>
<br/><br/>
Run Active MQ and check its admin console by accessing http://localhost:8161/admin
<br/><br/>
Check if you have exact port configured in the jms-activemq.xml. I have kept standard broker configuration i.e tcp://localhost:61616 <br/> <br/>
Check Database confuguration in the hibernate.cfg.xml.  <br/>
Here also I have kept standard configuration of XE schema with scott/tiger. <br/>
<br/>
Now go to base directory of project and Run
<br/>
mvn clean install exec:java
<br/>
Now we have JMS client listning on port 61616 and storm is consuming data from it. Go to activeMQ console and publish <br/> 
something to topic "testTopic1". JMS client will consume the topic and send it to the Storm for further processing. 
<br/>
Let me know if you run into any errors.. I will be glad to update it.. 
<br/>
Addition: I have added JMS publisher so that you dont have to access ActiveMQ admin console every time. 
<br/>
Its located in com.pd.utils.Publisher. 
<br/><br/><br/><br/>






