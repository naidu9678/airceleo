package com.airceleo.core;
import com.airceleo.core.FlightRoute;
import com.airceleo.core.FlightRouteRepository;
import java.time.Instant;
import java.util.UUID;

public class CreateRoute {
  private final FlightRouteRepository repo;
  public CreateRoute(FlightRouteRepository repo){ this.repo=repo; }
  public FlightRoute execute(UUID travelerId, String from, String to, Instant depart, Instant arrive, FlightRoute.BaggageType bt, double maxKg){
    var r = new FlightRoute(UUID.randomUUID(), travelerId, from, to, depart, arrive, bt, maxKg);
    return repo.save(r);
  }
}
