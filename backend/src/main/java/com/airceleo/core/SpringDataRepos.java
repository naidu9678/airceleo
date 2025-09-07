package com.airceleo.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import java.util.UUID;

public interface UserJpa extends JpaRepository<UserEntity, UUID> { Optional<UserEntity> findByEmail(String email); }
interface KycJpa extends JpaRepository<KycEntity, UUID> { Optional<KycEntity> findByProviderId(String providerId); }
interface RouteJpa extends JpaRepository<RouteEntity, UUID> {
  List<RouteEntity> findByDepartAirportIgnoreCaseAndArriveAirportIgnoreCase(String from, String to);
}

interface DeliveryJpa extends JpaRepository<DeliveryEntity, UUID> {}
interface MatchJpa extends JpaRepository<MatchEntity, UUID> { List<MatchEntity> findByDeliveryId(UUID deliveryId); }
