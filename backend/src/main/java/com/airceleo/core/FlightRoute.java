package com.airceleo.core;
import java.time.Instant;
import java.util.UUID;


public class FlightRoute {
  public enum BaggageType { HAND, CHECKED }
  private final UUID id; private final UUID travelerId;
  private final String departAirport; private final String arriveAirport;
  private final Instant departTime; private final Instant arriveTime;
  private final BaggageType baggageType; private final double maxWeightKg;
  public FlightRoute(UUID id, UUID travelerId, String departAirport, String arriveAirport,
                     Instant departTime, Instant arriveTime, BaggageType baggageType, double maxWeightKg){
    this.id=id; this.travelerId=travelerId; this.departAirport=departAirport; this.arriveAirport=arriveAirport;
    this.departTime=departTime; this.arriveTime=arriveTime; this.baggageType=baggageType; this.maxWeightKg=maxWeightKg;
  }
  public UUID getId(){return id;} public UUID getTravelerId(){return travelerId;}
  public String getDepartAirport(){return departAirport;} public String getArriveAirport(){return arriveAirport;}
  public java.time.Instant getDepartTime(){return departTime;} public java.time.Instant getArriveTime(){return arriveTime;}
  public BaggageType getBaggageType(){return baggageType;} public double getMaxWeightKg(){return maxWeightKg;}
}
