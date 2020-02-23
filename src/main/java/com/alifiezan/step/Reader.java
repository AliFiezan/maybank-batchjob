package com.alifiezan.step;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.alifiezan.model.Transaction;


public class Reader {
	public static FlatFileItemReader<Transaction> reader(String path, String delimiter) {
		FlatFileItemReader<Transaction> reader = new FlatFileItemReader<Transaction>();

		reader.setLinesToSkip(1); // skip first line (header)
		reader.setResource(new ClassPathResource(path));
		reader.setLineMapper(new DefaultLineMapper<Transaction>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "acc_no", "trx_amount", "description" , "trx_date" , "trx_time", "cust_id" });
						setDelimiter(delimiter);
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>() {
					{
						setTargetType(Transaction.class);
					}
				});
			}
		});
		return reader;
	}
}
