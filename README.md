# LadStorm
This is Durable Apache storm implementation. One liner description of this program is
<b>
"Storm will consume data from topic, and then it will push the data to two database tables"
</b>
For every message in topic, one row in the table named "FirstTable" will be added. 
For every message in topic, two rows by splitting records will be added to table named "SecondTable"
I have kept every class in com.pd folder (Will modularize later) 

<b>
Prerequisites<br/>
1> Java 1.6 or higher<br/>
2> Eclipse (essential but not required )<br/>
3> Apache Maven 3.x or above<br/>
4> Apache Active MQ<br/>
5> Any database ( I have used Oracle in this example)<br/> 
</b>
<br/><br/>
Procedure to run below program<br/>

Download program from the git using command if you have git, or simply download zip.<br/> 
git pull https://github.com/prakashd1/LadStorm.git<br/>
<br/><br/>
Run Active MQ and check its admin console by accessing 
<b> http://localhost:8161/admin </b>
<br/><br/>
Check if you have exact port configured in the jms-activemq.xml. <br/>
I have kept standard broker configuration i.e <b> tcp://localhost:61616 </b><br/> <br/>
Check Database confuguration in the hibernate.cfg.xml.  <br/>
Here also I have kept standard configuration of XE schema with scott/tiger. <br/>
<br/>
Now go to base directory of project and Run
<br/><b>
mvn clean install exec:java
</b><br/>
We have JMS client listning on port 61616 and storm is consuming data from it.<br/>
Go to activeMQ console and publish something to topic "testTopic1".<br/> 
JMS client will consume the topic and send it to the Storm for further processing.<br/> 
<br/>
Let me know if you run into any errors.. I will be glad to update them.. <br/>
<br/>
Addition: I have added JMS publisher so that you dont have to access ActiveMQ admin console every time.<br/><br/> 
<br/>
Its located in <b>com.pd.utils.Publisher. </b> 
<br/><br/><br/><br/>






