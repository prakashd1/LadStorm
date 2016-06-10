package com.pd;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class SecondTableInsertingBolt implements IRichBolt {

	Integer id;
	String name;
	Map<String, Integer> counters;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.counters = new HashMap<String, Integer>();
		this.collector = collector;
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();

	}

	@Override
	public void execute(Tuple input) {

		System.out.println("Putting in first table content" + input.getString(0));

		Session session = null;
		
		for(String f:input.getFields()){
			
			
		}
		String inputString=input.getValue(0).toString();
		String recordid=input.getValue(1).toString();

		int half = inputString.length() / 2;
		String firstHalf = inputString.substring(0, half);
		String secondHalf = inputString.substring(half, half * 2);
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			session.beginTransaction();

			
			
			SecondTable st1 = new SecondTable();
			st1.setContent(firstHalf);
			st1.setTs(new Timestamp(System.currentTimeMillis()));
			st1.setRecordId(recordid);

			SecondTable st2 = new SecondTable();
			st2.setContent(secondHalf);
			st2.setTs(new Timestamp(System.currentTimeMillis()));
			st2.setRecordId(recordid);

			session.save(st1);
			session.save(st2);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			collector.reportError(e);
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
		System.out.println("***************************************************************************");
		System.out.println(" -- Word Counter [" + name + "-" + id + "]");
		for (Map.Entry<String, Integer> entry : counters.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("***************************************************************************");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
