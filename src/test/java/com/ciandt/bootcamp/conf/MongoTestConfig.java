package com.ciandt.bootcamp.conf;

import com.mongodb.client.MongoClients;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@TestConfiguration
@Profile("test")
public class MongoTestConfig {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {

        String ip = "localhost";
        int port = 27017;

        return new SimpleMongoClientDatabaseFactory(MongoClients.create("mongodb://localhost:27017"), "breweryTest");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) throws Exception{
       return new MongoTemplate(mongoDatabaseFactory);
    }
}
