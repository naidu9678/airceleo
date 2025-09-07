package com.airceleo.core;
import java.util.UUID;

public class Match {
	
  public enum Status { PROPOSED, ACCEPTED, PAID, BOOKED, IN_TRANSIT, DELIVERED, CANCELLED }
  private final UUID id; private final UUID deliveryId; private final UUID routeId;
  private final double score; private final Status status;
  public Match(UUID id, UUID deliveryId, UUID routeId, double score, Status status){
    this.id=id; this.deliveryId=deliveryId; this.routeId=routeId; this.score=score; this.status=status;
  }
  public UUID getId(){return id;} public UUID getDeliveryId(){return deliveryId;} public UUID getRouteId(){return routeId;}
  public double getScore(){return score;} public Status getStatus(){return status;}
  public Match withStatus(Status s){ return new Match(id,deliveryId,routeId,score,s); }
}
