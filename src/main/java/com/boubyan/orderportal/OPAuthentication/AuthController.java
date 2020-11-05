package com.boubyan.orderportal.OPAuthentication;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boubyan.orderportal.OPAuthentication.models.NotFound;
import com.boubyan.orderportal.OPAuthentication.models.User;
import com.boubyan.orderportal.OPAuthentication.models.UserLogin;
import com.boubyan.orderportal.OPAuthentication.models.UserRegistration;
import com.boubyan.orderportal.OPAuthentication.utils.JwtTokenUtil;
import com.boubyan.orderportal.OPAuthentication.utils.messaging.MessageModel;
import com.boubyan.orderportal.OPAuthentication.utils.messaging.RabitMqConfig;
import com.boubyan.orderportal.OPAuthentication.utils.messaging.RabitMqHelper;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserDAOImp repo;
	

;
	@Autowired
	private RabitMqHelper rmhelper;
	// https://www.javainuse.com/spring/boot-jwt
	@GetMapping("/")
	public ResponseEntity<String> test() {
		
		rmhelper.sendMessage("/", "testing done succesffuly");
		
		return ResponseEntity.ok("testing");
	}

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLogin userLogin) {
		User user = null;

		user = repo.findUserByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());

		if (user != null) {
//			logger.info("User is notNull: " + user.getEmail());
			user.setJwt(jwtTokenUtil.createJWT(user.getId(), user.getName(), user.getEmail(),user.getRole()));
			HashMap<String, String> response = new HashMap<String, String>();
			rmhelper.sendMessage("Login", "Loged_in_ok_id: "+ user.getId());
			
			response.put("token", user.getJwt());
			response.put("exp", jwtTokenUtil.timeToExpire(user.getJwt())+"");
			return ResponseEntity.ok(response);
		} else {
//			logger.info("User is null: " + userLogin.getEmail());

			NotFound notFound = new NotFound();
			notFound.setMsg("the user with email: " + userLogin.getEmail() + " was not found");
//			logger.info(notFound.getMsg());
			rmhelper.sendMessage("Login", "Loged_in_failed: "+ userLogin.getEmail()+" not found");

			return ResponseEntity.status(404).body(notFound);
		}

	}

	@PostMapping(path ="/register",consumes = "application/json",produces ="application/json")
	public ResponseEntity<?> login(@RequestBody UserRegistration userRegisteration){
		if(userRegisteration.getPassword().equals(userRegisteration.getRepeatPassword())) {
			int rowsAffected=0;
			UUID id = UUID.randomUUID();
			try {
				
				rowsAffected = repo.registerByEmailAndPassword(id.toString(),userRegisteration.getEmail(), userRegisteration.getName(), userRegisteration.getPassword(),userRegisteration.getRole());
			} catch (Exception e) {
//				logger.info("error Caught");
				if (e.toString().contains("email_UNIQUE")) {
					NotFound notFound = new NotFound();
					notFound.setMsg("EMAIL_EXISTS");
					rmhelper.sendMessage("Register", "Errmsg: EMAIL_EXISTS");

					return ResponseEntity.status(400).body(notFound);
				}
//				e.printStackTrace();
			}
			if (rowsAffected==0) {
				NotFound notFound = new NotFound();
				notFound.setMsg("COULDNT_REGISTER");
				rmhelper.sendMessage("Register", "Errmsg: COULDNT_REGISTER");

				return ResponseEntity.status(400).body(notFound);
			}else {

				String jwt = jwtTokenUtil.createJWT(id.toString(), userRegisteration.getName(), userRegisteration.getEmail(),userRegisteration.getRole());
				HashMap<String, String> response = new HashMap<String, String>();
				response.put("token", jwt);
				response.put("msg","REGISTERED_SUCCSUFLY" );
				response.put("exp", jwtTokenUtil.timeToExpire(jwt)+"");
				rmhelper.sendMessage("Register", "ok: "+id);

				return ResponseEntity.ok(response);
			}
		}else {
			NotFound notFound = new NotFound();
			notFound.setMsg("PASSWORDS_DONT_MATCH");
			return ResponseEntity.status(400).body(notFound);
		}
	}
	
	@GetMapping(path = "/ver/token/{token}")
	public ResponseEntity<?> ver(@PathVariable String token){
		try {
		String parsed = (String) jwtTokenUtil.parseJwt(token).getBody().get("id");
		return ResponseEntity.status(200).body(parsed);

		}catch (ExpiredJwtException e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body("SESSION_EXPIRED");

		}catch(Exception e) {
			return ResponseEntity.status(400).body("INVALID_TOKEN");
		}
	}
}
