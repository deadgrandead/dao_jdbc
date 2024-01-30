package com.deadgrandead.dao_jdbc.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String fetchProductQuery;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.fetchProductQuery = read();
    }

    public List<String> getProductName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        return jdbcTemplate.queryForList(fetchProductQuery, params, String.class);
    }

    private static String read() {
        try (InputStream is = new ClassPathResource("fetch_product.sql").getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

