package com.airceleo.core;
import java.util.UUID;

public interface PersonaPort { record InquiryLink(String hostedLink, String providerId){} InquiryLink createInquiry(UUID userId, String email, String phone); void handleWebhook(String signature, String payload); }
public interface PaymentPort { record Intent(String id, String status){} Intent createEscrow(java.util.UUID matchId, long amountCents, String currency); void handleWebhook(String signature, String payload); }
public interface NotificationPort { void pushToUser(java.util.UUID userId, String title, String body); }
