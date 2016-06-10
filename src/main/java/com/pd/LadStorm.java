package com.pd;

import javax.jms.Session;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class LadStorm {

	public static void main(String[] args) throws Exception {

		// JMS Topic provider
		JmsProvider jmsTopicProvider = new SpringJmsProvider("jms-activemq.xml", "jmsConnectionFactory",
				"notificationTopic");

		JmsTupleProducer producer = new JsonTupleProducer();

		JmsSpout topicSpout = new JmsSpout();
		topicSpout.setJmsProvider(jmsTopicProvider);
		topicSpout.setJmsTupleProducer(producer);
		topicSpout.setJmsAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
		topicSpout.setDistributed(true); // allow multiple instances

		Config config = new Config();
		// config.put("inputFile", args[0]);
		config.setDebug(false);
		config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		config.setNumWorkers(2);

		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("jms-reader-spout", topicSpout);
		builder.setBolt("first-table-inseting", new FirstTableInsertingBolt()).shuffleGrouping("jms-reader-spout");
		builder.setBolt("second-table-inserting", new SecondTableInsertingBolt())
				.shuffleGrouping("first-table-inseting");

		// StormSubmitter.submitTopology("ladStorm", config,
		// builder.createTopology());

		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("dudeStorm", config, builder.createTopology());
		// Thread.sleep(10000);

		// cluster.shutdown();
	}

}
