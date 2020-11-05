package com.boubyan.orderportal.OPAuthentication;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

//import org.redisson.Redisson;
//import org.redisson.api.RBucket;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.boubyan.orderportal.OPAuthentication.models.User;

@Component
public class UserDAOImp extends JdbcDaoSupport implements UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImp.class);
	
//	@Value("${redisson.file}")
//	String redissonConfigUri;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Bean(destroyMethod = "shutdown")
//	public RedissonClient redissonClient() throws IOException{
//		String configFileName = redissonConfigUri;
//		File resourceURL = ResourceUtils.getFile(configFileName);
//		Config config =Config.fromYAML(resourceURL);
//		return Redisson.create(config);
//	}
//	@PreDestroy
//	private void clean() {
//		Rclient.shutdown();
//	}

	@Autowired
	DataSource dataSource;

//	private RedissonClient Rclient;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
//		logger.info("config file is at " +redissonConfigUri);
//			logger.info("Post Construct : constructed");
//		try {
//			Rclient = redissonClient();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	

	}




	@Override
	public User findUserByEmailAndPassword(String email, String password) {

		// TODO Auto-generated method stub
		String sql = "SELECT *  FROM users WHERE email=? ";
//		logger.info("QUery to run: " + sql);
		List<User> usr = getJdbcTemplate().query(sql, new Object[] { email }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User usr = new User();
				usr.setEmail(rs.getString("email"));
				usr.setId(rs.getString("id"));
				usr.setName(rs.getString("Name"));
				usr.setPassword(rs.getString("password"));
				usr.setRole(rs.getString("role"));
				if(passwordEncoder.matches(password, usr.getPassword())) {
//					RBucket<User> rbucket;
//
//					rbucket = Rclient.getBucket(usr.getId());
//					rbucket.set(usr,1,TimeUnit.HOURS);
			
				
				return usr;
				}else {
					usr=null;
					return null;
				}
				
			}

		});
		return usr.isEmpty() ? null : usr.get(0);
	}

	@Override
	public int registerByEmailAndPassword(String id, String email, String name, String password, String role) {

		String sql = "INSERT INTO users (id, email, name, password,role) VALUES(?,?,?,?,?)";
		String encodedPassword = passwordEncoder.encode(password);
//		logger.info("QUery to run: " + sql);
		int rowsAffected = 0;
		rowsAffected = getJdbcTemplate().update(sql, new Object[] { id, email, name, encodedPassword,role });

		return rowsAffected;
	}

}
