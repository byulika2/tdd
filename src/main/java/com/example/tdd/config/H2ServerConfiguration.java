package com.example.tdd.config;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class H2ServerConfiguration {

  /**
   * @return
   * @throws SQLException
   * @see org.h2.server.TcpServer
   */
  @Bean
  @ConfigurationProperties("spring.datasource.hikari")
  public DataSource dataSource() throws SQLException {
    Server server = defaultRun(9092);
    if (server.isRunning(true)) {
      log.info("server run success");
    }
    log.info("h2 server url = {}", server.getURL());

    return new HikariDataSource();
  }

  private Server defaultRun(int port) throws SQLException {
    return Server.createTcpServer(
        "-tcp",
        "-tcpAllowOthers",
        "-ifNotExists",
        "-tcpPort", port + "").start();
  }
}
