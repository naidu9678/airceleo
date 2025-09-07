package com.airceleo.core;
import java.time.Instant;
import java.util.UUID;

public class DeliveryRequest {
	
  public enum Status { OPEN, MATCHED, IN_PROGRESS, DELIVERED, CANCELLED }
  private final UUID id; private final UUID senderId;
  private final String pickupCity; private final String dropCity;
  private final String itemType; private final double weightKg;
  private final boolean specialHandling; private final Status status; private final Instant createdAt;
  public DeliveryRequest(UUID id, UUID senderId, String pickupCity, String dropCity, String itemType,
                         double weightKg, boolean specialHandling, Status status, Instant createdAt){
    this.id=id; this.senderId=senderId; this.pickupCity=pickupCity; this.dropCity=dropCity;
    this.itemType=itemType; this.weightKg=weightKg; this.specialHandling=specialHandling; this.status=status; this.createdAt=createdAt;
  }
  public UUID getId(){return id;} public UUID getSenderId(){return senderId;}
  public String getPickupCity(){return pickupCity;} public String getDropCity(){return dropCity;}
  public String getItemType(){return itemType;} public double getWeightKg(){return weightKg;}
  public boolean isSpecialHandling(){return specialHandling;} public Status getStatus(){return status;} public Instant getCreatedAt(){return createdAt;}
  public DeliveryRequest withStatus(Status s){ return new DeliveryRequest(id,senderId,pickupCity,dropCity,itemType,weightKg,specialHandling,s,createdAt); }

}
