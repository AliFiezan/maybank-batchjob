package com.alifiezan.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alifiezan.dao.TransactionDao;
import com.alifiezan.model.Transaction;
import com.alifiezan.step.Listener;
import com.alifiezan.step.Processor;
import com.alifiezan.step.Reader;
import com.alifiezan.step.Writer;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public TransactionDao transactionDao;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).listener(new Listener(transactionDao))
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Transaction, Transaction>chunk(2)
				.reader(Reader.reader("dataSource.txt", "|"))
				.processor(new Processor()).writer(new Writer(transactionDao)).build();
	}
}
