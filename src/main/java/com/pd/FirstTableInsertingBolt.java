package com.pd;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class FirstTableInsertingBolt implements IRichBolt {
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;

	}

	@Override
	public void execute(Tuple input) {

		System.out.println("Putting in first table content" + input.getString(0));

		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			session.beginTransaction();

			FirstTable ft = new FirstTable();
			ft.setContent(input.getString(0));
			ft.setTs(new Timestamp(System.currentTimeMillis()));
			ft.setRecordId(input.getMessageId().toString());
			session.save(ft);
			session.getTransaction().commit();

			String sentence = input.getString(0);
			Values v = new Values();
			v.add(input.getString(0));
			v.add(input.getMessageId().toString());

			collector.emit(v);

			/*
			 * String[] words = sentence.split(" "); for (String word : words) {
			 * word = word.trim(); if (!word.isEmpty()) { word =
			 * word.toLowerCase(); collector.emit(new Values(word)); } }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		collector.ack(input);
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word","recordid"));
		

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
