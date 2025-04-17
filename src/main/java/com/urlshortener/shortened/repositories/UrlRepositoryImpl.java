package com.urlshortener.shortened.repositories;

import com.urlshortener.shortened.dataclasses.UrlShort;
import com.urlshortener.shortened.utils.Base62EncoderDecoder;
import com.urlshortener.shortened.utils.UrlShortenerHash;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class UrlRepositoryImpl implements UrlRepositoryI{
    private final JdbcTemplate jdbcTemplate;
    private final UrlShortenerHash urlShortenerHash;
    private String createShortUrl_sql = "insert into tbl_url(long_url, short_url) values (?,?)";
    private String getShortUrlInfo_sql = "select * from tbl_url where short_url = ?";
    public UrlRepositoryImpl(JdbcTemplate jdbcTemplate, UrlShortenerHash urlShortenerHash) {
        this.jdbcTemplate = jdbcTemplate;
        this.urlShortenerHash = urlShortenerHash;
    }

    @Override
    public UrlShort saveUrl(String longUrl) {
        String randomShortUrl = urlShortenerHash.encode(System.currentTimeMillis());
        LoggerFactory.getLogger(this.getClass()).info("This was the hashed url generated ".concat(randomShortUrl));
        jdbcTemplate.update(createShortUrl_sql,longUrl,randomShortUrl);
        return new UrlShort(longUrl,randomShortUrl);
    }

    @Override
    public UrlShort getShortUrlInfo(String shortUrl) {
        return jdbcTemplate.queryForObject(getShortUrlInfo_sql,(rs,row) -> {
            return new UrlShort(
                    rs.getString("long_url"),
                    rs.getString("short_url"));
        },shortUrl);
    }
}
