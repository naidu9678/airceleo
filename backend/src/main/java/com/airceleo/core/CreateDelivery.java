package com.airceleo.core;
import com.airceleo.core.DeliveryRequest;
import com.airceleo.core.DeliveryRepository;
import java.time.Instant;
import java.util.UUID;

public class CreateDelivery {
  private final DeliveryRepository repo;
  public CreateDelivery(DeliveryRepository repo){ this.repo=repo; }
  public DeliveryRequest execute(UUID senderId, String pickup, String drop, String itemType, double weightKg, boolean special){
    var d = new DeliveryRequest(UUID.randomUUID(), senderId, pickup, drop, itemType, weightKg, special, DeliveryRequest.Status.OPEN, Instant.now());
    return repo.save(d);
  }
}
