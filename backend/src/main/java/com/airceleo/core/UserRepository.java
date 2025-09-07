package com.airceleo.core;

import com.airceleo.core.Users;
import com.airceleo.core.KycInquiry;
import com.airceleo.core.FlightRoute;
import com.airceleo.core.DeliveryRequest;
import com.airceleo.core.Match;
import java.util.*;
import java.util.UUID;
public interface UserRepository { Optional<User> findById(UUID id); Optional<User> findByEmail(String email); User save(User u); }
public interface KycInquiryRepository { Optional<KycInquiry> findByProviderId(String providerId); KycInquiry save(KycInquiry k); Optional<KycInquiry> findById(UUID id); }
public interface FlightRouteRepository { FlightRoute save(FlightRoute r); List<FlightRoute> search(String from, String to); Optional<FlightRoute> findById(UUID id);}
public interface DeliveryRepository { DeliveryRequest save(DeliveryRequest d); Optional<DeliveryRequest> findById(UUID id); List<DeliveryRequest> findOpen(); }
public interface MatchRepository { Match save(Match m); List<Match> findByDeliveryId(UUID id); Optional<Match> findById(UUID id); }
