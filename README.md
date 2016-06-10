# LadStorm
This is Durable Apache storm implementation. One liner description of this program is
"Storm will consume data from topic, and then it will push the data to two database tables"

For every message in topic, one row in the table named "FirstTable" will be added. 
For every message in topic, two rows by splitting records will be added to table named "SecondTable"
I have kept every class in com.pd folder (Will modularize later) 

Prerequisite
1> Java 1.6 or higher
2> Eclipse (essential but not required )
3> Apache Maven 3.x or above
4> Apache Active MQ
5> Any database ( I have used Oracle in this example) 


Procedure to run below program

Download program from the git using command if you have git, or simply download zip. 
git pull https://github.com/prakashd1/LadStorm.git

Run Active MQ and check its admin console by accessing http://localhost:8161/admin

Check if you have exact port configured in the jms-activemq.xml. I have kept standard broker configuration i.e tcp://localhost:61616
Check Database confuguration in the hibernate.cfg.xml. 
Here also I have kept standard configuration of XE schema with scott/tiger.

Now go to base directory of project and Run

mvn clean install exec:java

Now we have JMS client listning on port 61616 and storm is consuming data from it. Go to activeMQ console and publish 
something to topic "testTopic1". JMS client will consume the topic and send it to the Storm for further processing. 

Let me know if you run into any errors.. I will be glad to update it.. 

Addition: I have added JMS publisher so that you dont have to access ActiveMQ admin console every time. 
Its located in com.pd.utils.Publisher. 







