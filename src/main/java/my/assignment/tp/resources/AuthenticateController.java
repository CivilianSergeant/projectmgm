package my.assignment.tp.resources;

import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import my.assignment.tp.reponse.ApiResponse;
import my.assignment.tp.reponse.AuthResponse;
import my.assignment.tp.request.LoginRequest;
import my.assignment.tp.service.JwtService;
import my.assignment.tp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest){
        AuthResponse apiResponse;
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            if(!userDetails.getPassword().equalsIgnoreCase(loginRequest.getPassword())){
                throw new Exception("Invalid Username or Password");
            }
            String token = jwtService.generateToken(userDetails);
            apiResponse = new AuthResponse(1,"Login Successful");
            apiResponse.setUsername(userDetails.getUsername());
            apiResponse.setToken(token);
        }catch (BadCredentialsException ex){
             apiResponse = new AuthResponse(0,"Invalid Username or Password");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            apiResponse = new AuthResponse(0,"Invalid Username or Password");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
