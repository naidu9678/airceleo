package com.airceleo.adapters.web;

import com.airceleo.core.DeliveryRequest;
import com.airceleo.core.CreateDelivery;
import com.airceleo.core.ComputeMatches;
import com.airceleo.core.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

record CreateDeliveryRequest(java.util.UUID senderId, String pickupCity, String dropCity, String itemType, double weightKg, boolean specialHandling){}
@RestController @RequestMapping("/api/deliveries")
public class DeliveryController {
  private final CreateDelivery createDelivery; private final ComputeMatches computeMatches; private final MatchRepository matchRepo;
  public DeliveryController(CreateDelivery cd, ComputeMatches cm, MatchRepository mr){ this.createDelivery=cd; this.computeMatches=cm; this.matchRepo=mr; }
  @PostMapping public DeliveryRequest create(@RequestBody CreateDeliveryRequest r){
    return createDelivery.execute(r.senderId(), r.pickupCity(), r.dropCity(), r.itemType(), r.weightKg(), r.specialHandling());
  }
  @GetMapping("/{id}/matches")
  public List<com.airceleo.core.match.Match> matches(@PathVariable UUID id){ return computeMatches.execute(id); }
  @GetMapping("/{id}") public ResponseEntity<DeliveryRequest> get(@PathVariable UUID id){
    return ResponseEntity.of(Optional.ofNullable(null));
  }
}
