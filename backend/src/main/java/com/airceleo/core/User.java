package com.airceleo.core;
import java.time.Instant;
import java.util.UUID;

public class User {
  private final UUID id;
  private final String email;
  private final String phone;
  private final boolean termsAccepted;
  private final KycStatus kycStatus;
  private final double ratingAvg;
  private final int ratingCount;
  private final Instant createdAt;
  public enum KycStatus { PENDING, APPROVED, REJECTED, NOT_STARTED }
  public User(UUID id, String email, String phone, boolean termsAccepted,
              KycStatus kycStatus, double ratingAvg, int ratingCount, Instant createdAt){
    this.id=id; this.email=email; this.phone=phone; this.termsAccepted=termsAccepted;
    this.kycStatus=kycStatus; this.ratingAvg=ratingAvg; this.ratingCount=ratingCount; this.createdAt=createdAt;
  }
  public UUID getId(){return id;} public String getEmail(){return email;} public String getPhone(){return phone;}
  public boolean isTermsAccepted(){return termsAccepted;} public KycStatus getKycStatus(){return kycStatus;}
  public double getRatingAvg(){return ratingAvg;} public int getRatingCount(){return ratingCount;}
  public Instant getCreatedAt(){return createdAt;}
  public User withKyc(KycStatus s){ return new User(id,email,phone,termsAccepted,s,ratingAvg,ratingCount,createdAt);}
}
