package com.airceleo.adapters.web;
import com.airceleo.Users;
import com.airceleo.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.UUID;

record RegisterRequest(@NotBlank String email, @NotBlank String phone, boolean terms) {}
record RegisterResponse(UUID userId, String token) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final UserRepository users;
  public AuthController(UserRepository users){ this.users=users; }
  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest req){
    var existing = users.findByEmail(req.email());
    if (existing.isPresent()) return ResponseEntity.ok(new RegisterResponse(existing.get().getId(), "demo-jwt-"+existing.get().getId()));
    var u = new User(UUID.randomUUID(), req.email(), req.phone(), req.terms(), User.KycStatus.NOT_STARTED, 0.0, 0, Instant.now());
    users.save(u);
    return ResponseEntity.ok(new RegisterResponse(u.getId(), "demo-jwt-"+u.getId()));
  }
}
