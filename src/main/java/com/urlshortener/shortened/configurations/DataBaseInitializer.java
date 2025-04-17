package com.urlshortener.shortened.configurations;

import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component
public class DataBaseInitializer {
    String createTableSql = "CREATE TABLE IF NOT EXISTS public.tbl_url + (" +
            "id bigint NOT NULL GENERATED ALWAYS AS IDENTITY " +
            "( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ) " +
            "long_url text NOT NULL " +
            "short_url text NOT NULL " +
            "CONSTRAINT tbl_url_pkey PRIMARY KEY (id)) ";

    private final JdbcTemplate jdbcTemplate;
    public DataBaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostConstruct
    public void postgresInitializer(){
        if(databaseSchemaExists("tbl_url")){
            return;
        }
        LoggerFactory.getLogger(this.getClass()).info("Database Schema User Doesn't exist creating the schema");
        createDatabaseSchema();
    }
    private boolean databaseSchemaExists(String tablename) {
        try{
            String query = "SELECT to_regclass(?)";
            String result = jdbcTemplate.queryForObject(query,String.class, tablename);
            return result.isEmpty();
        }catch(DataAccessException e){
            LoggerFactory.getLogger(this.getClass()).warn("Couldn't determine if database tbl_url exists, skipping creation");
            return true;
        }
    }
    private void createDatabaseSchema() {
        jdbcTemplate.execute(createTableSql);
    }
}
