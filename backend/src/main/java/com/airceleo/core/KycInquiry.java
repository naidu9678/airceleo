package com.airceleo.core;

import java.time.Instant;
import java.util.UUID;

public class KycInquiry {
  public enum Status { CREATED, COMPLETED, FAILED, REVIEW }
  private final UUID id;
  private final UUID userId;
  private final String providerId;
  private final String hostedLink;
  private final Status status;
  private final Instant createdAt;
  public KycInquiry(UUID id, UUID userId, String providerId, String hostedLink, Status status, Instant createdAt){
    this.id=id; this.userId=userId; this.providerId=providerId; this.hostedLink=hostedLink; this.status=status; this.createdAt=createdAt;
  }
  public UUID getId(){return id;} public UUID getUserId(){return userId;} public String getProviderId(){return providerId;}
  public String getHostedLink(){return hostedLink;} public Status getStatus(){return status;} public Instant getCreatedAt(){return createdAt;}
  public KycInquiry withStatus(Status s){ return new KycInquiry(id,userId,providerId,hostedLink,s,createdAt); }
}
