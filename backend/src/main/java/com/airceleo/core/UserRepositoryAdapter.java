package com.airceleo.adapters.persistence;
import com.airceleo.core.ports.*;
import com.airceleo.core.users.User;
import com.airceleo.core.kyc.KycInquiry;
import com.airceleo.core.travel.FlightRoute;
import com.airceleo.core.delivery.DeliveryRequest;
import com.airceleo.core.match.Match;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.*;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepository {
  private final UserJpa jpa;
  public UserRepositoryAdapter(UserJpa jpa){ this.jpa=jpa; }
  @Override public Optional<User> findById(UUID id){ return jpa.findById(id).map(this::toDomain); }
  @Override public Optional<User> findByEmail(String email){ return jpa.findByEmail(email).map(this::toDomain); }
  @Override public User save(User u){
    UserEntity e=new UserEntity();
    e.setId(u.getId()); e.setEmail(u.getEmail()); e.setPhone(u.getPhone()); e.setTermsAccepted(u.isTermsAccepted());
    e.setKycStatus(u.getKycStatus().name()); e.setRatingAvg(u.getRatingAvg()); e.setRatingCount(u.getRatingCount()); e.setCreatedAt(u.getCreatedAt());
    jpa.save(e); return toDomain(e);
  }
  private User toDomain(UserEntity e){
    return new User(e.getId(), e.getEmail(), e.getPhone(), e.isTermsAccepted(), User.KycStatus.valueOf(e.getKycStatus()), e.getRatingAvg(), e.getRatingCount(), e.getCreatedAt());
  }
}

@Component
class KycRepositoryAdapter implements KycInquiryRepository {
  private final KycJpa jpa;
  public KycRepositoryAdapter(KycJpa jpa){ this.jpa=jpa; }
  @Override public Optional<KycInquiry> findByProviderId(String pid){ return jpa.findByProviderId(pid).map(this::toDomain); }
  @Override public KycInquiry save(KycInquiry k){
    KycEntity e=new KycEntity();
    e.setId(k.getId()); e.setUserId(k.getUserId()); e.setProviderId(k.getProviderId()); e.setHostedLink(k.getHostedLink());
    e.setStatus(k.getStatus().name()); e.setCreatedAt(k.getCreatedAt()); jpa.save(e); return toDomain(e);
  }
  @Override public Optional<KycInquiry> findById(UUID id){ return jpa.findById(id).map(this::toDomain); }
  private KycInquiry toDomain(KycEntity e){ return new KycInquiry(e.getId(), e.getUserId(), e.getProviderId(), e.getHostedLink(), KycInquiry.Status.valueOf(e.getStatus()), e.getCreatedAt()); }
}

@Component
class FlightRouteRepositoryAdapter implements FlightRouteRepository {
  private final RouteJpa jpa;
  public FlightRouteRepositoryAdapter(RouteJpa jpa){ this.jpa=jpa; }
  @Override public FlightRoute save(FlightRoute r){
    RouteEntity e=new RouteEntity();
    e.setId(r.getId()); e.setTravelerId(r.getTravelerId()); e.setDepartAirport(r.getDepartAirport()); e.setArriveAirport(r.getArriveAirport());
    e.setDepartTime(r.getDepartTime()); e.setArriveTime(r.getArriveTime()); e.setBaggageType(r.getBaggageType().name()); e.setMaxWeightKg(r.getMaxWeightKg());
    jpa.save(e); return r;
  }
  @Override public List<FlightRoute> search(String from, String to){
    return jpa.findByDepartAirportIgnoreCaseAndArriveAirportIgnoreCase(from,to).stream().map(this::toDomain).toList();
  }
  @Override public Optional<FlightRoute> findById(UUID id){ return jpa.findById(id).map(this::toDomain); }
  private FlightRoute toDomain(RouteEntity e){ return new FlightRoute(e.getId(), e.getTravelerId(), e.getDepartAirport(), e.getArriveAirport(), e.getDepartTime(), e.getArriveTime(), FlightRoute.BaggageType.valueOf(e.getBaggageType()), e.getMaxWeightKg()); }
}

@Component
class DeliveryRepositoryAdapter implements DeliveryRepository {
  private final DeliveryJpa jpa;
  public DeliveryRepositoryAdapter(DeliveryJpa jpa){ this.jpa=jpa; }
  @Override public DeliveryRequest save(DeliveryRequest d){
    DeliveryEntity e=new DeliveryEntity();
    e.setId(d.getId()); e.setSenderId(d.getSenderId()); e.setPickupCity(d.getPickupCity()); e.setDropCity(d.getDropCity());
    e.setItemType(d.getItemType()); e.setWeightKg(d.getWeightKg()); e.setSpecialHandling(d.isSpecialHandling());
    e.setStatus(d.getStatus().name()); e.setCreatedAt(d.getCreatedAt()); jpa.save(e); return d;
  }
  @Override public Optional<DeliveryRequest> findById(UUID id){ return jpa.findById(id).map(this::toDomain); }
  @Override public List<DeliveryRequest> findOpen(){ return jpa.findAll().stream().filter(e -> "OPEN".equals(e.getStatus())).map(this::toDomain).toList(); }
  private DeliveryRequest toDomain(DeliveryEntity e){ return new DeliveryRequest(e.getId(), e.getSenderId(), e.getPickupCity(), e.getDropCity(), e.getItemType(), e.getWeightKg(), e.isSpecialHandling(), DeliveryRequest.Status.valueOf(e.getStatus()), e.getCreatedAt()); }
}

@Component
class MatchRepositoryAdapter implements MatchRepository {
  private final MatchJpa jpa;
  public MatchRepositoryAdapter(MatchJpa jpa){ this.jpa=jpa; }
  @Override public Match save(Match m){
    MatchEntity e=new MatchEntity(); e.setId(m.getId()); e.setDeliveryId(m.getDeliveryId()); e.setRouteId(m.getRouteId()); e.setScore(m.getScore()); e.setStatus(m.getStatus().name());
    jpa.save(e); return m;
  }
  @Override public List<Match> findByDeliveryId(UUID id){ return jpa.findByDeliveryId(id).stream().map(this::toDomain).toList(); }
  @Override public Optional<Match> findById(UUID id){ return jpa.findById(id).map(this::toDomain); }
  private Match toDomain(MatchEntity e){ return new Match(e.getId(), e.getDeliveryId(), e.getRouteId(), e.getScore(), Match.Status.valueOf(e.getStatus())); }
}
