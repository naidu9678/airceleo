package com.airceleo.core;

import com.airceleo.core.KycInquiry;
import com.airceleo.core.KycInquiryRepository;
import com.airceleo.core.PersonaPort;
import com.airceleo.core.User;
import java.time.Instant;
import java.util.UUID;
public class InitiateKyc {
  private final PersonaPort persona; private final KycInquiryRepository kycRepo;
  public InitiateKyc(PersonaPort persona, KycInquiryRepository kycRepo){ this.persona=persona; this.kycRepo=kycRepo; }
  public KycInquiry execute(User user){
    var link = persona.createInquiry(user.getId(), user.getEmail(), user.getPhone());
    var inquiry = new KycInquiry(UUID.randomUUID(), user.getId(), link.providerId(), link.hostedLink(), KycInquiry.Status.CREATED, Instant.now());
    return kycRepo.save(inquiry);
  }
}
