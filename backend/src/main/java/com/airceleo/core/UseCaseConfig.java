package com.airceleo.adapters.config;
import com.airceleo.core.*;
import com.airceleo.core.*;

import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
@Configuration
public class UseCaseConfig {
  @Bean InitiateKyc initiateKyc(PersonaPort persona, KycInquiryRepository repo){ return new InitiateKyc(persona, repo); }
  @Bean CreateDelivery createDelivery(DeliveryRepository repo){ return new CreateDelivery(repo); }
  @Bean CreateRoute createRoute(FlightRouteRepository repo){ return new CreateRoute(repo); }
  @Bean ComputeMatches computeMatches(DeliveryRepository d, FlightRouteRepository f, MatchRepository m){ return new ComputeMatches(d,f,m); }
}
