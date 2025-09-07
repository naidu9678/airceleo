package com.airceleo.core;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name="users")
public class UserEntity {
  @Id private UUID id;
  @Column(unique=true) private String email;
  @Column(unique=true) private String phone;
  private boolean termsAccepted;
  private String kycStatus;
  private double ratingAvg;
  private int ratingCount;
  private Instant createdAt;
  // getters/setters
}

@Entity @Table(name="kyc_inquiries")
class KycEntity {
  @Id private UUID id;
  private UUID userId;
  private String providerId;
  private String hostedLink;
  private String status;
  private Instant createdAt;
  // getters/setters
}

@Entity @Table(name="routes")
class RouteEntity {
  @Id private UUID id;
  private UUID travelerId;
  private String departAirport; private String arriveAirport;
  private Instant departTime; private Instant arriveTime;
  private String baggageType; private double maxWeightKg;
  // getters/setters
}

@Entity @Table(name="deliveries")
class DeliveryEntity {
  @Id private UUID id;
  private UUID senderId;
  private String pickupCity; private String dropCity;
  private String itemType; private double weightKg;
  private boolean specialHandling; private String status;
  private Instant createdAt;
  // getters/setters
}

@Entity @Table(name="matches")
class MatchEntity {
  @Id private UUID id;
  private UUID deliveryId; private UUID routeId;
  private double score; private String status;
  // getters/setters
}
