package com.airceleo.core;
import com.airceleo.core.DeliveryRequest;
import com.airceleo.core.Match;
import com.airceleo.core.DeliveryRepository;
import com.airceleo.core.FlightRouteRepository;
import com.airceleo.core.MatchRepository;
import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

public class ComputeMatches {
  private final DeliveryRepository delRepo; private final FlightRouteRepository routeRepo; private final MatchRepository matchRepo;
  public ComputeMatches(DeliveryRepository d, FlightRouteRepository f, MatchRepository m){ this.delRepo=d; this.routeRepo=f; this.matchRepo=m; }
  public List<Match> execute(UUID deliveryId){
    var d = delRepo.findById(deliveryId).orElseThrow();
    var routes = routeRepo.search(d.getPickupCity(), d.getDropCity());
    List<Match> out = new ArrayList<>();
    for (var r: routes){
      double capacityFit = r.getMaxWeightKg() >= d.getWeightKg() ? 1.0 : 0.0;
      double score = 0.4*capacityFit + 0.6; // simple baseline
      var m = new Match(UUID.randomUUID(), d.getId(), r.getId(), score, Match.Status.PROPOSED);
      out.add(matchRepo.save(m));
    }
    return out.stream().sorted(Comparator.comparing(Match::getScore).reversed()).collect(Collectors.toList());
  }
}
