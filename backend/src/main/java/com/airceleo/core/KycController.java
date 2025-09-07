package com.airceleo.adapters.web;
import com.airceleo.core.usecases.InitiateKyc;
import com.airceleo.core.ports.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

record KycStartRequest(@NotNull UUID userId) {}
record KycStartResponse(String hostedLink) {}

@RestController
@RequestMapping("/api/kyc")
public class KycController {
  private final InitiateKyc initiateKyc; private final UserRepository users;
  public KycController(InitiateKyc k, UserRepository u){ this.initiateKyc=k; this.users=u; }
  @PostMapping("/inquiries")
  public ResponseEntity<KycStartResponse> start(@RequestBody KycStartRequest req){
    var user = users.findById(req.userId()).orElseThrow();
    var inquiry = initiateKyc.execute(user);
    return ResponseEntity.ok(new KycStartResponse(inquiry.getHostedLink()));
  }
}
